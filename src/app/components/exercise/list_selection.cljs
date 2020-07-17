(ns app.components.exercise.list-selection
  (:require [re-frame.core :as rf]))

(defn vocab-lists []
  (let [active-exercise-id @(rf/subscribe [:exercise-id])]
    (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
      [:button.big-btn {:key title
                        :on-click #(rf/dispatch [:set-vocab-list id])
                        :class (when (= active-exercise-id id)
                                 "active")}
       title])))

(def options-data
  [{:name "Word cloud" :option-kw :word-cloud}
   {:name "Single-word prompt" :option-kw :prompt}])

(defn exercise-options []
  (let [active-exercise-option @(rf/subscribe [:exercise-option])]
    [:div
     (for [{:keys [name option-kw]} options-data]
       [:button.big-btn {:key name
                         :on-click #(rf/dispatch
                                     [:set-exercise-option option-kw])
                         :class (when (= active-exercise-option option-kw)
                                  "active")}
        name])]))

(defn confirm-button []
  (when @(rf/subscribe [:valid-exercise-options?])
    [:button.big-btn.right {:on-click #(rf/dispatch [:start-exercise])
                            :auto-focus true}
     "Let's go!"]))

(defn lists []
  [:div.lists
   [:h1 "German vocabulary exercises"]
   [:h2 "Choose a vocabulary list:"]
   (vocab-lists)
   [:h2 "Choose exercise type:"]
   (exercise-options)
   (confirm-button)])
