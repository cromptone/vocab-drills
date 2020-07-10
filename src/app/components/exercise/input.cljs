(ns app.components.exercise.input
  (:require [re-frame.core :as rf]))

(defn input-handler [e]
  (when (= (.. e -keyCode) 13)
    (let [value (.. e -target -value)
          vocab @(rf/subscribe [:correct-answers])
          input-value-correct? (some #(= value %) (map first vocab))]
      (when input-value-correct?
        (do
          (rf/dispatch [:move-vocab-status value])
          (-> js/document (.getElementById "vocab-input") .-value (set! "")))))))

(defn input []
  [:input {:id "vocab-input"
           :on-key-up input-handler
           :auto-focus true}])
