;; Problem 3

;; The prime factors of 13195 are 5, 7, 13 and 29.

;; What is the largest prime factor of the number 600851475143 ?

(ns problem003
  (:use [utils :only (primes-under ceiling |)]))

(defn divide* [m n]
  (loop [m m]
    (if (| m n)
      (recur (/ m n))
      m)))

(defn solve
  ([] (solve 600851475143))
  ([m]
   (loop [[p & ps :as pps] (primes-under (ceiling (Math/sqrt m))),
	  m m
	  ret 1]
     (cond (= m 1) ret
	   (empty? pps) (max m ret)
	   (| m p) (recur ps (divide* m p) p)
	   :else (recur ps m ret)))))
