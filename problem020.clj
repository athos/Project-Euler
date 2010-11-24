;; Problem 20

;; n! means n × (n − 1) × ... × 3 × 2 × 1

;; Find the sum of the digits in the number 100!

(ns problem020
  (:use [utils :only (!)]))

(defn solve []
  (reduce + (map #(Character/digit % 10) (str (! 100)))))
