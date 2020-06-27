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
 :current-vocab-list
 :<- [:vocab-lists]
 :<- [:current-exercise-id]
 (fn [[vocab-lists current-exercise-id] _]
   (:vocab (first (filter #(= (:id %) current-exercise-id) vocab-lists)))))

(rf/reg-sub
 :unanswered-vocab
 :<- [:current-exercise]
 :<- [:current-vocab-list]
 (fn [[current-exercise current-vocab-list] _]
   (for [idx (get-in current-exercise [:vocab :unanswered])]
     (nth current-vocab-list idx))))
