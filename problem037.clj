;; Problem 37

;; The number 3797 has an interesting property. Being prime itself,
;; it is possible to continuously remove digits from left to right,
;; and remain prime at each stage: 3797, 797, 97, and 7.
;; Similarly we can work from right to left: 3797, 379, 37, and 3.

;; Find the sum of the only eleven primes that are both truncatable
;; from left to right and right to left.

;; NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

(ns problem037
  (:use [utils :only (primes-under number->digits digits->number)]))

(defn truncated-numbers [n]
  (map digits->number
       (let [v (vec (number->digits n))]
	 (lazy-cat
	   (take-while #(not (empty? %))
		       (rest (iterate rest v)))
	   (take-while #(not (empty? %))
		       (rest (iterate #(subvec % 0 (dec (count %))) v)))))))

(def primes
  (into (sorted-set)
	(primes-under 1000000)))

(defn solve []
  (reduce + (for [p (drop 4 (seq primes))
		  :when (every? primes (truncated-numbers p))]
	      p)))
