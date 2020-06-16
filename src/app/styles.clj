(ns app.styles
  (:require [clojure.java.io :as io]
            [garden.core :refer [css]]))

(def accent-gray "#a2a2a2")
(def dark-back "#0f1418")

(defn build []
  (io/make-parents "public/css/styles.css")
  (css
   {:output-to "public/css/styles.css"
    :pretty-print? false}
   [:body {:background-color dark-back
           :color "salmon"
           :font-family "Roboto, sans-serif"};

    [:button {:border-radius "9px"
              :border-style "none"
              :margin "0px 0px 10px 10px"
              :background-color "salmon"
              :color "rgb(18, 18, 36)"
              :font-size "20px"
              :height "40px"}]]))
