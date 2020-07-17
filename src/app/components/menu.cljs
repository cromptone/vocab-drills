(ns app.components.menu
  (:require [app.router :as router]
            [re-frame.core :as rf]))

(def menu-items
  [{:title "About" :page :about}
   {:title "Exercises" :page :exercise}])

(defn menu-item [{:keys [title page]}]
  (let [current-page (or @(rf/subscribe [:page])
                         :exercise)]
    [:a {:key title
         :on-click (:set-page page)
         :class (when (= page current-page) "active")
         :href (router/router-path page)}
     title]))

(defn menu []
  [:header
   [:span "German vocabulary drills"]
   [:nav (for [item menu-items] (menu-item item))]])
