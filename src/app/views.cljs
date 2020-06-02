(ns app.views
  (:require [shadow.resource :as rc]))

(defn button [text]
  [:button {:on-click #(.alert js/window (rc/inline "../vocab/test.txt"))} text])

(defn app []
  [:div
   [:h1 "Vocabulary Drills"]
   (button "Start exercises")
   [:p "More to come"]])
