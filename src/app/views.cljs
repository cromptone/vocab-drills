(ns app.views
  (:require [re-frame.core :as rf]
            [clojure.pprint]))

(defn click-list [id]
  (rf/dispatch [:set-current-exercise id]))

(defn lists []
  (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title :on-click #(click-list id)} title]))

; (defn input-handler [e]
;   (let [value (.. e -target -value)
;         vocab @(rf/subscribe [:unanswered-vocab])]
;     (when-let [idx (ffirst (filter
;                             #(= value (first (second %)))
;                             vocab))]
;       (rf/dispatch [:add-correct-answer value]))))
;
; (defn vocab-input []
;   [:input {:on-key-up input-handler
;            :auto-focus true}])

(defn return-button []
  [:button {:on-click #(rf/dispatch [:clear-current-exercise])} "Go back"])

(defn click-cloud [e]
  (-> (.. e -target)
      (.getAttribute "correct")
      js/console.log))

(defn exercise []
  (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
    [:div.cloud-word {:key (str "XXX" "-" eng)
                      :correct ger
                      :on-click click-cloud}
     (str ger)]))

(defn app []
  [:<>
   [:h1 "Vocabulary Drills"]
   (if-not @(rf/subscribe [:active-exercise?])
     (lists)
     [:<>
      (return-button)
      ; (vocab-input)
      (exercise)])])
