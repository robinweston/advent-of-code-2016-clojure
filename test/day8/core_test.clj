(ns day8.core-test
  (:require [clojure.test :refer :all]
            [day8.core :refer :all]))

              

(deftest screen-pixels
    (testing "rect"
        (is (= [[true]] (process-command "rect 1x1" [[false]])))
        (is (= [[true false false]] (process-command "rect 1x1" [[false false false]])))
        (is (= [[true false false][true false false]] (process-command "rect 1x2" [[false false false][false false false]])))
    )
    
    (testing "rotate-from-idx"
        (is (= 0 (rotate-from-idx 0 0 1)))       
        (is (= 1 (rotate-from-idx 0 1 2)))       
        (is (= 0 (rotate-from-idx 1 1 2)))       
        (is (= 3 (rotate-from-idx 1 2 4)))       
        (is (= 1 (rotate-from-idx 3 2 4)))       
        (is (= 2 (rotate-from-idx 2 20 10)))       
        (is (= 0 (rotate-from-idx 0 0 3)))       
        (is (= 0 (rotate-from-idx 2 2 3)))       
    )

    (testing "rotate column command"
        (is (= [[true][false]] (process-command "rotate column x=0 by 1" [[false][true]])))
        (is (= [[true false true][false true false]] (process-command "rotate column x=1 by 1" [[true true true][false false false]])))
        (is (= [[true true true][true true false]] (process-command "rotate column x=2 by 15" [[true true false][true true true]])))
        (is (= [[true true false][true true true]] (process-command "rotate column x=2 by 10" [[true true false][true true true]])))
    )

    (testing "rotate row"
        (is (= [true false] (rotate-row [true false] 0)))
        (is (= [true false] (rotate-row [false true] 1)))
        (is (= [false false true] (rotate-row [true false false] 2)))
        (is (= [false false true] (rotate-row [true false false] 11)))
    )

    (testing "rotate row command"
        (is (= [[true false]] (process-command "rotate row y=0 by 1" [[false true]])))
        (is (= [[true false true][true false false]] (process-command "rotate row y=1 by 1" [[true false true][false false true]])))
        (is (= [[false false false][false false true]] (process-command "rotate row y=1 by 11" [[false false false][true false false]])))
    )

    (def input (slurp "resources/day8.txt"))

    (testing "process-commands-then-count-pixels"
        (is (= 6 (process-commands-then-count-pixels 7 3 "rect 3x2/nrotate column x=1 by 1/nrotate row y=0 by 4/nrotate column x=1 by 1")))    
        (is (= 115 (process-commands-then-count-pixels 50 6 input)))    
    )
)