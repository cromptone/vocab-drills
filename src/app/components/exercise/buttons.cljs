(ns app.components.exercise.buttons
  (:require [re-frame.core :as rf]))

(defn button-data []
  [{:text "Exit exercise" :dispatch-kw :clear-exercise}
   {:text "Remove 20 words"
    :dispatch-kw :remove-20-words
    :disabled? (->> @(rf/subscribe [:unanswered-vocab]) count (>= 20))}
   {:text "Redo with missed" :dispatch-kw :redo-with-missed}
   {:text "Show answers" :dispatch-kw :show-answers}
   {:text "Restart" :dispatch-kw :reset}])

(defn buttons []
  [:div.exercise-btns
   (for [{:keys [dispatch-kw text disabled?]} (button-data)]
     [:button {:class "small-btn"
               :key text
               :on-click #(rf/dispatch [dispatch-kw])
               :disabled disabled?}
      text])])
