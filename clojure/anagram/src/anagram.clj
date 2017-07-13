(ns anagram
  (:require [clojure.string :as string])
)

(defn s-sort
  "Sord string"
  [word]
  (string/join "" (map str (sort (seq (string/lower-case word)))))
)

(defn anagram?
  "Check if candidate is angram with word"
  [word candidate]
  (= word (s-sort candidate))
)

(defn not-same-word?
  "Check if candidate is not the same word that word"
  [word candidate]
  (not (= word candidate))
)

(defn not-capital-word?
  "Check if candidate is not capital word"
  [word candidate]
  (not (= word (string/upper-case candidate)))
)

(defn anagrams-for
  "Return words list from words that are anagram with anagram"
  [anagram words]
  (let [sorted-anagram (s-sort anagram)]
    (filter
      #(and (anagram? sorted-anagram %)
            (not-same-word? anagram %)
            (not-capital-word? anagram %))
      words)
  )
)
