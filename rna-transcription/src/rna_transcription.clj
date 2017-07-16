(ns rna-transcription
  (require [clojure.string :as string :refer[join]])
  )

(defn dna-n-to-rna-n
  [nucleotide]
    (cond
      (= nucleotide "C") "G"
      (= nucleotide "G") "C"
      (= nucleotide "A") "U"
      (= nucleotide "T") "A"
      :else
      (throw (AssertionError. "Assertion violation"))
    )
  )

(defn to-rna
  [dna]
    (string/join "" (map dna-n-to-rna-n (map str (seq dna))))
    )
