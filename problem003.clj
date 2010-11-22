;; Problem 3

;; The prime factors of 13195 are 5, 7, 13 and 29.

;; What is the largest prime factor of the number 600851475143 ?

(ns problem003)

(defn | [m n]
  (= (mod m n) 0))

(defn divide* [m n]
  (loop [m m]
    (if (| m n)
      (/ m n)
      m)))

(defn solve
  ([] (solve 600851475143))
  ([m]
   (loop [i 3, n m, ret 1]
     (println i n ret)
     (cond (> i m) ret
	   (| n i) (recur (+ i 2) (divide* n i) i)
	   :else (recur (+ i 2) n ret)))))
