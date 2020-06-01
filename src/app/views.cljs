(ns app.views)

(defn app
  []
  [:div
   [:h1 "Vocabulary Drills"]
   [:button {:on-click #(.alert js/window "Button clicked")} "Start exercises"]
   [:p "More to come"]])
