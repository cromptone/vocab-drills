(ns app.core
  (:require [reagent.dom :as r]
            [re-frame.core :as rf]
            [app.components.main-view :refer [app]]
            [app.events]
            [app.subs]
            [app.db]
            [app.router]))

(defn ^:dev/after-load start
  []
  (rf/dispatch-sync [:initialize-db])
  (r/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (app.router/start!)
  (start))
