(ns day7.core-test
  (:require [clojure.test :refer :all]
            [day7.core :refer :all]))

(def input (slurp "resources/day7.txt"))

(deftest IPv7-snooping
  (testing "contains-abba"
    (is (= true (contains-abba? "abba")))
    (is (= false (contains-abba? "abcd")))
    (is (= false (contains-abba? "aaaa")))
    (is (= false (contains-abba? "aa")))
    (is (= true (contains-abba? "ioxxoj")))
  )

  (testing "supports-tls"
    (is (= true (supports-tls? "abba[mnop]qrst")))
    (is (= false (supports-tls? "abcd[bddb]xyyx")))
    (is (= false (supports-tls? "aaaa[qwer]tyui")))
    (is (= true (supports-tls? "ioxxoj[asdfgh]zxcvbn")))
    (is (= true (supports-tls? "xxxbbbbyuuy[asdfgsdfsdfh]zxcvbsdfsdfn")))
    (is (= true (supports-tls? "aaaa[bbbb]cccc[dddd]abba")))
    (is (= false (supports-tls? "abba[bbbb]cccc[yxxy]pppp")))

    )

    (testing "count-tls-supported-ips"
      
      (is (= 1 (count-tls-supported-ips "abba[mnop]qrst")))
      (is (= 0 (count-tls-supported-ips "aaaa[qwer]tyui")))
      (is (= 2 (count-tls-supported-ips "abba[mnop]qrst\naaaa[qwer]tyui\nioxxoj[asdfgh]zxcvbn")))
      (is (= 110 (count-tls-supported-ips input)))
      
      )
    )

