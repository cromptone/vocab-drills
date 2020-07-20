(ns app.compile-vocab
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.string :as str])
  (:import  [org.apache.commons.io FilenameUtils]))

(def COMPILED-DIR "./src/vocab/compiled_vocab.edn")

(defn prn-confirmation []
  (prn "Vocabulary compiled:")
  (prn (->> COMPILED-DIR
            io/reader
            java.io.PushbackReader.
            edn/read
            (map :title))))

(defn parse-file [idx f]
  (let [trim-in-coll (fn [coll] (map str/trim coll))
        title (with-open [rdr (io/reader f)]
                (-> rdr line-seq first))]
    {:title title
     :id (str idx "_" title)
     :vocab (with-open [rdr (io/reader f)]
              (->> rdr
                   line-seq
                   rest
                   distinct
                   (reduce conj [])
                   (map #(-> %
                             (str/split #"\t")
                             trim-in-coll))))}))

(defn compile-vocab []
  (let [txt-file? #(= "txt" (-> % .getName FilenameUtils/getExtension))
        files (->> "./src/vocab" io/file .listFiles (filter txt-file?))]
    (io/make-parents COMPILED-DIR)
    (with-open [wrtr (io/writer COMPILED-DIR)]
      (->> files
           (map-indexed parse-file)
           flatten
           pr-str
           (.write wrtr))))
  (prn-confirmation))
