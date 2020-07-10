(ns app.events
  (:require [re-frame.core :as rf]))

(defn get-unanswered [db] (get-in db [:exercise :vocab :unanswered]))
(defn get-dropped    [db] (get-in db [:exercise :vocab :dropped]))
(defn get-answered   [db] (get-in db [:exercise :vocab :answered]))
(defn get-incorrect  [db] (get-in db [:exercise :vocab :incorrect]))

(defn clear-unanswered [db] (assoc-in db [:exercise :vocab :unanswered] []))
(defn clear-answered   [db] (assoc-in db [:exercise :vocab :answered] []))
(defn clear-dropped    [db] (assoc-in db [:exercise :vocab :dropped] []))
(defn clear-incorrect  [db] (assoc-in db [:exercise :vocab :incorrect] []))

(rf/reg-event-db
 :set-exercise-option
 (fn [db [_ id]]
   (assoc-in db [:exercise :exercise-option] id)))

(rf/reg-event-db
 :set-vocab-list
 (fn [db [_ id]]
   (-> db
       (assoc-in [:exercise :exercise-id] id)
       clear-answered
       clear-dropped
       clear-incorrect
       (assoc-in [:exercise :vocab :unanswered]  (->> db
                                                      :vocab-lists
                                                      (filter #(= (:id %) id))
                                                      first
                                                      :vocab
                                                      shuffle)))))

(rf/reg-event-db
 :reset
 (fn [db [_ _]]
   (let [answered (get-answered db)
         dropped (get-dropped db)
         incorrect (get-incorrect db)]
     (-> db
         (clear-answered)
         (clear-dropped)
         (clear-incorrect)
         (update-in [:exercise :vocab :unanswered]
                    #(->> % (concat answered dropped incorrect) shuffle))))))

(rf/reg-event-db
 :redo-with-missed
 (fn [db [_ _]]
   (let [answered (get-answered db)
         incorrect (get-incorrect db)]
     (-> db
         (clear-answered)
         (clear-incorrect)
         (update-in [:exercise :vocab :dropped]
                    #(concat % answered))
         (update-in [:exercise :vocab :unanswered]
                    #(->> % (concat incorrect) shuffle))))))

(rf/reg-event-db
 :move-correct-vocab
 (fn [db [_ value]]
   (let [remove-answer (fn [vocab] (remove #(= value (first %)) vocab))
         answer (filter #(= value (first %))
                        (get-in db [:exercise :vocab :unanswered]))]
     (-> db
         (update-in [:exercise :vocab :unanswered] remove-answer)
         (update-in [:exercise :vocab :answered] #(concat % answer))))))

(rf/reg-event-db
 :move-incorrect-vocab
 (fn [db [_ _]]
   (let [[incorrect unanswered] (split-at 1 (get-unanswered db))]
     (-> db
         (update-in [:exercise :vocab :incorrect] #(concat % incorrect))
         (assoc-in [:exercise :vocab :unanswered] unanswered)))))

(rf/reg-event-db
 :remove-20-words
 (fn [db [_ _]]
   (let [[dropped unanswered] (split-at 20 (get-unanswered db))]
     (-> db
         (assoc-in [:exercise :vocab :unanswered] unanswered)
         (update-in [:exercise :vocab :dropped] (partial concat dropped))))))

(rf/reg-event-db
 :clear-exercise
 (fn [db [_ _]]
   (dissoc db :exercise)))

(rf/reg-event-db
 :set-page
 (fn [db [_ {:keys [handler]}]]
   (assoc db :page handler)))
