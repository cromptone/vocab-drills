(ns app.components.exercise.list-selection
  (:require [re-frame.core :as rf]))

(defn lists []
  (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title
              :on-click #(rf/dispatch [:set-current-exercise id])}
     title]))
