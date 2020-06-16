(ns app.views
  (:require [shadow.resource :as rc]
            [clojure.edn :as edn]))

(defn app []
  [:div
   [:h1 "Vocabulary Drills"]
   (for [title (map :title (edn/read-string (rc/inline "../vocab/compiled_vocab.edn")))]
     [:button {:class "btn1" :key title} title])])
