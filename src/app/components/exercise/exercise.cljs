(ns app.components.exercise.exercise
  (:require [re-frame.core :as rf]
            [app.components.exercise.list-selection :as list-selection]
            [app.components.exercise.buttons :as buttons]
            [app.components.exercise.input :as input]
            [app.components.exercise.clouds :as cloud]))

(defn input-&-word-cloud []
  (let [option @(rf/subscribe [:exercise-option])]
    (.log js/console (str option))
    [:<>
     (buttons/buttons)
     (when (= option :prompt)
       [:p (-> @(rf/subscribe [:correct-answers]) first second)])
     (input/input)
     (cloud/unanswered-cloud)
     (cloud/answered-cloud)
     (cloud/incorrect-cloud)]))

(defn exercise []
  (if @(rf/subscribe [:active-exercise?])
    (input-&-word-cloud)
    (list-selection/lists)))
