(ns app.menu)

(defn menu []
  [:nav "Menu"
   [:a {:href "/about"} "About"]
   [:a {:href "/exercise"} "Exercise"]])
