(ns app.views
  (:require [re-frame.core :as rf]))

(defn click-list [id]
  (rf/dispatch [:set-current-exercise id]))

(defn lists []
  (for [{:keys [title id]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title :on-click #(click-list id)} title]))

(defn vocab-input []
  [:input {:on-key-up #(.log js/console (.. % -target -value))
           :auto-focus true}])

(defn return-button []
  [:button {:on-click #(rf/dispatch [:clear-current-exercise])} "Go back"])

(defn click-cloud [e]
  (-> (.. e -target)
      (.getAttribute "correct")
      js/console.log))

(defn exercise []
  (for [[idx [ger eng]] @(rf/subscribe [:unanswered-vocab])]
    [:div.cloud-word {:key (str idx "-" eng)
                      :idx idx
                      :correct ger
                      :on-click click-cloud}
     (str idx ger)]))

(defn app []
  [:<>
   [:h1 "Vocabulary Drills"]
   (if-not @(rf/subscribe [:active-exercise?])
     (lists)
     [:<>
      (return-button)
      (vocab-input)
      (exercise)])])
