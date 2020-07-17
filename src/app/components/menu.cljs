(ns app.components.menu
  (:require [app.router :as router]
            [re-frame.core :as rf]))

(def menu-items
  [{:name "About" :page :about}
   {:name "Exercises" :page :exercise}])

(defn menu-item [{:keys [name page]}]
  (let [current-page (or @(rf/subscribe [:page]) :exercise)] ; exercise default
    [:a {:key name
         :on-click (:set-page page)
         :class (when (= page current-page) "active")
         :href (router/router-path page)}
     name]))

(defn menu []
  [:header
   [:span "German vocabulary drills"]
   [:nav (for [item menu-items] (menu-item item))]])
