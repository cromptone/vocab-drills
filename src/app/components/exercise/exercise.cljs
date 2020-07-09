(ns app.components.exercise.exercise
  (:require [re-frame.core :as rf]
            [app.components.exercise.list-selection :as list-selection]
            [app.components.exercise.buttons :as buttons]
            [app.components.exercise.input :as input]
            [clojure.string :as str]))

(defn unanswered-cloud []
  (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
    [:div.cloud-word.cloud-word__unanswered {:key (str ger "-" eng)}
     (str eng)]))

(defn answered-cloud []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    [:div.cloud-word.cloud-word__answered {:key (str ger "-" eng)}
     (str ger " → " eng)]))

(defn input-&-word-cloud []
  (let [option @(rf/subscribe [:exercise-option])]
    (.log js/console (str option))
    [:<>
     (buttons/buttons)
     (when (= option :prompt)
       [:p (-> @(rf/subscribe [:correct-answers]) first second)])
     (input/input)
     (when (= option :word-cloud)
       (unanswered-cloud))
     (answered-cloud)]))

(defn exercise []
  (if @(rf/subscribe [:active-exercise?])
    (input-&-word-cloud)
    (list-selection/lists)))
