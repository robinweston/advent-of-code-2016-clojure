(ns day3.core-test
  (:require [clojure.test :refer :all]
            [day3.core :refer :all]))

(def test-input (slurp "resources/day3.txt"))

(deftest count-possible-triangles
  (testing "Simple examples"
    (is (= 0 (count-triangles "1 2 3")))
    (is (= 0 (count-triangles "3 2 1")))
    (is (= 1 (count-triangles "1 1 1")))
  )
  (testing "Complex examples"
      (is (= 1 (count-triangles "1 2 3\n1 1 1")))
      (is (= 1 (count-triangles "  541  588  421")))
      (is (= 993 (count-triangles test-input)))
      ))