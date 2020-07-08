(ns app.components.exercise
  (:require [re-frame.core :as rf]))

(defn click-list [id]
  (rf/dispatch [:set-current-exercise id]))

(defn lists []
  (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title :on-click #(click-list id)} title]))

(defn input-handler [e]
  (let [value (.. e -target -value)
        vocab @(rf/subscribe [:unanswered-vocab])
        input-value-correct? (some #(= value %) (map first vocab))]
    (when input-value-correct?
      (do
        (rf/dispatch [:move-vocab-status value])
        (-> js/document (.getElementById "vocab-input") .-value (set! ""))))))

(defn vocab-input []
  [:input {:id "vocab-input"
           :on-key-up input-handler
           :auto-focus true}])

(defn return-button []
  [:button {:on-click #(rf/dispatch [:clear-current-exercise])} "Go back"])

(defn cloud []
  (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
    [:div.cloud-word.cloud-word__unanswered
     {:key (str ger "-" eng)
      :correct ger}
     (str ger)]))

(defn correct-answers []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    [:div.cloud-word.cloud-word__answered {:key (str ger "-" eng)}
     (str ger)]))

(defn input-&-word-cloud []
  [:<>
   (return-button)
   (vocab-input)
   (cloud)
   (correct-answers)])

(defn exercise []
  (if-not @(rf/subscribe [:active-exercise?])
    (lists)
    (input-&-word-cloud)))
