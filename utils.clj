(ns utils)

(defn | [m n]
  (= (mod m n) 0))

(defn ceiling [n]
  (int (Math/ceil n)))

(defn primes-under [n]
  (letfn [(cleanup [t]
	    (loop [i 2, t t]
	      (cond (>= i n) t
		    (nth t i) (recur (inc i) (assoc! t i i))
		    :else (recur (inc i) t))))]
    (let [max (ceiling (Math/sqrt n))]
      (loop [i (int 2), t (transient (vec (repeat n true)))]
	(cond (> i max) (filter number? (persistent! (cleanup t)))
	      (not (nth t i)) (recur (inc i) t)
	      :else (letfn [(iter [t]
			      (loop [j (int (* 2 i)), t t]
				(if (< j n)
				  (recur (+ j i) (assoc! t j false))
				  t)))]
		      (recur (inc i) (iter t))))))))