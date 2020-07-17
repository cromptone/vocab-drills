(ns app.components.about)

(def buttons
  [{:href "https://www.github.com/cromptone/vocab-drills" :text "View repository"}
   {:href "https://www.linkedin.com/in/alexandercrompton/" :text "View my LinkedIn"}])

(defn about []
  [:div.about
   [:h1 "About this website"]
   [:p "This is a German language website coded by Alexander Crompton. I created it because I wanted a customizable vocabulary drill app that matched my learning style; I also wanted to publish a website showing off my Clojure skills (since most of the Clojure work I've done so far is proprietary)."]
   [:p "This website is coded in ClojureScipt using Reagent and re-frame. The vocabulary is compiled from flat files using a Clojure script that runs at build-time. The CSS is written using the Garden library (though for a similar project in the future I'd probably stick to Sass), with the router being managed with the pushy library."]
   [:h2 "Want more vocabulary?"]
   [:p "Adding vocabulary is easy! Feel free to reach out to me if you'd like to see more vocab, or if you have any more ideas of how this might be useful for you."]
   (for [{:keys [href text]} buttons]
     [:a {:type "button"
          :href href
          :target "_blank"}
      [:button.big-btn text]])])
