(ns app.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :set-exercise-option
 (fn [db [_ id]]
   (assoc-in db [:exercise :exercise-option] id)))

(rf/reg-event-db
 :set-vocab-list
 (fn [db [_ id]]
   (-> db
       (assoc-in [:exercise :exercise-id] id)
       (assoc-in [:exercise :vocab :answered] [])
       (assoc-in [:exercise :vocab :unanswered]  (->> db
                                                      :vocab-lists
                                                      (filter #(= (:id %) id))
                                                      first
                                                      :vocab
                                                      shuffle)))))

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
   (update-in db [:exercise :vocab :unanswered] (partial drop 20))))

(rf/reg-event-db
 :clear-exercise
 (fn [db [_ _]]
   (dissoc db :exercise)))

(rf/reg-event-db
 :set-page
 (fn [db [_ {:keys [handler]}]]
   (assoc db :page handler)))
