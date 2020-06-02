(ns app.compile-vocab
  (:require [clojure.java.io :as io]))

(defn compile-vocab []
  (let [files (-> "./src/vocab" io/file .listFiles)
        reduce-file #(with-open [rdr (io/reader %)]
                       (reduce conj [] (line-seq rdr)))]
    (prn (flatten (map reduce-file files)))))
