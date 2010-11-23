;; Problem 5

;; 2520 is the smallest number that can be divided by
;; each of the numbers from 1 to 10 without any remainder.

;; What is the smallest positive number that is evenly
;; divisible by all of the numbers from 1 to 20?

(ns problem005
  (:use [utils :only (gcd)]))

(defn solve []
  (reduce (fn [p n]
	    (let [g (gcd p n)]
	      (* p (/ n g))))
	  (range 1 21)))
