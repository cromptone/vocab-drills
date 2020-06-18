(ns app.views
  (:require [shadow.resource :as rc]
            [clojure.edn :as edn]))

(defn click-fn [title]
  (js/alert title))

(defn app []
  [:div
   [:h1 "Vocabulary Drills"]
   (for [vocab-list (edn/read-string (rc/inline "../vocab/compiled_vocab.edn"))]
     [:button {:key (:title vocab-list) :on-Click #(click-fn (:vocab vocab-list))} (:title vocab-list)])])
