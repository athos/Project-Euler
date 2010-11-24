;; Problem 19

;; You are given the following information, but you may prefer
;; to do some research for yourself.

;; * 1 Jan 1900 was a Monday.
;; * Thirty days has September,
;;   April, June and November.
;;   All the rest have thirty-one,
;;   Saving February alone,
;;   Which has twenty-eight, rain or shine.
;;   And on leap years, twenty-nine.
;; * A leap year occurs on any year evenly divisible by 4, but
;;   not on a century unless it is divisible by 400.

;; How many Sundays fell on the first of the month during
;; the twentieth century (1 Jan 1901 to 31 Dec 2000)?

(ns problem019
  (:use [utils :only (|)]))

(def num-days-of-month
  (sorted-map 0 31, 1 28, 2 31, 3 30, 4 31, 5 30,
	      6 31, 7 31, 8 30, 9 31, 10 30, 11 31))

(defn days-before-month [month]
  (loop [[[m ds] & rest] (seq num-days-of-month), days 0]
    (if (= month m)
      days
      (recur rest (+ days ds)))))

(defn leap-year? [year]
  (or (| year 400)
      (and (not (| year 100))
	   (| year 4))))

(defn num-of-leap-year-during [start end]
  (count (filter leap-year? (range start end))))

(defn num-of-days-before [year month date]
  (+ (* 365 (- year 1900))
     (days-before-month month)
     (num-of-leap-year-during 1900 year)
     date))

(defn day-of-date [year month date]
  (mod (num-of-days-before year month date) 7))

(defn num-of-days-during [y0 m0 d0 y1 m1 d1]
  (- (num-of-days-before y1 m1 d1)
     (num-of-days-before y0 m0 d0)
     -1))

;; FIXME
(defn num-of-sundays-during [y0 m0 d0 y1 m1 d1]
  (quot (- (num-of-days-during y0 m0 d0 y1 m1 d1)
	   (- 6 (day-of-date y0 m0 d0))
	   (- (day-of-date y1 m1 d1) 6))
	7))

(defn solve []
  (num-of-sundays-during 1901 0 0 2000 11 30))
