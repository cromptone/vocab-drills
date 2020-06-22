(ns app.views
  (:require [re-frame.core :as rf]))

(defn click-fn [vocab]
  (rf/dispatch [:current-exercise {:vocab vocab}]))

(defn lists []
  (for [{:keys [vocab title]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title :on-click #(click-fn vocab)} title]))

(defn exercise []
  (for [[idx [ger eng]] (->> @(rf/subscribe [:current-exercise])
                             :vocab
                             (map-indexed vector)
                             shuffle)]
    [:div.cloud-word {:key (str idx "-" eng)} (str idx ger)]))

(defn app []
  [:<>
   [:h1 "Vocabulary Drills"]
   (lists)
   (exercise)])
