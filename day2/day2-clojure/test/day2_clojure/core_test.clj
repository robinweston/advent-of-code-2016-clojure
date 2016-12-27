(ns day2-clojure.core-test
  (:require [clojure.test :refer :all]
            [day2-clojure.core :refer :all]))

(deftest finding-keys
  (testing "Simple moves"
    (is (= [2] (find-keys "U")))
    (is (= [4] (find-keys "L")))
    (is (= [6] (find-keys "R")))
    (is (= [8] (find-keys "D")))
    (is (= [1] (find-keys "UL")))
    (is (= [9] (find-keys "DR")))
    (is (= [2] (find-keys "UU"))))

    (testing "Multiple lines"
      (is (= [2 5] (find-keys "U\nD")))
      (is (= [1 9 8 5] (find-keys "ULL\nRRDDD\nLURDL\nUUUUD")))
  ))
