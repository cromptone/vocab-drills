(ns app.compile-vocab
  (:require [clojure.java.io :as io]))

(defn compile-vocab []
  (let [files (-> "./src/vocab" io/file .listFiles)
        reduce-file #(with-open [rdr (io/reader %)]
                       (reduce conj [] (line-seq rdr)))]
    (io/make-parents "./src/vocab/compiled_vocab.edn")
    (with-open [wrtr (io/writer "./src/vocab/compiled_vocab.edn")]
      (.write wrtr (pr-str (flatten (map reduce-file files)))))))
