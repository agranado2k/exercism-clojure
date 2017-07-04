(ns bob
    (:require
          [clojure.string :as str]))

(defn response-for 
  [question]
  (cond 
    (str/blank? question) "Fine. Be that way!"
    (and (re-find #"[A-Z]+" question) (= (str/upper-case question) question)) "Whoa, chill out!"
    (str/ends-with? question "?" ) "Sure."
    :else
    (str "Whatever.")  
  )
)
