(ns app.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :value
 (fn [db [_ val]]
   (assoc db :value val)))
