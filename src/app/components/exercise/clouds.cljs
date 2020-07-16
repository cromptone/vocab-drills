(ns app.components.exercise.clouds
  (:require [re-frame.core :as rf]))

(defn- gen-key [ger eng] (str ger "-" eng))

(defn- cloud-word [{:keys [key class text]}]
  [:p.cloud-word {:class class :key key} text])

(defn example []
  (when (-> @(rf/subscribe [:answered-vocab]) count (< 3))
    [:div.word-cloud.example
     (let [text (str "Enter the singular and plural,  separated by a comma → das "
                     "Beispiel, die Beispiele")]
       (cloud-word {:key text
                    :class "example"
                    :text text}))]))

(defn unanswered []
  (when @(rf/subscribe [:show-unanswered-cloud?])
    [:div.word-cloud.unanswered-cloud
     (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
       (cloud-word {:key (gen-key ger eng)
                    :class "unanswered"
                    :text (str eng)}))]))

(defn answered []
  [:div.word-cloud.answered-cloud
   (for [[ger eng] @(rf/subscribe [:answered-vocab])]
     (cloud-word {:key (gen-key ger eng)
                  :class "answered"
                  :text (str ger " → " eng)}))])

(defn incorrect []
  [:div.word-cloud.incorrect-cloud
   (for [[ger eng] (reverse @(rf/subscribe [:incorrect-vocab]))]
     (cloud-word {:key (gen-key ger eng)
                  :class "incorrect"
                  :text (str ger " → " eng)}))])
