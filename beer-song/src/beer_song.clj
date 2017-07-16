(ns beer-song)

(defn verse [num_verse]
  (let [bottles num_verse
        next_bottles (- num_verse 1)]
    (cond
      (>= num_verse 3) (str bottles " bottles of beer on the wall, " bottles " bottles of beer.\n"
           "Take one down and pass it around, " next_bottles " bottles of beer on the wall.\n")
      (= num_verse 2) (str "2 bottles of beer on the wall, 2 bottles of beer.\n"
           "Take one down and pass it around, 1 bottle of beer on the wall.\n")
      (= num_verse 1) (str "1 bottle of beer on the wall, 1 bottle of beer.\n"
           "Take it down and pass it around, no more bottles of beer on the wall.\n")
      (= num_verse 0) (str "No more bottles of beer on the wall, no more bottles of beer.\n"
           "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
    )
  )
)

(defn sing [ & verses]
  (let [start (inc (first verses))
        end (or (second verses) 0)]
    (clojure.string/join "\n" (map verse (reverse (range end start))))
  )
)
