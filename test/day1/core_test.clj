(ns day1.core-test
  (:require [clojure.test :refer :all]
            [day1.core :refer :all]))

(deftest path-moves

    (def input (slurp "resources/day1.txt"))

    (testing "shortest-distance"
        (is (= 5 (shortest-distance "R2, L3")))   
        (is (= 2 (shortest-distance "R2, R2, R2")))   
        (is (= 12 (shortest-distance "R5, L5, R5, R3")))   
        (is (= 0 (shortest-distance "L1, L1, L1, L1")))   
        (is (= 78 (shortest-distance "R78")))   
        (is (= 332 (shortest-distance input)))   
    ))