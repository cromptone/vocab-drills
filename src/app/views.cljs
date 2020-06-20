(ns app.views
  (:require [shadow.resource :as rc]
            [clojure.edn :as edn]
            [re-frame.core :as rf]))

(defn click-fn [title]
  (rf/dispatch [:value (first title)]))

(defn app []
  [:<>
   [:h1 "Vocabulary Drills"]
   (for [{:keys [vocab title]} (-> "../vocab/compiled_vocab.edn" rc/inline edn/read-string)]
     [:button {:key title :on-click #(click-fn vocab)} title])
   [:h2 @(rf/subscribe [:value])]])
