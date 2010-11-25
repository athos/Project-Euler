;; Problem 35

;; The number, 197, is called a circular prime because all
;; rotations of the digits: 197, 971, and 719, are themselves prime.

;; There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13,
;; 17, 31, 37, 71, 73, 79, and 97.

;; How many circular primes are there below one million?

(ns problem035
  (:use [utils :only (primes-under number->digits)]))

(def primes
  (into (sorted-set)
	(remove (fn [p]
		  (and (not= p 2)
		       (some even? (number->digits p))))
		(primes-under 1000000))))

(defn rotations [xs]
  (let [v (vec xs)]
    (loop [i (count v), v v, acc []]
      (if (= i 0)
	acc
	(recur (dec i)
	       (conj (subvec v 1) (first v))
	       (conj acc v))))))

(defn circular-prime? [p]
  (every? (fn [ds] (primes (reduce #(+ (* 10 %1) %2) ds)))
	  (rotations (number->digits p))))

(defn solve []
  (count (for [p (seq primes)
	       :when (circular-prime? p)]
	   p)))
