(ns app.views
  (:require [shadow.resource :as rc]
            [clojure.edn :as edn]))

(defn click-fn [title]
  (js/alert title))

(defn app []
  [:div
   [:h1 "Vocabulary Drills"]
   (for [{:keys [vocab title]} (edn/read-string (rc/inline "../vocab/compiled_vocab.edn"))]
     [:button {:key title :on-click #(click-fn vocab)} title])])
