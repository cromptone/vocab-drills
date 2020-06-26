(ns app.core
  (:require [reagent.dom :as r]
            [re-frame.core :as rf]
            [app.views :as views]
            [app.events]
            [app.subs]
            [app.db]))

(defn ^:dev/after-load start
  []
  (rf/dispatch-sync [:initialize-db])
  (r/render [views/app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
