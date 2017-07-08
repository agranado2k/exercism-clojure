(ns word-count
  (:require [clojure.string :as string])
  )

(defn recreate-r
  [r w]
  (if (nil? (r w))
    (assoc r w 1)
    (assoc r w (inc (r w)))
  ))

(defn word-count
  [phrase]
   (let [words (string/split
                (string/lower-case
                   (string/replace phrase #"[^\d\w\s]" "")) #"\s+")
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
