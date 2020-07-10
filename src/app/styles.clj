(ns app.styles
  (:require [clojure.java.io :as io]
            [garden.core :refer [css]]
            [garden.def :refer [defkeyframes]]))

(def accent-gray "#a2a2a2")
(def dark-back "#0f1418")

(defkeyframes shine
              ["0%" {:opacity 1}]
              ["8%" {:opacity 0.6}]
              ["100%" {:opacity 1}])

(defkeyframes shine1
              ["0%" {:transform "rotate(0deg)"}]
              ["15%" {:transform "rotate(1deg)"}]
              ["20%" {:transform "rotate(-1deg)"}]
              ["30%" {:transform "rotate(2deg)"}]
              ["70%" {:transform "rotate(-2deg)"}]
              ["75%" {:transform "rotate(1deg)"}]
              ["85%" {:transform "rotate(-1deg)"}]
              ["100%" {:transform "rotate(0deg)"}])

(defn build []
  (io/make-parents "public/css/styles.css")
  (css
   {:output-to "public/css/styles.css"
    :pretty-print? false}
   [shine]
   [shine1]
   [:body {:background-color dark-back
           :color "salmon"
           :font-family "Roboto, sans-serif"};
    [:h1 ^:prefix {:animation [[shine "2s" :infinite :alternate]]}]

    [:button {:border-radius "9px"
              :border-style "none"
              :margin "0px 0px 10px 10px"
              :background-color "salmon"
              :color "rgb(18, 18, 36)"
              :font-size "20px"
              :height "40px"
              :transition "background-color 2s"}]
    [:.cloud-word {:color dark-back
                   :display "inline-block"
                   :padding "5px"
                   :margin "5px"
                   :border-radius "5px"}]
    [:.button__active ^:prefix {:animation [[shine1 "1s"]]
                                :background-color "pink"}]
    [:.cloud-word__answered {:background-color "yellow"}]
    [:.cloud-word__incorrect {:background-color "orange"}]
    [:.cloud-word__unanswered {:background-color "pink"}]]))
