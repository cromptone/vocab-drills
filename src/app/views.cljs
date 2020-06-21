(ns app.views
  (:require [re-frame.core :as rf]))

(defn click-fn [vocab]
  (rf/dispatch [:current-exercise {:vocab vocab}]))

(defn lists []
  (for [{:keys [vocab title]} @(rf/subscribe [:vocab-lists])]
    [:button {:key title :on-click #(click-fn vocab)} title]))

(defn exercise []
  (for [[ger eng] (:vocab @(rf/subscribe [:current-exercise]))]
    [:div.cloud-word {:key (str ger "-" eng)} eng]))

(defn app []
  [:<>
   [:h1 "Vocabulary Drills"]
   (lists)
   (exercise)])
