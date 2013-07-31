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
