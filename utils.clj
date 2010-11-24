(ns utils)

(defn | [m n]
  (= (mod m n) 0))

(defn ceiling [n]
  (int (Math/ceil n)))

(defn primes-under [n]
  (let [n* (ceiling (/ n 2))]
    (letfn [(val-at [i]
	      (+ (* 2 i) 3))
	    (cleanup [t]
	      (loop [i 0, t t]
		(cond (>= i n*) t
		      (nth t i) (recur (inc i) (assoc! t i (val-at i)))
		      :else (recur (inc i) t))))]
      (let [max (ceiling (Math/sqrt n*))]
	(loop [i (int 0), t (transient (vec (repeat n* true)))]
	  (cond (> i max) (cons 2 (filter number? (persistent! (cleanup t))))
		(not (nth t i)) (recur (inc i) t)
		:else (letfn [(iter [t]
				(loop [j (int (+ i (val-at i))), t t]
				  (if (< j n*)
				    (recur (+ j (int (val-at i)))
					   (assoc! t j false))
				    t)))]
			(recur (inc i) (iter t)))))))))

(defn gcd [m n]
  (let [r (mod m n)]
    (if (= r 0)
      n
      (recur n r))))

(defn ! [n]
  (reduce * (range 1 (inc n))))

(defn char->integer [c]
  (Character/digit c 10))

(defn divisors-of [n]
  (let [n* (int (ceiling (Math/sqrt n)))]
    (loop [d (int 1), ds []]
      (cond (> d n*) ds
	    (| n d)
	    (if (= d n*)
	      (recur (inc d) (conj ds d))
	      (recur (inc d) (conj ds (/ n d) d)))
	    :else (recur (inc d) ds)))))

(defn sum-of-proper-divisors [n]
  (reduce + (rest (divisors-of n))))
