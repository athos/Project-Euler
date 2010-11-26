;; Problem 39

;; If p is the perimeter of a right angle triangle with integral
;; length sides, {a,b,c}, there are exactly three solutions for p = 120.

;; {20,48,52}, {24,45,51}, {30,40,50}

;; For which value of p ≤ 1000, is the number of solutions maximised?

(ns problem039
  (:use [utils :only (ceiling)]))

(defn solutions-for [p]
  (let [m (ceiling (/ p 3))]
    (for [c (range m (- p 3))
	  b (range m c)
	  :let [a (- p c b)]
	  :when (and (< 0 a b c)
		     (= (+ (* a a) (* b b)) (* c c)))]
      [a b c])))

(defn find-max-between [start end]
  (reduce (fn [[p c :as x] [p* c* :as x*]]
	    (if (> c* c) x* x))
	  (map (fn [p]
		 [p (count (solutions-for p))])
	       (range start end))))

(defn solve []
  (let [x (future (find-max-between 1 800))
	y (future (find-max-between 800 1001))
	[p0 c0] @x
	[p1 c1] @y]
    (if (>= c0 c1)
      p0
      p1)))
