(ns app.views
  (:require [re-frame.core :as rf]
            [app.menu :refer [menu]]
            [clojure.pprint]))

(defn click-list [id]
  (rf/dispatch [:set-current-exercise id]))

(defn lists []
  (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title :on-click #(click-list id)} title]))

(defn input-handler [e]
  (let [value (.. e -target -value)
        vocab @(rf/subscribe [:unanswered-vocab])]
    (if (some #(= value %) (map first vocab))
      (rf/dispatch [:move-vocab-status value]))))
;
(defn vocab-input []
  [:input {:on-key-up input-handler
           :auto-focus true}])

(defn return-button []
  [:button {:on-click #(rf/dispatch [:clear-current-exercise])} "Go back"])

(defn click-cloud [e]
  (-> (.. e -target)
      (.getAttribute "correct")
      js/console.log))

(defn exercise []
  (for [[ger eng] @(rf/subscribe [:unanswered-vocab])]
    [:div.cloud-word.cloud-word__unanswered
     {:key (str ger "-" eng)
      :correct ger
      :on-click click-cloud}
     (str ger)]))

(defn correct-answers []
  (for [[ger eng] @(rf/subscribe [:answered-vocab])]
    [:div.cloud-word.cloud-word__answered {:key (str ger "-" eng)}
     (str ger)]))

(defn app []
  (let [page-kw @(rf/subscribe [:page])]
    [:<>
     (menu)
     [:h1 "Vocabulary Drills"
      (case page-kw
        :about [:p "About me"]
        (if-not @(rf/subscribe [:active-exercise?])
          (lists)
          [:<>
           (return-button)
           (vocab-input)
           (exercise)
           (correct-answers)]))]]))
