;; Problem 17

;; If the numbers 1 to 5 are written out in words: one, two,
;; three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19
;; letters used in total.

;; If all the numbers from 1 to 1000 (one thousand) inclusive
;; were written out in words, how many letters would be used?

;; NOTE: Do not count spaces or hyphens. For example, 342
;; (three hundred and forty-two) contains 23 letters and
;; 115 (one hundred and fifteen) contains 20 letters.
;; The use of "and" when writing out numbers is in compliance
;; with British usage.

(ns problem017
  (:use [clojure.contrib.string :only (join)]))

(def number->word
  (sorted-map
    1 "one", 2 "two", 3 "three", 4 "four", 5 "five",
    6 "six", 7 "seven", 8 "eight", 9 "nine", 10 "ten",
    11 "eleven", 12 "twelve", 13 "thirteen", 14 "fourteen", 15 "fifteen",
    16 "sixteen", 17 "seventeen", 18 "eighteen", 19 "nineteen", 20 "twenty",
    30 "thirty", 40 "forty", 50 "fifty", 60 "sixty", 70 "seventy",
    80 "eighty", 90 "ninety", 100 "hundred", 1000 "thousand"))

(defn number->words [n]
  (loop [n n, [[m w] & ws] (rseq number->word), ret []]
    (cond (= n 0) (join " " ret)
	  (>= n m)
	  (if (or (= m 1000) (= m 100))
	    (recur (mod n m) ws
		   (conj ret
			 (number->word (quot n m))
			 (number->word m)
			 (if (and (= m 100) (not= (mod n m) 0))
			   "and"
			   "")))
	    (recur (- n m) ws (conj ret (number->word m))))
	  :else (recur n ws ret))))

(defn solve []
  (reduce + (map #(count (remove #{\space \-} (number->words %)))
		 (range 1 1001))))
