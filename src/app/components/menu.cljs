(ns app.components.menu
  (:require [app.router :as router]))

(def menu-items
  [{:name "About" :page :about}
   {:name "Exercises" :page :exercise}])

(defn menu-item [{:keys [name page]}]
  [:a {:key name
       :on-click (:set-page page)
       :href (router/router-path page)}
   name])

(defn menu []
  [:header
   [:span "German vocabulary drills"]
   [:nav (for [item menu-items] (menu-item item))]])
