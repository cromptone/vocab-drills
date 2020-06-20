(ns app.db
  (:require [re-frame.core :as rf]
            [shadow.resource :as rc]
            [clojure.edn :as edn]))

(defn initial-app-db []
  {:vocab-lists (-> "../vocab/compiled_vocab.edn" rc/inline edn/read-string)})

(rf/reg-event-db :initialize-db (fn [_ _] (initial-app-db)))
