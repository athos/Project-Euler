;; Problem 28

;; Starting with the number 1 and moving to the right in a clockwise
;; direction a 5 by 5 spiral is formed as follows:

;; 21 22 23 24 25
;; 20  7  8  9 10
;; 19  6  1  2 11
;; 18  5  4  3 12
;; 17 16 15 14 13

;; It can be verified that the sum of the numbers on the diagonals is 101.

;; What is the sum of the numbers on the diagonals in a 1001 by 1001
;; spiral formed in the same way?

(ns problem028
  (:use [utils :only (|)]))

(defn numbers-on-diagonals []
  (letfn [(rec [i v d]
	    (lazy-seq
		(cons v (rec (inc i)
			     (+ v d)
			     (if (| i 4) (+ d 2) d)))))]
    (rec 1 1 2)))

(defn solve []
  (reduce + (take-while #(<= % (* 1001 1001))
			(numbers-on-diagonals))))
