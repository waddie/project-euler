(ns euler-clj.core-test
  (:require [clojure.test :refer :all]
            [euler-clj.core :refer :all]))

(deftest test-problem-1
  (testing "Problem 1"
    (is (= (problem-1 10) 23))))

(deftest test-problem-1-smart
  (testing "Problem 1 – Smarter Solution"
    (is (= (problem-1-smart 10) 23))))
