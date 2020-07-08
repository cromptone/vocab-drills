(ns app.router
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as rf]))

(def app-routes
  ["/" {"" :exercises
        "about" :about}])

(def history
  (pushy/pushy #(rf/dispatch [:set-page %])
               (partial bidi/match-route app-routes)))

(defn start! []
  (pushy/start! history))
