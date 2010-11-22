;; Problem 4

;; A palindromic number reads the same both ways.
;; The largest palindrome made from the product of
;; two 2-digit numbers is 9009 = 91 × 99.

;; Find the largest palindrome made from the product
;; of two 3-digit numbers.

(ns problem004)

(defn palindromic? [n]
  (let [s (str n)]
    (= (seq s) (reverse s))))

(defn solve []
  (let [xs (range 999 99 -1)]
    (reduce max (for [m xs, n xs
		      :let [m*n (* m n)]
		      :when (palindromic? m*n)]
		  m*n))))
