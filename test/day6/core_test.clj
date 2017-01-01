(ns day6.core-test
  (:require [clojure.test :refer :all]
            [day6.core :refer :all]))

(def sample-input (slurp "resources/day6_sample.txt"))
(def input (slurp "resources/day6.txt"))

(deftest message-decoding
  (testing "character-frequency"
    (is (= [{\a 1} {\b 1} {\c 1}] (character-frequency "abc")))
    (is (= [{\a 2} {\b 2} {\c 1 \x 1}] (character-frequency "abc\nabx")))
  )

  (testing "decode-message"
      (is (= "top" (decode-message "top")))
      (is (= "easter" (decode-message sample-input)))
      (is (= "afwlyyyq" (decode-message input)))
    )
    
  (testing "decode-advanced-message"
      (is (= "top" (decode-advanced-message "top")))
      (is (= "advent" (decode-advanced-message sample-input)))
      (is (= "bhkzekao" (decode-advanced-message input)))
    )

    )

