(ns word-count
  (:require [clojure.string :as string])
  )

(defn word-count
  [phrase]
  (frequencies
     (re-seq #"\w+|\d+" (string/lower-case phrase))
  )
)
