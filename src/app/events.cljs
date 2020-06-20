(ns app.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :current-exercise
 (fn [db [_ exercise]]
   (assoc db :current-exercise exercise)))
