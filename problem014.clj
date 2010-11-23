;; Problem 14

;; The following iterative sequence is defined for the set of
;; positive integers:

;; n → n/2 (n is even)
;; n → 3n + 1 (n is odd)

;; Using the rule above and starting with 13, we generate
;; the following sequence:

;; 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1

;; It can be seen that this sequence (starting at 13 and
;; finishing at 1) contains 10 terms. Although it has not been
;; proved yet (Collatz Problem), it is thought that all
;; starting numbers finish at 1.

;; Which starting number, under one million, produces the longest chain?

;; NOTE: Once the chain starts the terms are allowed to go above one million.

(ns problem014)

(defn collatz-step [n]
  (loop [n n, step (int 0)]
    (cond (= n 1) step
	  (even? n) (recur (/ n 2) (inc step))
	  :else     (recur (+ (* 3 n) 1) (inc step)))))

(defn solve []
  (second (reduce #(if (> (first %1) (first %2)) %1 %2)
		  (map (fn [n] [(collatz-step n) n])
		       (range 1 1000000)))))
