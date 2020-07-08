(ns app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :vocab-lists
 (fn [db _]
   (:vocab-lists db)))

(rf/reg-sub
 :current-exercise
 (fn [db _]
   (:current-exercise db)))

(rf/reg-sub
 :current-exercise-id
 :<- [:current-exercise]
 (fn [current-exercise _]
   (:exercise-id current-exercise)))

(rf/reg-sub
 :active-exercise?
 :<- [:current-exercise]
 (fn [current-exercise _]
   (boolean current-exercise)))

(rf/reg-sub
 :unanswered-vocab
 :<- [:current-exercise]
 (fn [current-exercise _]
   (get-in current-exercise [:vocab :unanswered])))

(rf/reg-sub
 :answered-vocab
 :<- [:current-exercise]
 (fn [current-exercise _]
   (get-in current-exercise [:vocab :answered])))

(rf/reg-sub
 :page
 (fn [db _]
   (:page db)))
