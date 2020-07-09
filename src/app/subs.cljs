(ns app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :vocab-lists
 (fn [db _]
   (:vocab-lists db)))

(rf/reg-sub
 :exercise
 (fn [db _]
   (:exercise db)))

(rf/reg-sub
 :vocab
 :<- [:exercise]
 (fn [exercise _]
   (:vocab exercise)))

(rf/reg-sub
 :exercise-id
 :<- [:exercise]
 (fn [exercise _]
   (:exercise-id exercise)))

(rf/reg-sub
 :exercise-option
 :<- [:exercise]
 (fn [exercise _]
   (:exercise-option exercise)))

(rf/reg-sub
 :active-exercise?
 :<- [:exercise-id]
 :<- [:exercise-option]
 (fn [[exercise-option exercise-id] _]
   (boolean (and exercise-id exercise-option))))

(rf/reg-sub
 :unanswered-vocab
 :<- [:vocab]
 (fn [vocab _]
   (:unanswered vocab)))

(rf/reg-sub
 :answered-vocab
 :<- [:vocab]
 (fn [vocab _]
   (:answered vocab)))

(rf/reg-sub
 :page
 (fn [db _]
   (:page db)))
