(ns app.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :set-current-exercise
 (fn [db [_ id]]
   (assoc db :current-exercise {:exercise-id id
                                :vocab {:unanswered (->> db
                                                         :vocab-lists
                                                         (filter #(= (:id %) id))
                                                         first
                                                         :vocab
                                                         shuffle)}})))

(rf/reg-event-db
 :move-vocab-status
 (fn [db [_ value]]
   (-> db
       (update-in [:current-exercise :vocab :unanswered] (fn [vocab] (remove #(= value (first %)) vocab))))))

(rf/reg-event-db
 :clear-current-exercise
 (fn [db [_ _]]
   (dissoc db :current-exercise)))
