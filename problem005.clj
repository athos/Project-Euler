;; Problem 5

;; 2520 is the smallest number that can be divided by
;; each of the numbers from 1 to 10 without any remainder.

;; What is the smallest positive number that is evenly
;; divisible by all of the numbers from 1 to 20?

(ns problem005)

(defn gcd [m n]
  (let [r (mod m n)]
    (if (= r 0)
      n
      (recur n r))))

(defn solve []
  (reduce (fn [p n]
	    (let [g (gcd p n)]
	      (* p (/ n g))))
	  (range 1 21)))
