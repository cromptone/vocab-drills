(ns app.events
  (:require [re-frame.core :as rf]))

(defn get-unanswered [db] (get-in db [:exercise :vocab :unanswered]))
(defn get-dropped    [db] (get-in db [:exercise :vocab :dropped]))
(defn get-answered   [db] (get-in db [:exercise :vocab :answered]))

(defn clear-answered [db] (assoc-in db [:exercise :vocab :answered] []))
(defn clear-dropped  [db] (assoc-in db [:exercise :vocab :dropped] []))

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
         dropped (get-dropped db)]
     (-> db
         (clear-answered)
         (clear-dropped)
         (update-in [:exercise :vocab :unanswered]
                    #(->> % (concat answered dropped) shuffle))))))

(rf/reg-event-db
 :move-vocab-status
 (fn [db [_ value]]
   (let [remove-answer (fn [vocab] (remove #(= value (first %)) vocab))
         answer (filter #(= value (first %))
                        (get-in db [:exercise :vocab :unanswered]))]
     (-> db
         (update-in [:exercise :vocab :unanswered] remove-answer)
         (update-in [:exercise :vocab :answered] #(concat % answer))))))

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
