(ns app.components.main-view
  (:require [re-frame.core :as rf]
            [app.components.menu :refer [menu]]
            [app.components.exercise.exercise :refer [exercise]]
            [app.components.about :refer [about]]))

(defn app []
  (let [page-kw @(rf/subscribe [:page])]
    [:<>
     (menu)
     [:h1 "Vocabulary Drills"]
     (case page-kw
       :about (about)
       (exercise))]))
