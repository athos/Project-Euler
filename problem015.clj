;; Problem 15

;; Starting in the top left corner of a 2×2 grid, there are
;; 6 routes (without backtracking) to the bottom right corner.

;; How many routes are there through a 20×20 grid?

(ns problem015)

(defn ! [n]
  (reduce * (range 1 (inc n))))

(defn C [m n]
  (/ (! m) (! n) (! (- m n))))

(defn solve []
  (C 40 20))
