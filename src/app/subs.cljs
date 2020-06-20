(ns app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :value
 (fn [db _]
   (:value db)))

(rf/reg-sub
 :vocab-lists
 (fn [db _]
   (:vocab-lists db)))
