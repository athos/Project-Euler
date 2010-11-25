;; Problem 36

;; The decimal number, 585 = 1001001001_(2) (binary), is palindromic
;; in both bases.

;; Find the sum of all numbers, less than one million, which are
;; palindromic in base 10 and base 2.

;; (Please note that the palindromic number, in either base,
;; may not include leading zeros.)

(ns problem036
  (:use [utils :only (palindromic?)]))

(defn solve []
  (reduce + (for [i (range 1 1000000)
		  :when (and (palindromic? i)
			     (palindromic? (Integer/toString i 2)))]
	      i)))
