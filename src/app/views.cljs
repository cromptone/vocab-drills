(ns app.views
  (:require [re-frame.core :as rf]))

(defn click-list [vocab]
  (rf/dispatch [:current-exercise {:vocab vocab}]))

(defn click-cloud [e]
  (-> (.. e -target)
      (.getAttribute "correct")
      js/console.log))

(defn lists []
  (for [{:keys [vocab title]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title :on-click #(click-list vocab)} title]))

(defn exercise []
  (for [[idx [ger eng]] (->> @(rf/subscribe [:current-exercise])
                             :vocab
                             (map-indexed vector)
                             shuffle)]
    [:div.cloud-word {:key (str idx "-" eng)
                      :idx idx
                      :correct ger
                      :on-click click-cloud}
     (str idx ger)]))

(defn app []
  [:<>
   [:h1 "Vocabulary Drills"]
   (lists)
   (exercise)])
