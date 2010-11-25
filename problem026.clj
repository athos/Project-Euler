;; Problem 26

;; A unit fraction contains 1 in the numerator. The decimal
;; representation of the unit fractions with denominators 2
;; to 10 are given:

;; ^(1)/_(2)= 0.5
;; ^(1)/_(3)= 0.(3)
;; ^(1)/_(4)= 0.25
;; ^(1)/_(5)= 0.2
;; ^(1)/_(6)= 0.1(6)
;; ^(1)/_(7)= 0.(142857)
;; ^(1)/_(8)= 0.125
;; ^(1)/_(9)= 0.(1)
;; ^(1)/_(10)= 0.1

;; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle.
;; It can be seen that ^(1)/_(7) has a 6-digit recurring cycle.

;; Find the value of d < 1000 for which ^(1)/_(d) contains the longest
;; recurring cycle in its decimal fraction part.

(ns problem026)

(defn quotient-sequence [n d]
  (loop [r n, t (hash-map)]
    (if (t r) t
	(let [r* (* 10 (mod r d))]
	  (recur r* (assoc t r [(quot r d) r*]))))))

(defn recurring-cycle [n d]
  (let [qseq (quotient-sequence n d)]
    (letfn [(seq-start-with [n]
	      (let [[q i] (qseq n)]
		(loop [i i, s [q]]
		  (if (= i n)
		    s
		    (let [[q* i*] (qseq i)]
		      (recur i* (conj s q*)))))))]
      (loop [n n, visited #{}]
	(if (visited n)
	  (seq-start-with n)
	  (let [[_ n*] (qseq n)]
	    (recur n* (conj visited n))))))))

(defn solve []
  (reduce (fn [[mn ml :as m] [xn xl :as x]]
	    (if (> xl ml) x m))
	  (for [i (range 2 1000)]
	    [i (count (recurring-cycle 1 i))])))
