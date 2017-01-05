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
    )

    (testing "super-decompressed-length"
        (is (= 6 (super-decompressed-length "ADVENT")))
        (is (= 20 (super-decompressed-length "X(8x2)(3x3)ABCY")))
        (is (= 241920 (super-decompressed-length "(27x12)(20x12)(13x14)(7x10)(1x12)A")))
        (is (= 445 (super-decompressed-length "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN")))
        (is (= 11658395076 (super-decompressed-length input)))

))