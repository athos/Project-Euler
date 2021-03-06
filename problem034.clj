;; Problem 34

;; 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

;; Find the sum of all numbers which are equal to the sum of
;; the factorial of their digits.

;; Note: as 1! = 1 and 2! = 2 are not sums they are not included.

(ns problem034
  (:use [utils :only (! number->digits)]))

(defn solve []
  (reduce + (for [i (range 3 10000000)
		  :when (= i (reduce + (map ! (number->digits i))))]
	      i)))
