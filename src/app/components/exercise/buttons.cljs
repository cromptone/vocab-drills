(ns app.components.exercise.buttons
  (:require [re-frame.core :as rf]))

(defn button-data []
  [{:text "Go back" :dispatch-kw :clear-exercise}
   {:text "Remove 20 words"
    :dispatch-kw :remove-20-words
    :disabled (->> @(rf/subscribe [:unanswered-vocab])
                   count
                   (>= 20))}
   {:text "Reset exercise" :dispatch-kw :reset}])

(defn buttons []
  (for [{:keys [dispatch-kw text disabled]} (button-data)]
    [:button {:key text
              :on-click #(rf/dispatch [dispatch-kw])
              :disabled disabled}
     text]))
