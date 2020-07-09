(ns app.components.exercise.input
  (:require [re-frame.core :as rf]))

(defn input-handler [e]
  (let [value (.. e -target -value)
        vocab @(rf/subscribe [:unanswered-vocab])
        input-value-correct? (some #(= value %) (map first vocab))]
    (when input-value-correct?
      (do
        (rf/dispatch [:move-vocab-status value])
        (-> js/document (.getElementById "vocab-input") .-value (set! ""))))))

(defn input []
  [:input {:id "vocab-input"
           :on-key-up input-handler
           :auto-focus true}])