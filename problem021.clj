;; Problem 21

;; Let d(n) be defined as the sum of proper divisors of n
;; (numbers less than n which divide evenly into n).
;; If d(a) = b and d(b) = a, where a ≠ b, then a and b are
;; an amicable pair and each of a and b are called amicable numbers.

;; For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11,
;; 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper
;; divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

;; Evaluate the sum of all the amicable numbers under 10000.

(ns problem021
  (:use [utils :only (divisors-of)]))

(defn sum-of-proper-divisors [n]
  (reduce + (rest (divisors-of n))))

(defn amicable-numbers-under [max]
  (loop [i 1, as #{}]
    (cond (>= i max) as
	  (as i) (recur (inc i) as)
	  :else (let [n (sum-of-proper-divisors i)
		      m (sum-of-proper-divisors n)]
		  (if (and (= m i) (not= i n) (< n max))
		    (recur (inc i) (conj as i n))
		    (recur (inc i) as))))))

(defn solve []
  (reduce + (amicable-numbers-under 10000)))
