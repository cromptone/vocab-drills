(ns app.core
  (:require [reagent.dom :as r]
            [re-frame.core :as rf]
            [app.events] ;; These two are required so that
            [app.subs]   ;; the compiler loads them
            [app.views :as views]))

(defn ^:dev/after-load start
  []
  (r/render [views/app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
