(ns day9.core-test
  (:require [clojure.test :refer :all]
            [day9.core :refer :all]))

(deftest decompression
    (testing "decompress"
        (is (= "ADVENT" (decompress "ADVENT")))
        (is (= "ABBBBBC" (decompress "A(1x5)BC")))
        (is (= "ABCBCDEFEFG" (decompress "A(2x2)BCD(2x2)EFG")))
        (is (= "(1x3)A" (decompress "(6x1)(1x3)A")))
        (is (= "X(3x3)ABC(3x3)ABCY" (decompress "X(8x2)(3x3)ABCY")))
        )
    
    (def input (slurp "resources/day9.txt"))
    
    (testing "compression-count"
        (is (= 18 (decompression-count "X(8x2)(3x3)ABCY")))
        (is (= 120765 (decompression-count input)))
))