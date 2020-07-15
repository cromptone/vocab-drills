(ns app.components.exercise.clouds
  (:require [re-frame.core :as rf]))

(defn- cloud-word
  ([class text]         (cloud-word nil nil class text))
  ([ger eng class text] [:div.cloud-word {:class class
                                          :key (str ger "-" eng)}
                         text]))

(defn example []
  (when (-> @(rf/subscribe [:answered-vocab]) count (< 3))
    (cloud-word "cloud-word__example" (str "Enter the singular and plural,"
                                           " separated by a comma → das "
                                           "Beispiel, die Beispiele"))))

(defn unanswered []
  (when @(rf/subscribe [:show-unanswered-cloud?])
    (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
      (cloud-word ger eng "cloud-word__unanswered" (str eng)))))

(defn answered []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    (cloud-word ger eng "cloud-word__answered" (str ger " → " eng))))

(defn incorrect []
  (for [[ger eng] @(rf/subscribe [:incorrect-vocab])]
    (cloud-word ger eng "cloud-word__incorrect" (str ger " → " eng))))
