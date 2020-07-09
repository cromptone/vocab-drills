(ns app.components.exercise.list-selection
  (:require [re-frame.core :as rf]))

(defn vocab-lists []
  (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title
              :on-click #(rf/dispatch [:set-vocab-list id])}
     title]))

(defn lists []
  [:<>
   (vocab-lists)])
