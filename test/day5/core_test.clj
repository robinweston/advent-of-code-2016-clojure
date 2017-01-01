(ns day5.core-test
  (:require [clojure.test :refer :all]
            [day5.core :refer :all]))

(deftest md5-hashing
  (testing "md5"
    (is (= "00000155f8105dff7f56ee10fa9b9abd" (md5 "abc3231929")))
    (is (= "000008f82c5b3924a1ecbebf60344e00" (md5 "abc5017308")))
    )

    (testing "is-interesting-hash?"
       (is (= false (is-interesting-hash? "00x008f82c5b3924a1ecbebf60344e00")))
       (is (= true (is-interesting-hash? "00000155f8105dff7f56ee10fa9b9abd")))
       (is (= true (is-interesting-hash? "000008f82c5b3924a1ecbebf60344e00")))
      )
  )

(deftest passwords
  (testing "calculate-door-password"
    ;(is (= "18f47a30" (calculate-door-password "abc")))
    ;(is (= "1a3099aa" (calculate-door-password "uqwqemis")))
    )
  )

  (testing "calculate-complex-door-password"
    ;(is (= "05ace8e3" (calculate-complex-door-password "abc")))
    ;(is (= "694190cd" (calculate-complex-door-password "uqwqemis")))
    )

