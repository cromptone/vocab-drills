(ns app.styles
  (:require [clojure.java.io :as io]
            [garden.core :refer [css]]))

(defn build []
  (io/make-parents "public/css/styles.css")
  (css
   {:output-to "public/css/styles.css"
    :pretty-print? false}
   [:body {:background-color "salmon"}]))
