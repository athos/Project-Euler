;; Problem 20

;; n! means n × (n − 1) × ... × 3 × 2 × 1

;; Find the sum of the digits in the number 100!

(ns problem020
  (:use [utils :only (! digit->integer)]))

(defn solve []
  (reduce + (map digit->integer (str (! 100)))))
