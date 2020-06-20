(ns app.compile-vocab
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.string :as str])
  (:import  [org.apache.commons.io FilenameUtils]))

(defn reduce-file [f]
  {:title (with-open [rdr (io/reader f)]
            (-> rdr line-seq first))
   :vocab (with-open [rdr (io/reader f)]
            (->> rdr
                 line-seq
                 rest
                 (reduce conj [])
                 (map #(str/split % #"\t"))))})

(defn compile-vocab []
  (let [txt-file? #(= "txt" (-> % .getName FilenameUtils/getExtension))
        files (->> "./src/vocab" io/file .listFiles (filter txt-file?))]
    (io/make-parents "./src/vocab/compiled_vocab.edn")
    (with-open [wrtr (io/writer "./src/vocab/compiled_vocab.edn")]
      (.write wrtr (pr-str (vec (flatten (map reduce-file files))))))
    (prn (map :title (edn/read (java.io.PushbackReader. (io/reader "./src/vocab/compiled_vocab.edn")))))))
