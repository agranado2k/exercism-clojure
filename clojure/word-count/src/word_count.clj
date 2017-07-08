(ns word-count
  (:require [clojure.string :as string])
  )

(defn recreate-r
  [r w]
  (assoc r w (inc (or (get r w) 0)))
)

(defn word-count
  [phrase]
   (let [words (re-seq #"\w+|\d+" (string/lower-case phrase))
         result {}
         ]
      (loop [w words r result]
        (if (empty? w)
          r
          (recur (rest w) (recreate-r r (first w)))
        )
      )
    )
)
