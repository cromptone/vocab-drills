(ns app.components.exercise.input
  (:require [re-frame.core :as rf]))

(defn clear-input []
  (-> js/document (.getElementById "vocab-input") .-value (set! "")))

(defn handler-word-cloud [e]
  (when (= (.. e -key) "Enter")
    (let [value (.. e -target -value)
          vocab @(rf/subscribe [:correct-answers])
          input-value-correct? (some #(= value %) (map first vocab))]
      (when input-value-correct?
        (do
          (rf/dispatch [:move-correct-vocab value])
          (clear-input))))))

(defn handler-prompt [e]
  (when (= (.. e -key) "Enter")
    (let [value (.. e -target -value)
          correct-answer (ffirst @(rf/subscribe [:correct-answers]))
          input-value-correct? (= value correct-answer)]
      (if input-value-correct?
        (rf/dispatch [:move-correct-vocab value])
        (rf/dispatch [:move-incorrect-vocab]))
      (clear-input))))

(defn input []
  [:input {:id "vocab-input"
           :on-key-down (case @(rf/subscribe [:exercise-option])
                          :word-cloud handler-word-cloud
                          :prompt handler-prompt
                          handler-word-cloud)
           :auto-focus true}])
