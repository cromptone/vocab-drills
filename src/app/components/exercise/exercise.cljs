(ns app.components.exercise.exercise
  (:require [re-frame.core :as rf]
            [app.components.exercise.list-selection :as list-selection]
            [app.components.exercise.buttons :as buttons]
            [app.components.exercise.input :as input]
            [app.components.exercise.clouds :as clouds]))

(defn input-&-word-cloud []
  [:<>
   (buttons/buttons)
   [:p @(rf/subscribe [:prompt-text])]
   (input/input)
   (clouds/unanswered)
   (clouds/answered)
   (clouds/incorrect)])

(defn exercise []
  (if @(rf/subscribe [:exercise-in-progress?])
    (input-&-word-cloud)
    (list-selection/lists)))
