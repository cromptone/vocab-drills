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
                                                         count
                                                         range
                                                         shuffle)}})))

(rf/reg-event-db
 :clear-current-exercise
 (fn [db [_ _]]
   (dissoc db :current-exercise)))
