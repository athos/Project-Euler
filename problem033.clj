;; Problem 33

;; The fraction ^(49)/_(98) is a curious fraction, as an inexperienced
;; mathematician in attempting to simplify it may incorrectly
;; believe that ^(49)/_(98) = ^(4)/_(8), which is correct,
;; is obtained by cancelling the 9s.

;; We shall consider fractions like, ^(30)/_(50) = ^(3)/_(5),
;; to be trivial examples.

;; There are exactly four non-trivial examples of this type
;; of fraction, less than one in value, and containing two
;; digits in the numerator and denominator.

;; If the product of these four fractions is given in its
;; lowest common terms, find the value of the denominator.

(ns problem033
  (:use [utils :only (number->digits)]))

(defn unorthodox-cancelling [[n d]]
  (let [[n0 n1] (number->digits n)
	[d0 d1] (number->digits d)]
    (letfn [(f [x y v w]
	      (if (and (= x y) (not= x 0) (not= w 0))
		[[v w]] nil))]
      (distinct (concat (f n0 d0 n1 d1)
			(f n0 d1 n1 d0)
			(f n1 d0 n0 d1)
			(f n1 d1 n0 d0))))))

(defn solve []
  (.denominator
    (reduce * (for [n (range 10 100)
		    d (range (inc n) 100)
		    [n* d*] (unorthodox-cancelling [n d])
		    :when (= (/ n d) (/ n* d*))]
		(/ n d)))))
