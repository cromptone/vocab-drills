(ns app.compile-vocab
  (:require [clojure.java.io :as io])
  (:import  [org.apache.commons.io FilenameUtils]))

(defn compile-vocab []
  (let [files (-> "./src/vocab" io/file .listFiles)
        reduce-file #(with-open [rdr (io/reader %)]
                       (reduce conj [] (line-seq rdr)))]
    (io/make-parents "./src/vocab/compiled_vocab.edn")
    (prn (map #(FilenameUtils/getExtension (.getName %)) files))
    (with-open [wrtr (io/writer "./src/vocab/compiled_vocab.edn")]
      (.write wrtr (pr-str (flatten (map reduce-file files)))))))
