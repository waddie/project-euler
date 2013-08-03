(ns euler-clj.core-test
  (:require [clojure.test :refer :all]
            [euler-clj.core :refer :all]
            [clojure.math.numeric-tower :as math]))

(deftest test-problem-1
  (testing "problem-1 for n = 10"
    (is (= (problem-1 10) 23))))

(deftest test-sum-divisible-by
  (testing "sum-divisible-by for n = 3, target = 10"
    (is (= (sum-divisible-by 3 10) 18)))
  (testing "problem-1-smart for n = 10"
    (is (= (problem-1-smart 10) 23))))

(deftest test-fib
  (testing "fib for the 500th Fibonacci number"
    (is (= (fib 500) 139423224561697880139724382870407283950070256587697307264108962948325571622863290691557658876222521294125))))

(deftest test-problem-2
  (testing "problem-2 for n = 90")
    (is (= (problem-2 90) 44))
  (testing "problem-2-unfiltered for n = 90")
    (is (= (problem-2-unfiltered 90) 44)))

(deftest test-problem-3
  (testing "problem-3 for n = 13195")
    (is (= (problem-3 13195) 29))
  (testing "problem-3 for n = 7")
    (is (= (problem-3 7) 1)))

(deftest test-problem-5
  (testing "problem-5 for m = 1, n = 10")
    (is (= (problem-5 1 10) 2520)))

(deftest test-problem-6
  (testing "problem-6 for n = 10")
    (is (= (problem-6 10) 2640))
  (testing "problem-6-smart for n = 10")
    (is (= (problem-6-smart 10) 2640)))

(deftest test-problem-7
  (testing "problem-7 for n = 6")
    (is (= (problem-7 6) 13)))
