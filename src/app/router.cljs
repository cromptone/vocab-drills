(ns app.router
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as rf]))

(def app-routes
  ["/" {"" :exercise
        "exercise" :exercise
        "about" :about}])

(defn router-path [route]
  (bidi/path-for app-routes route))

(def history
  (pushy/pushy #(rf/dispatch [:set-page %])
               (partial bidi/match-route app-routes)))

(defn start! []
  (pushy/start! history))
