(ns app.components.exercise.buttons
  (:require [re-frame.core :as rf]))

(defn button-data []
  [{:text "Go back" :dispatch-kw :clear-exercise}
   {:text "Remove 20 words"
    :dispatch-kw :remove-20-words
    :disabled? (->> @(rf/subscribe [:unanswered-vocab]) count (>= 20))}
   {:text "Restart" :dispatch-kw :reset}
   {:text "Redo with missed" :dispatch-kw :redo-with-missed}
   {:text "Show answers" :dispatch-kw :show-answers}])

(defn buttons []
  (for [{:keys [dispatch-kw text disabled?]} (button-data)]
    [:button {:key text
              :on-click #(rf/dispatch [dispatch-kw])
              :disabled disabled?}
     text]))
