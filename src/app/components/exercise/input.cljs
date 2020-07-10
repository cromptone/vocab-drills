(ns app.components.exercise.input
  (:require [re-frame.core :as rf]))

(defn handler-word-cloud [e]
  (when (= (.. e -key) "Enter")
    (let [value (.. e -target -value)
          vocab @(rf/subscribe [:correct-answers])
          input-value-correct? (some #(= value %) (map first vocab))]
      (when input-value-correct?
        (do
          (rf/dispatch [:move-correct-vocab value])
          (-> js/document (.getElementById "vocab-input") .-value (set! "")))))))

(defn handler-prompt [e]
  (when (= (.. e -key) "Enter")
    (let [value (.. e -target -value)
          vocab @(rf/subscribe [:correct-answers])
          input-value-correct? (some #(= value %) (map first vocab))]
      (if input-value-correct?
        (rf/dispatch [:move-correct-vocab value])
        (rf/dispatch [:move-incorrect-vocab]))
      (-> js/document (.getElementById "vocab-input") .-value (set! "")))))

(defn input []
  [:input {:id "vocab-input"
           :on-key-up (case @(rf/subscribe [:exercise-option])
                        :word-cloud handler-word-cloud
                        :prompt handler-prompt
                        handler-word-cloud)
           :auto-focus true}])
