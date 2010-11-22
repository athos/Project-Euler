;; Problem 1

;; If we list all the natural numbers below 10 that are
;; multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of
;; these multiples is 23.

;; Find the sum of all the multiples of 3 or 5 below 1000.

(ns problem001)

(defn multiples-under [m n]
  (filter #(= (mod % n) 0) (range m)))

(defn solve []
  (reduce + (into (into #{}
			(multiples-under 1000 3))
		  (multiples-under 1000 5))))
