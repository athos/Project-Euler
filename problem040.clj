;; Problem 40

;; An irrational decimal fraction is created by concatenating
;; the positive integers:

;; 0.123456789101112131415161718192021...

;; It can be seen that the 12^(th) digit of the fractional part is 1.

;; If d_(n) represents the n^(th) digit of the fractional part,
;; find the value of the following expression.

;; d_(1) × d_(10) × d_(100) × d_(1000) × d_(10000) × d_(100000) × d_(1000000)

(ns problem040
  (:use [utils :only (number->digits)]))

(def target-numbers
  [1 10 100 1000 10000 100000 1000000])

(defn digits [i]
  (lazy-cat (number->digits i)
	    (digits (inc i))))

(defn solve []
  (loop [i 1
	 [d & ds] (digits 1)
	 [n & ns :as nns] target-numbers
	 p 1]
    (cond (empty? ns) p
	  (= i n) (recur (inc i) ds ns (* d p))
	  :else (recur (inc i) ds nns p))))
