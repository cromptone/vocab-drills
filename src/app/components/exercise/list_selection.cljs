(ns app.components.exercise.list-selection
  (:require [re-frame.core :as rf]))

(defn vocab-lists []
  (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title
              :on-click #(rf/dispatch [:set-vocab-list id])}
     title]))

(defn exercise-options []
  [:div
   [:button "Exercise A"]
   [:button "Exercise B"]])

(defn lists []
  [:<>
   (vocab-lists)
   (exercise-options)])
