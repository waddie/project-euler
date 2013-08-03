(ns euler-clj.core
  (:require [clojure.math.numeric-tower :as math]
            [clojure.string :as string])
  (:gen-class))

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
  (case n
    2 true
    even? false
    (loop [p (int (math/floor (math/sqrt n)))]
      (if (= p 1)
        true
        (if (= (mod n p) 0)
          false
          (recur (- p 1)))))))

(defn problem-3
  "Find the largest prime factor of n."
  [n]
  (loop [p (int (math/floor (math/sqrt n)))]
    (if (and (= (mod n p) 0)
             (or (odd? p)
                 (= p 2))
             (prime? p))
      p
      (recur (- p 1)))))

(defn palindromes-for-problem-4
  "Return palindromes made from the product of two 3-digit numbers."
  []
  (for [x (range 100 1000)
        y (range 100 1000)
        :let  [z (* x y)]
        :when (= (str z) (string/reverse (str z)))]
    z))

(defn problem-4
  "Find the largest palindrome made from the product of two 3-digit numbers."
  []
  (take 1 (reverse (sort (palindromes-for-problem-4)))))

(defn -main
  [& args]
  (time (println "Problem 1: " (problem-1-smart 1000)))
  (time (println "Problem 2: " (problem-2-unfiltered 4000000)))
  (time (println "Problem 3: " (problem-3  600851475143)))
  (time (println "Problem 4: " (problem-4))))
