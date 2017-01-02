(ns day7.core-test
  (:require [clojure.test :refer :all]
            [day7.part1 :refer :all]
            [day7.part2 :refer :all]))

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

  (testing "is-aba"
    (is (= true (is-aba? "aba")))  
    (is (= false (is-aba? "aaa")))  
    (is (= false (is-aba? "abba")))  
    (is (= false (is-aba? "xyz")))  
  )

  (testing "supports-ssl"
    (is (= true (supports-ssl? "aba[bab]xyz")))
    (is (= false (supports-ssl? "xyx[xyx]xyx")))
    (is (= true (supports-ssl? "aaa[kek]eke")))
    (is (= true (supports-ssl? "zazbz[bzb]cdb")))
  )

  (testing "count-tls-supported-ips"
    (is (= 1 (count-tls-supported-ips "abba[mnop]qrst")))
    (is (= 0 (count-tls-supported-ips "aaaa[qwer]tyui")))
    (is (= 2 (count-tls-supported-ips "abba[mnop]qrst\naaaa[qwer]tyui\nioxxoj[asdfgh]zxcvbn")))
    (is (= 110 (count-tls-supported-ips input)))
  )

    (testing "count-ssl-supported-ips"
    (is (= 3 (count-ssl-supported-ips "aba[bab]xyz\nxyx[xyx]xyx\naaa[kek]eke\nzazbz[bzb]cdb")))
    (is (= 242 (count-ssl-supported-ips input)))
  )
)

