(ns app.views
  (:require [re-frame.core :as rf]))

(defn click-fn [vocab]
  (rf/dispatch [:value (ffirst vocab)]))

(defn app []
  [:<>
   [:h1 "Vocabulary Drills"]
   (for [{:keys [vocab title]} @(rf/subscribe [:vocab-lists])]
     [:button {:key title :on-click #(click-fn vocab)} title])
   [:h2 @(rf/subscribe [:value])]])
