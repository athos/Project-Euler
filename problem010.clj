;; Problem 10

;; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

;; Find the sum of all the primes below two million.

(ns problem010
  (:use [utils :only (primes-under |)]))

(defn solve []
  (reduce + (primes-under 2000000)))
