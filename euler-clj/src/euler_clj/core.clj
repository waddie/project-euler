(ns euler-clj.core)

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
  "Calculate the nth Fibonacci number."
  [n p0 p1]
  (if (= n 1)
    p1
    (recur (- n 1) (bigint p1) (bigint (+ p0 p1)))))

(defn fib
  "Calculate the nth Fibonacci number."
  [n]
  (if (= n 0)
    0
    (fib* n 0 1)))

(defn fib-seq
  "Return a lazy sequence of the Fibonacci numbers."
  []
  (map fib (range)))

(defn problem-2
  "Find the sum of the even terms Fibonacci sequence whose values are less than n."
  [n]
  (apply + (filter even? (take-while (partial > n) (fib-seq)))))

(defn stepped-fib-seq
  "Retur a lazy sequence of every nth Fibonacci numbers."
  [step]
  (map fib (iterate #(+ % step) step)))

(defn problem-2-unfiltered
  "Find the sum of the even terms Fibonacci sequence whose values are less than n."
  [n]
  (apply + (take-while (partial > n) (stepped-fib-seq 3))))
