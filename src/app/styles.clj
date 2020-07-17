(ns app.styles
  (:require [clojure.java.io :as io]
            [garden.core :refer [css]]
            [garden.def :refer [defkeyframes]]))

(def gray "#a2a2a2")
(def dark-back "#0f1418")
(def dark-blue "#121224")
(def main-margin "50px")

(defkeyframes wiggle
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
    :pretty-print? true}
   [wiggle]
   [:body {:background-color dark-back
           :color "salmon"
           :font-family "Roboto, sans-serif"}
    [:.about {:margin main-margin
              :color gray}
     [:p {:max-width "600px"}]]
    [:header {:line-height "40px"
              :height "40px"
              :width "100%"
              :color dark-blue
              :background-color "#888888"
              :font-size "20px"
              :position "fixed"
              :left 0
              :top 0
              :box-shadow "0px 3px 9px #000000"}
     [:span {:float "left"
             :margin-left "20px"}]
     [:nav {:float "right"}
      [:a {:float "right"
           :margin-right "20px"
           :text-decoration "none"
           :color dark-blue
           :display "inline"
           :height "30px"}
       [:&:hover {:color "salmon"}]
       [:&.active {:border-bottom-style "solid"}]]]]
    [:.lists {:margin main-margin}]
    [:.exercise-btns {:margin-right "15%"
                      :float "right"}]
    [:.prompt {:margin "auto"
               :width "70%"
               :color gray
               :clear "both"}]
    [:button {:border-radius "9px"
              :border-style "none"}
     [:&:hover {:cursor "pointer"}]
     [:&.big-btn {:margin "0px 0px 10px 10px"
                  :background-color "salmon"
                  :color "rgb(18, 18, 36)"
                  :font-size "20px"
                  :height "40px"}]
     [:&.small-btn {:margin "0px 0px 10px 10px"
                    :background-color "rgb(162, 162, 162)"
                    :color "rgb(18, 18, 36)"
                    :font-size "12px"}]

     [:&.right {:float "right"}]]
    [:main {:margin-top "100px"}
     [:input {:width "70%"
              :height "55px"
              :padding "20px"
              :border "none"
              :border-radius "9px"
              :display "block"
              :margin "10px auto 20px auto"
              :background-color gray
              :color dark-blue
              :font-size "30px"
              :margin-bottom "30px"}]
     [:.word-clouds {:width "calc(20px + 70%)"
                     :display "flex"
                     :flex-direction "column"
                     :margin "auto"}]
     [:.cloud-word {:background-color gray
                    :color dark-blue
                    :margin "3px"
                    :float "left"
                    :border-radius "5px"
                    :padding "5px"
                    :opacity ".8"}
      [:&.example {:background-color "yellow"}]
      [:&.incorrect {:background-color "#ffa2a2"}]
      [:&.answered {:background-color "aliceblue"}]]
     [:.button__active ^:prefix {:animation [[wiggle "1s"]]
                                 :background-color "pink"}]]

    [:footer {:height "20px"
              :width "100%"
              :position "fixed"
              :left 0
              :bottom 0
              :background-color gray
              :color dark-blue
              :box-shadow "3px 3px 9px #000000"}
     [:span {:font-family "monospace"
             :margin-left "10px"
             :font-size "smaller"}]]]))
