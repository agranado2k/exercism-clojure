(ns anagram
  (:require [clojure.string :as string])
)

(defn s-sort [word]
  (string/join "" (map str (sort (seq (string/lower-case word)))))
)

(defn anagrams-for [anagram words]
  (let [sorted-anagram (s-sort anagram)]
    (filter
      #(and (= sorted-anagram (s-sort %))
            (not (= anagram %))
            (not (= anagram (string/upper-case %))))
      words)
  )
)
