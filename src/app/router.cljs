(ns app.router
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]))

(def state (atom {}))

(def app-routes
  ["/" {"" :exercises
        "about " :about}])

(defn set-page! [match]
  (swap! state assoc :page match))

(def history
  (pushy/pushy set-page! (partial bidi/match-route app-routes)))

(defn start! []
  (pushy/start! history))
