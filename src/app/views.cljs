(ns app.views
  (:require [shadow.resource :as rc]
            [clojure.edn :as edn]))

(defn click-fn [title]
  (js/alert title))

(defn app []
  [:div
   [:h1 "Vocabulary Drills"]
   (for [{:keys [vocab title]} (-> "../vocab/compiled_vocab.edn" rc/inline edn/read-string)]
     [:button {:key title :on-click #(click-fn vocab)} title])])
