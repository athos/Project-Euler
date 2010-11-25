;; Problem 32

;; We shall say that an n-digit number is pandigital if it makes
;; use of all the digits 1 to n exactly once; for example,
;; the 5-digit number, 15234, is 1 through 5 pandigital.

;; The product 7254 is unusual, as the identity, 39 × 186 = 7254,
;; containing multiplicand, multiplier, and product is 1 through 9 pandigital.

;; Find the sum of all products whose multiplicand/multiplier/product
;; identity can be written as a 1 through 9 pandigital.

;; HINT: Some products can be obtained in more than one way so be
;; sure to only include it once in your sum.

(ns problem032
  (:use [utils :only (number->digits divisors-of)]))

(def all-digits #{1 2 3 4 5 6 7 8 9})

(defn pandigital? [x y z]
  (empty? (apply disj
		 all-digits
		 (concat (number->digits x)
			 (number->digits y)
			 (number->digits z)))))

(defn find-all-products []
  (distinct (for [i (range 1000 10000)
		  d0 (divisors-of i)
		  :let [d1 (/ i d0)]
		  :when (and (or (and (<= 10 d0 99) (<= 100 d1 999))
				 (and (<= 1 d0 9) (<= 1000 d1 9999)))
			     (pandigital? i d0 d1))]
	      i)))

(defn solve []
  (reduce + (find-all-products)))
