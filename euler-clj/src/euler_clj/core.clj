(ns euler-clj.core
  (:require [clojure.math.numeric-tower :as math]))

(defn problem-1
  "Find the sum of all the multiples of 3 or 5 below n."
  [n]
  (apply + (filter #(or (= (mod % 3) 0)
                        (= (mod % 5) 0)) (range 1 n))))

(defn sum-divisible-by
  "Find the sum of all the multiples of n below target."
  [n target]
  (let [p (quot target n)]
    (/ (* n p (+ p 1)) 2)))

(defn problem-1-smart
  "Find the sum of all the multiples of 3 or 5 below n."
  [n]
  (- (+ (sum-divisible-by 3 (- n 1))
        (sum-divisible-by 5 (- n 1)))
     (sum-divisible-by 15 (- n 1))))

(defn- fib*
  "Calculate Fibonacci number Fn."
  [n p0 p1]
  (case n
    0 0N
    1 (bigint p1)
    (recur (- n 1) (bigint p1) (bigint (+ p0 p1)))))

(defn fib
  "Calculate Fibonacci number Fn."
  [n]
  (fib* n 0 1))

(defn fib-seq
  "Return a lazy sequence of the Fibonacci numbers."
  []
  (map fib (range)))

(defn problem-2
  "Find the sum of the even terms Fibonacci sequence whose values are less than n."
  [n]
  (apply + (filter even? (take-while (partial > n) (fib-seq)))))

(defn stepped-fib-seq
  "Return a lazy sequence of every nth Fibonacci number."
  [step]
  (map fib (iterate #(+ % step) step)))

(defn problem-2-unfiltered
  "Find the sum of the even terms Fibonacci sequence whose values are less than n."
  [n]
  (apply + (take-while (partial > n) (stepped-fib-seq 3))))

(defn prime?
  "Test if n is a prime number."
  [n]
  (loop [p (int (math/floor (math/sqrt n)))]
    (if (= p 1)
      true
      (if (= (mod n p) 0)
        false
        (recur (- p 1))))))

(defn problem-3
  "Find the largest prime factor of n."
  [n]
  (loop [p (int (math/floor (math/sqrt n)))]
    (if (and (= (mod n p) 0)
             (prime? p))
      p
      (recur (- p 1)))))
