;; Problem 6

;; The sum of the squares of the first ten natural numbers is,
;; 1^(2) + 2^(2) + ... + 10^(2) = 385

;; The square of the sum of the first ten natural numbers is,
;; (1 + 2 + ... + 10)^(2) = 55^(2) = 3025

;; Hence the difference between the sum of the squares of
;; the first ten natural numbers and the square of the sum
;; is 3025 − 385 = 2640.

;; Find the difference between the sum of the squares of
;; the first one hundred natural numbers and the square of the sum.

(ns problem006)

(defn square-of-sum [n]
  (/ (* n n (inc n) (inc n)) 4))

(defn sum-of-squares [n]
  (/ (* n (inc n) (+ (* 2 n) 1)) 6))

(defn solve []
  (let [n 100]
    (- (square-of-sum n)
       (sum-of-squares n))))
