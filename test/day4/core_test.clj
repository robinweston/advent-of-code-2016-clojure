(ns day4.core-test
  (:require [clojure.test :refer :all]
            [day4.core :refer :all]))

(deftest testing-extract-room-info
    (testing "extract-room-info"
        (is (= { :sector-id 123 :checksum "abxyz" :name "aaaaa-bbb-z-y-x"} (extract-room-info "aaaaa-bbb-z-y-x-123[abxyz]")))
        (is (= { :sector-id 987 :checksum "abcde" :name "a-b-c-d-e-f-g-h"} (extract-room-info "a-b-c-d-e-f-g-h-987[abcde]")))
        ))

(deftest generating-checksum
    (testing "generate-checksum"
        (is (= "abxyz" (generate-checksum "aaaaa-bbb-z-y-x")))
        (is (= "abxyz" (generate-checksum "bbb-z-aaaaa-y-x")))
        (is (= "zimth" (generate-checksum "qzmt-zixmtkozy-ivhz")))
        ) 
    )

(deftest valid-rooms
    (testing "is-valid-room"
        (is (= true (is-valid-room? {:checksum "abxyz" :name "aaaaa-bbb-z-y-x"})))
        (is (= false (is-valid-room? {:checksum "abxyk" :name "aaaaa-bbb-z-y-x"})))
        ) 
    )

(def test-input (slurp "resources/day4.txt"))

(deftest sector-ids-sum
    (testing "sector-ids-sum-for-valid-rooms"
        (is (= 123 (sector-ids-sum-for-valid-rooms "aaaaa-bbb-z-y-x-123[abxyz]")))
        (is (= 0 (sector-ids-sum-for-valid-rooms "aaaaa-bbb-z-y-x-123[abxyk]")))
        (is (= 123 (sector-ids-sum-for-valid-rooms "aaaaa-bbb-z-y-x-123[abxyz]\naaaaa-bbb-z-y-x-123[abxyk]")))
        (is (= 1514 (sector-ids-sum-for-valid-rooms "aaaaa-bbb-z-y-x-123[abxyz]\na-b-c-d-e-f-g-h-987[abcde]\nnot-a-real-room-404[oarel]\ntotally-real-room-200[decoy]")))
        (is (= 245102 (sector-ids-sum-for-valid-rooms test-input)))
        )
    )

(deftest decrypt-names
    (testing "decrypt-name"
        (is (= "very encrypted name" (decrypt-name {:name "qzmt-zixmtkozy-ivhz" :sector-id 343})))
    )
    (testing "decrypt-all-valid-rooms"
        (is (= [{:sector-id 343, :decrypted-name "very encrypted name"}] (decrypt-all-valid-rooms "qzmt-zixmtkozy-ivhz-343[zimth]")))
        (is (= 324 (->>
            test-input
            decrypt-all-valid-rooms
            (filter #(= (:decrypted-name %) "northpole object storage"))
            first
            :sector-id
    ))))
)