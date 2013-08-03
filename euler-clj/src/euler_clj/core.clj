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
    (/ (* n p (inc p)) 2)))

(defn problem-1-smart
  "Find the sum of all the multiples of 3 or 5 below n."
  [n]
  (- (+ (sum-divisible-by 3 (dec n ))
        (sum-divisible-by 5 (dec n)))
     (sum-divisible-by 15 (dec n))))

(defn- fib*
  "Calculate Fibonacci number Fn."
  [n p0 p1]
  (case n
    0 0N
    1 (bigint p1)
    (recur (dec n) (bigint p1) (bigint (+ p0 p1)))))

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
          (recur (dec p)))))))

(defn problem-3
  "Find the largest prime factor of n."
  [n]
  (loop [p (int (math/floor (math/sqrt n)))]
    (if (and (= (mod n p) 0)
             (or (odd? p)
                 (= p 2))
             (prime? p))
      p
      (recur (dec p)))))

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
  (first (reverse (sort (palindromes-for-problem-4)))))

(defn problem-5
  "Return the smallest integer evenly divisible by every integer from m to n."
  [m n]
  (first
    (for [x     (iterate (partial + n) n)
          :when (= (apply + (map #(mod x %) (range m n))) 0)]
      x)))

(defn squared-natural-numbers
  "Return a lazy sequence of the natural numbers squared."
  []
  (map #(* % %) (drop 1 (range))))

(defn problem-6
  "Return the difference between the sum of the squares of the first n natural numbers, and the square of the sum."
  [n]
  (let [sum-of-squares (apply + (take n (squared-natural-numbers)))
        sum            (apply + (take n (drop 1 (range))))
        square-of-sum  (* sum sum)]
    (math/abs (- square-of-sum sum-of-squares))))

(defn problem-6-smart
  "Return the difference between the sum of the squares of the first n natural numbers, and the square of the sum."
  [n]
  (let [m              (bigint n)
        sum            (/ (* m (inc m)) 2)
        sum-of-squares (/ (* (inc (* m 2)) (inc m) m) 6)]
    (math/abs (- (* sum sum) sum-of-squares))))

(defn problem-7
  "Return the nth prime number."
  [n]
  (first (drop (dec n) (filter prime? (drop 2 (range))))))

(defn -main
  [& args]
  (time (println "Problem 1: " (problem-1-smart 1000)))
  (time (println "Problem 2: " (problem-2-unfiltered 4000000)))
  (time (println "Problem 3: " (problem-3  600851475143)))
  (time (println "Problem 4: " (problem-4)))
  (time (println "Problem 5: " (problem-5 1 20)))
  (time (println "Problem 6: " (problem-6-smart 100)))
  (time (println "Problem 7: " (problem-7 10001))))
