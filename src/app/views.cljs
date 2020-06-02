(ns app.views)

(defn button [text]
  [:button {:on-click #(.alert js/window "hit")} text])

(defn app []
  [:div
   [:h1 "Vocabulary Drills"]
   (button "Start exercises")
   [:p "More to come"]])
