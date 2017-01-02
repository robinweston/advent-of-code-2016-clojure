(ns day7.part2
    (:require [useful-funcs :refer [in? any? char-seq->str ]]
    :require [day7.core :refer [parse-ip-address]])
)

(defn is-aba? [input]
    (and 
        (= 3 (count input))
        (not= (first input) (second input))
        (= input (char-seq->str (reverse input)))
))

(defn- contains-inverse-aba? [aba coll]
    (in? coll (str (second aba) (first aba) (second aba)))
    )

(defn- extract-abas [ip-segment]
    (->> ip-segment
        (partition 3 1)
        (map #(apply str %))
        (map #(if(is-aba? %) %))
        (remove nil?)
))

(defn parsed-ip-address-supports-ssl? [ip-address]
    (let [
        hypernet-abas (flatten (map extract-abas (:hypernet ip-address)))
        supernet-abas (flatten (map extract-abas (:supernet ip-address)))
    ]
        (any? #(contains-inverse-aba? % hypernet-abas) supernet-abas) 
    ))

(defn supports-ssl? [ip-address]
    (-> ip-address
        parse-ip-address
        parsed-ip-address-supports-ssl?
    ))

(defn count-ssl-supported-ips [input]
    (->> input
        clojure.string/split-lines
        (filter supports-ssl?)
        count
        ))