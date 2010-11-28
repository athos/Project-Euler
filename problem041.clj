;; Problem 41

;; We shall say that an n-digit number is pandigital if it makes
;; use of all the digits 1 to n exactly once. For example,
;; 2143 is a 4-digit pandigital and is also prime.

;; What is the largest n-digit pandigital prime that exists?

(ns problem041
  (:use [utils :only (| ceiling primes-under number->digits)]))

(defn pandigital? [m n]
  (let [digits (set (range 1 (inc n)))]
    (loop [ds (number->digits m),
	   digits digits]
      (or (and (empty? digits)
	       (empty? ds))
	  (and (digits (first ds))
	       (recur (rest ds) (disj digits (first ds))))))))

(defn solve []
  (let [n 10000000
	num 7
	primes (primes-under (ceiling (Math/sqrt n)))]
    (first (for [i (range n (/ n 10) -1)
		 :when (and (every? #(not (| i %)) primes)
			    (pandigital? i num))]
	     i))))
