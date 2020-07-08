(ns app.menu)

(defn menu-item [{:keys [name href page]}]
  [:a {:on-click (:set-page page)
       :href href
       :key name}
   name])

(def menu-items
  [{:name "Exercises"
    :href "/exercises"
    :page :about}
   {:name "About"
    :href "/about"
    :page :about}])

(defn menu []
  [:nav "German vocabulary drills"
   (for [item menu-items]
     (menu-item item))])
