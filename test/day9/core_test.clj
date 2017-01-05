(ns day9.core-test
  (:require [clojure.test :refer :all]
            [day9.core :refer :all]))

(deftest decompression
    
    (def input (slurp "resources/day9.txt"))
    
    (testing "decompressed-length"
        (is (= 6 (decompressed-length "ADVENT")))
        (is (= 7 (decompressed-length "A(1x5)BC")))
        (is (= 11 (decompressed-length "A(2x2)BCD(2x2)EFG")))
        (is (= 6 (decompressed-length "(6x1)(1x3)A")))
        (is (= 18 (decompressed-length "X(8x2)(3x3)ABCY")))
        (is (= 120765 (decompressed-length input)))
))