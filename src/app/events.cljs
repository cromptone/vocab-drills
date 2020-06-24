(ns app.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :set-current-exercise
 (fn [db [_ exercise]]
   (assoc db :current-exercise exercise)))
