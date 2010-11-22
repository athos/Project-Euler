;; Problem 7

;; By listing the first six prime numbers:
;; 2, 3, 5, 7, 11, and 13, we can see that the 6^(th) prime is 13.

;; What is the 10001^(st) prime number?

(ns problem007)

(defn primes-under [n]
  (letfn [(cleanup [t]
	    (loop [i 2, t t]
	      (cond (>= i n) t
		    (nth t i) (recur (inc i) (assoc! t i i))
		    :else (recur (inc i) t))))]
    (let [max (int (Math/ceil (Math/sqrt n)))]
      (loop [i (int 2), t (transient (vec (repeat n true)))]
	(cond (> i max) (filter number? (persistent! (cleanup t)))
	      (not (nth t i)) (recur (inc i) t)
	      :else (letfn [(iter [t]
			      (loop [j (int (* 2 i)), t t]
				(if (< j n)
				  (recur (+ j i) (assoc! t j false))
				  t)))]
		      (recur (inc i) (iter t))))))))

(defn solve []
  (nth (primes-under 1000000) 10000))
