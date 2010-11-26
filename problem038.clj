;; Problem 38

;; Take the number 192 and multiply it by each of 1, 2, and 3:

;; 192 × 1 = 192
;; 192 × 2 = 384
;; 192 × 3 = 576

;; By concatenating each product we get the 1 to 9 pandigital,
;; 192384576. We will call 192384576 the concatenated product
;; of 192 and (1,2,3)

;; The same can be achieved by starting with 9 and multiplying
;; by 1, 2, 3, 4, and 5, giving the pandigital, 918273645,
;; which is the concatenated product of 9 and (1,2,3,4,5).

;; What is the largest 1 to 9 pandigital 9-digit number
;; that can be formed as the concatenated product of an integer
;; with (1,2, ... , n) where n > 1?

(ns problem038
  (:use [utils :only (number->digits)]))

(def all-digits
  #{1 2 3 4 5 6 7 8 9})

(defn multiples-of [n]
  (iterate #(+ % n) n))

(defn reduce-unused [unused xs]
  (reduce (fn [unused x]
	    (and unused
		 (unused x)
		 (disj unused x)))
	  unused
	  xs))

(defn pandigital-multiples [n]
  (loop [[m & ms] (multiples-of n)
	 unused all-digits
	 ret []]
    (if (empty? unused)
      ret
      (let [ds (number->digits m)]
	(if-let [unused* (reduce-unused unused ds)]
	  (recur ms unused* (into ret ds))
	  nil)))))

(defn digits->number [ds]
  (reduce #(+ (* 10 %1) %2) 0 ds))

(defn solve []
  (reduce max
	  (map #(digits->number (pandigital-multiples %))
	       (range 1 100000))))
