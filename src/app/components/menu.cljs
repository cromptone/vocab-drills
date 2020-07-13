(ns app.components.menu
  (:require [app.router :as router]))

(def menu-items
  [{:name "Exercises" :page :exercise}
   {:name "About" :page :about}])

(defn menu-item [{:keys [name page]}]
  [:a {:key name
       :on-click (:set-page page)
       :href (router/router-path page)}
   name])

(defn menu []
  [:nav (for [item menu-items] (menu-item item))])
