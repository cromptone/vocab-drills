(ns app.compile-vocab
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn])
  (:import  [org.apache.commons.io FilenameUtils]))

(defn compile-vocab []
  (let [txt-file? #(= "txt" (-> % .getName FilenameUtils/getExtension))
        files (->> "./src/vocab" io/file .listFiles (filter txt-file?))
        reduce-file #(with-open [rdr (io/reader %)]
                       {:title (with-open [rdr (io/reader %)]
                                 (first (line-seq rdr)))
                        :vocab (with-open [rdr (io/reader %)]
                                 (reduce conj [] (rest (line-seq rdr))))})]
    (io/make-parents "./src/vocab/compiled_vocab.edn")
    (with-open [wrtr (io/writer "./src/vocab/compiled_vocab.edn")]
      (.write wrtr (pr-str (flatten (map reduce-file files)))))
    (prn (map :title (edn/read (java.io.PushbackReader. (io/reader "./src/vocab/compiled_vocab.edn")))))))
