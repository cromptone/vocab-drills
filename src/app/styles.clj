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
    [:header   {:color "#121224"
                :background-color "#888888"
                :font-size 15
                :font-family "sans-serif"
                :display "flex"
                :height "30px"
                :margin "0 auto 0 auto"
                :position "fixed"
                :left 0
                :top 0
                :width "100%"
                :box-shadow "0px 3px 9px #000000"
                :bottom-margin "80px"
                :padding "5px 10px"}]
    [:main {:margin 100}
     [:.cloud-word {:background-color "#a2a2a2"
                    :color "#121224"
                    :margin "3px"
                    :float "left"
                    :border-radius "5px"
                    :padding "5px"}
      [:&.cloud-word__answered {:opacity ".6"}]
      [:&.cloud-word__incorrect {:background-color "#ffa2a2"}]
      [:&.cloud-word__unanswered {:opacity ".4"}]]
     [:.button__active ^:prefix {:animation [[shine1 "1s" :infinite :alternate]]
                                 :background-color "pink"}]]

    [:footer {:height 100 :clear "both"}]]))
