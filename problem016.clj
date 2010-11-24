;; Problem 16

;; 2^(15) = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

;; What is the sum of the digits of the number 2^(1000)?

(ns problem016)

(defn solve []
  (reduce + (map #(Character/digit % 10)
		 (str (.pow (BigInteger/valueOf 2) 1000)))))
