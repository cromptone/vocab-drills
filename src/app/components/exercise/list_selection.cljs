(ns app.components.exercise.list-selection
  (:require [re-frame.core :as rf]))

(defn vocab-lists []
  (let [active-exercise-id @(rf/subscribe [:exercise-id])]
    (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
      [:button {:key title
                :on-click #(rf/dispatch [:set-vocab-list id])
                :class (when (= active-exercise-id id) ["button__active"])}
       title])))

(def options-data
  [{:name "Word cloud" :option-kw :word-cloud}
   {:name "Single-word prompt" :option-kw :prompt}])

(defn exercise-options []
  (let [active-exercise-option @(rf/subscribe [:exercise-option])]
    [:div
     (for [{:keys [name option-kw]} options-data]
       [:button {:key name
                 :on-click #(rf/dispatch [:set-exercise-option option-kw])
                 :class (when (= active-exercise-option option-kw) ["button__active"])}
        name])]))

(defn lists []
  [:<>
   (vocab-lists)
   (exercise-options)])
