(ns app.events
  (:require [re-frame.core :as rf]))

(def blank-vocab-list
  {:answered []
   :dropped []
   :incorrect []
   :unanswered []})

(defn get-unanswered [db] (get-in db [:exercise :vocab :unanswered]))

(defn move-vocab-from-to [db from-kw to-kw]
  (let [from-vocab (get-in db [:exercise :vocab from-kw])]
    (-> db
        (assoc-in [:exercise :vocab from-kw] [])
        (update-in [:exercise :vocab to-kw]
                   #(concat % from-vocab)))))

(rf/reg-event-db
 :set-exercise-option
 (fn [db [_ id]]
   (assoc-in db [:exercise :exercise-option] id)))

(rf/reg-event-db
 :set-vocab-list
 (fn [db [_ id]]
   (-> db
       (assoc-in [:exercise :exercise-id] id)
       (assoc-in [:exercise :vocab] blank-vocab-list)
       (assoc-in [:exercise :vocab :unanswered]  (->> db
                                                      :vocab-lists
                                                      (filter #(= (:id %) id))
                                                      first
                                                      :vocab
                                                      shuffle)))))

(rf/reg-event-db
 :start-exercise
 (fn [db [_ _]]
   (if (and (get-in db [:exercise :exercise-id])
            (get-in db [:exercise :exercise-option]))
     (assoc-in db [:exercise :exercise-in-progress?] true))))

(rf/reg-event-db
 :reset
 (fn [db [_ _]]
   (-> db
       (move-vocab-from-to :answered :unanswered)
       (move-vocab-from-to :dropped :unanswered)
       (move-vocab-from-to :incorrect :unanswered)
       (update-in [:exercise :vocab :unanswered] shuffle))))

(rf/reg-event-db
 :redo-with-missed
 (fn [db [_ _]]
   (-> db
       (move-vocab-from-to :answered :dropped)
       (move-vocab-from-to :incorrect :unanswered)
       (update-in [:exercise :vocab :unanswered] shuffle))))

(rf/reg-event-db
 :move-correct-vocab
 (fn [db [_ value]]
   (let [remove-answer (fn [vocab] (remove #(= value (first %)) vocab))
         answer (filter #(= value (first %)) (get-unanswered db))]
     (-> db
         (update-in [:exercise :vocab :unanswered] remove-answer)
         (update-in [:exercise :vocab :answered] #(concat % answer))))))

(rf/reg-event-db
 :move-incorrect-prompt-vocab
 (fn [db [_ _]]
   (let [[incorrect unanswered] (split-at 1 (get-unanswered db))]
     (-> db
         (assoc-in [:exercise :vocab :unanswered] unanswered)
         (update-in [:exercise :vocab :incorrect] #(concat % incorrect))))))

(rf/reg-event-db
 :remove-20-words
 (fn [db [_ _]]
   (let [[dropped unanswered] (split-at 20 (get-unanswered db))]
     (-> db
         (assoc-in [:exercise :vocab :unanswered] unanswered)
         (update-in [:exercise :vocab :dropped] (partial concat dropped))))))

(rf/reg-event-db
 :show-answers
 (fn [db [_ _]]
   (move-vocab-from-to db :unanswered :incorrect)))

(rf/reg-event-db
 :clear-exercise
 (fn [db [_ _]]
   (dissoc db :exercise)))

(rf/reg-event-db
 :set-page
 (fn [db [_ {:keys [handler]}]]
   (assoc db :page handler)))
