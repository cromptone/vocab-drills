(ns app.components.exercise.clouds
  (:require [re-frame.core :as rf]))

(defn gen-cloud-key-map [ger eng]
  {:key (str ger "-" eng)})

(defn example []
  (when (-> @(rf/subscribe [:answered-vocab]) count (< 3))
    [:div.cloud-word.cloud-word__example
     (str "Enter the singular and plural, separated by a comma → das Beispiel, die Beispiele")]))

(defn unanswered []
  (when @(rf/subscribe [:show-unanswered-cloud?])
    (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
      [:div.cloud-word.cloud-word__unanswered (gen-cloud-key-map ger eng)
       (str eng)])))

(defn answered []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    [:div.cloud-word.cloud-word__answered (gen-cloud-key-map ger eng)
     (str ger " → " eng)]))

(defn incorrect []
  (for [[ger eng] @(rf/subscribe [:incorrect-vocab])]
    [:div.cloud-word.cloud-word__incorrect (gen-cloud-key-map ger eng)
     (str ger " → " eng)]))
