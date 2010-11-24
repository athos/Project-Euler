;; Problem 22

;; Using names.txt (right click and 'Save Link/Target As...'),
;; a 46K text file containing over five-thousand first names,
;; begin by sorting it into alphabetical order. Then working out
;; the alphabetical value for each name, multiply this value
;; by its alphabetical position in the list to obtain a name score.

;; For example, when the list is sorted into alphabetical order,
;; COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th
;; name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.

;; What is the total of all the name scores in the file?

(ns problem022
  (:use [clojure.contrib.io :only (reader)])
  (:import java.io.PushbackReader))

(def the-filename "names.txt")

(defn names []
  (let [r (PushbackReader. (reader the-filename))]
    (loop [ns (sorted-set)]
      (if-let [name (read r false nil)]
	(recur (conj ns name))
	(do (.close r)
	    ns)))))

(defn alphabetical-value [word]
  (let [base (dec (int \A))]
    (reduce + (map #(- (int %) base) word))))

(defn solve []
 (loop [pos 1, [name & names] (seq (names)), total 0]
   (if (nil? name)
     total
     (recur (inc pos)
	    names
	    (+ (* pos (alphabetical-value name))
	       total)))))
