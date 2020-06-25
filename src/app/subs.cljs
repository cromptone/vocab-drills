(ns app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :current-exercise
 (fn [db _]
   (:current-exercise db)))

(rf/reg-sub
 :vocab-lists
 (fn [db _]
   (:vocab-lists db)))

(rf/reg-sub
 :active-exercise?
 (fn [db _]
   (boolean (:current-exercise db))))
