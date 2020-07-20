(ns app.helpers)

(defn in-coll? [item coll]
  (->> coll
       (some #(= item %))
       boolean))
