(ns app.components.exercise.clouds
  (:require [re-frame.core :as rf]))

(defn example []
  (when (-> @(rf/subscribe [:answered-vocab]) count (< 3))
    [:div.cloud-word.cloud-word__example
     (str "Enter the singular and plural, separated by a comma → das Beispiel, die Beispiele")]))

(defn unanswered []
  (when @(rf/subscribe [:show-unanswered-cloud?])
    (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
      [:div.cloud-word.cloud-word__unanswered {:key (str ger "-" eng)}
       (str eng)])))

(defn answered []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    [:div.cloud-word.cloud-word__answered {:key (str ger "-" eng)}
     (str ger " → " eng)]))

(defn incorrect []
  (for [[ger eng] @(rf/subscribe [:incorrect-vocab])]
    [:div.cloud-word.cloud-word__incorrect {:key (str ger "-" eng)}
     (str ger " → " eng)]))
