(ns day2.core-test
  (:require [clojure.test :refer :all]
            [day2.core :refer :all]))

(def puzzle-input (slurp "resources/day2.txt"))

(deftest finding-keys-in-9-by-9-pad
  (testing "Simple moves"
    (is (= [2] (find-keys-part-1 "U")))
    (is (= [4] (find-keys-part-1 "L")))
    (is (= [6] (find-keys-part-1 "R")))
    (is (= [8] (find-keys-part-1 "D")))
    (is (= [1] (find-keys-part-1 "UL")))
    (is (= [9] (find-keys-part-1 "DR")))
    (is (= [2] (find-keys-part-1 "UU"))))

    (testing "Multiple lines"
      (is (= [2 5] (find-keys-part-1 "U\nD")))
      (is (= [1 9 8 5] (find-keys-part-1 "ULL\nRRDDD\nLURDL\nUUUUD")))
      (is (= [1 8 8 4 3] (find-keys-part-1 puzzle-input)))
  ))

  (deftest finding-keys-in-complex-pad
    (testing "Simple moves"
      (is (= [6] (find-keys-part-2 "R")))
      (is (= [2] (find-keys-part-2 "RU")))
      (is (= [5] (find-keys-part-2 "U")))
      
    (testing "Multiple lines"
      (is (= [5 \D \B 3] (find-keys-part-2 "ULL\nRRDDD\nLURDL\nUUUUD")))
      (is (= [6 7 \B \B 9] (find-keys-part-2 puzzle-input)))
  )))
