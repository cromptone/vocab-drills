(ns app.views)

(def make-alert #(.alert js/window "Button clicked"))

(defn button [text make-alert]
  [:button {:on-click #(.alert js/window "Button clicked")} text])

(defn app []
  [:div
   [:h1 "Vocabulary Drills"]
   (button "Start exercises" make-alert)
   [:p "More to come"]])
