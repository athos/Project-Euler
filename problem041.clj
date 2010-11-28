;; Problem 41

;; We shall say that an n-digit number is pandigital if it makes
;; use of all the digits 1 to n exactly once. For example,
;; 2143 is a 4-digit pandigital and is also prime.

;; What is the largest n-digit pandigital prime that exists?

(ns problem041
  (:use [utils :only (| ceiling primes-under digits->number permutations)]))

(defn pandigital? [ds n]
  (let [digits (set (range 1 (inc n)))]
    (loop [ds ds,
	   digits digits]
      (or (and (empty? digits)
	       (empty? ds))
	  (and (digits (first ds))
	       (recur (rest ds) (disj digits (first ds))))))))

(defn solve []
  (let [num 7
	primes (primes-under (ceiling (Math/sqrt (Math/pow 10 num))))]
    (first (for [ds (permutations (range 1 (inc num)) :desc)
		 :let [i (digits->number ds)]
		 :when (and (every? #(not (| i %)) primes)
			    (pandigital? ds num))]
	     i))))
