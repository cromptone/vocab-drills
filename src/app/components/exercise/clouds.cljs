(ns app.components.exercise.clouds
  (:require [re-frame.core :as rf]))

(defn unanswered-cloud []
  (when @(rf/subscribe [:show-unanswered-cloud?])
    (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
      [:div.cloud-word.cloud-word__unanswered {:key (str ger "-" eng)}
       (str eng)])))

(defn answered-cloud []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    [:div.cloud-word.cloud-word__answered {:key (str ger "-" eng)}
     (str ger " → " eng)]))

(defn incorrect-cloud []
  (for [[ger eng] @(rf/subscribe [:incorrect-vocab])]
    [:div.cloud-word.cloud-word__incorrect {:key (str ger "-" eng)}
     (str ger " → " eng)]))
