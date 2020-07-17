(ns app.components.menu
  (:require [app.router :as router]))

(def menu-items
  [{:name "Exercises" :page :exercise}
   {:name "About" :page :about}])

(defn menu-item [{:keys [name page]}]
  [:a {:style {:float "right"
               :text-decoration "none"
               :color "green"}
       :key name
       :on-click (:set-page page)
       :href (router/router-path page)}
   name])

(defn menu []
  [:header "German vocabulary drills"
   [:nav {:float "right"}
    (for [item menu-items] (menu-item item))]])
