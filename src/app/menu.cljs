(ns app.menu
  (:require [app.router :as router]))

(defn menu-item [{:keys [name page]}]
  [:a {:on-click (:set-page page)
       :href (router/router-path page)
       :key name}
   name])

(def menu-items
  [{:name "Exercises"
    :page :exercise}
   {:name "About"
    :page :about}])

(defn menu []
  [:nav "German vocabulary drills"
   (for [item menu-items]
     (menu-item item))])
