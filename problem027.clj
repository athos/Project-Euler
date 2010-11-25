;; Problem 27

;; Euler published the remarkable quadratic formula:

;; n² + n + 41

;; It turns out that the formula will produce 40 primes for
;; the consecutive values n = 0 to 39. However, when n = 40,
;; 40^(2) + 40 + 41 = 40(40 + 1) + 41 is divisible by 41,
;; and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.

;; Using computers, the incredible formula  n² − 79n + 1601 was
;; discovered, which produces 80 primes for the consecutive values
;; n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.

;; Considering quadratics of the form:

;; n² + an + b, where |a| < 1000 and |b| < 1000

;; where |n| is the modulus/absolute value of n
;; e.g. |11| = 11 and |−4| = 4

;; Find the product of the coefficients, a and b, for the quadratic
;; expression that produces the maximum number of primes for consecutive
;; values of n, starting with n = 0.

(ns problem027
  (:use [utils :only (primes-under)]))

(def primes
  (into (sorted-set)
	(primes-under 1000000)))

(defn primes-generated-from [a b]
  (letfn [(step [i]
	    (lazy-seq
	      (cons (+ (* i i) (* a i) b)
		    (step (inc i)))))]
    (take-while primes (step 0))))

(defn solve []
  (reduce (fn [[v a b :as x] [v* a* b* :as x*]]
	    (if (> v* v) x* x))
	  (for [a (range -999 1000),
		b (range -999 1000)]
	    [(count (primes-generated-from a b)) a b])))
