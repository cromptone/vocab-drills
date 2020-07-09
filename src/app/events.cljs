(ns app.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :set-vocab-list
 (fn [db [_ id]]
   (assoc db :current-exercise {:exercise-id id
                                :vocab {:answered []
                                        :unanswered (->> db
                                                         :vocab-lists
                                                         (filter #(= (:id %) id))
                                                         first
                                                         :vocab
                                                         shuffle)}})))

(rf/reg-event-db
 :move-vocab-status
 (fn [db [_ value]]
   (let [answer (filter #(= value (first %)) (get-in db [:current-exercise :vocab :unanswered]))]
     (-> db
         (update-in [:current-exercise :vocab :unanswered] (fn [vocab] (remove #(= value (first %)) vocab)))
         (update-in [:current-exercise :vocab :answered] #(concat % answer))))))

(rf/reg-event-db
 :clear-current-exercise
 (fn [db [_ _]]
   (dissoc db :current-exercise)))

(rf/reg-event-db
 :set-page
 (fn [db [_ {:keys [handler]}]]
   (assoc db :page handler)))
