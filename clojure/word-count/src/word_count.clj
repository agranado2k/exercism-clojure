(ns word-count
  (:require [clojure.string :as string])
  )

(defn word-count
  [phrase]
  (reduce
     #(assoc %1 %2 (inc (or (get %1 %2) 0)))
     {}
     (re-seq #"\w+|\d+" (string/lower-case phrase))
  )
)
