(ns app.components.exercise.exercise
  (:require [re-frame.core :as rf]
            [app.components.exercise.list-selection :as list-selection]))

(defn input-handler [e]
  (let [value (.. e -target -value)
        vocab @(rf/subscribe [:unanswered-vocab])
        input-value-correct? (some #(= value %) (map first vocab))]
    (when input-value-correct?
      (do
        (rf/dispatch [:move-vocab-status value])
        (-> js/document (.getElementById "vocab-input") .-value (set! ""))))))

(defn unanswered-cloud []
  (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
    [:div.cloud-word.cloud-word__unanswered {:key (str ger "-" eng)}
     (str eng)]))

(defn answered-cloud []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    [:div.cloud-word.cloud-word__answered {:key (str ger "-" eng)}
     (str ger " → " eng)]))

(defn input-&-word-cloud []
  [:<>
   [:button {:on-click #(rf/dispatch [:clear-current-exercise])} "Go back"]
   [:input {:id "vocab-input" :on-key-up input-handler :auto-focus true}]
   (unanswered-cloud)
   (answered-cloud)])

(defn exercise []
  (if-not @(rf/subscribe [:active-exercise?])
    (list-selection/lists)
    (input-&-word-cloud)))
