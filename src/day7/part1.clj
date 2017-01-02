(ns day7.part1
    (:require [day7.core :refer [parse-ip-address]]
     :require [useful-funcs :refer [any?]])
)

(defn- is-abba? [input]
    (and 
        (= 4 (count input))
        (not= (first input) (second input))
        (= input (reverse input))))

(defn contains-abba? [input]
    (->> input
        (partition 4 1)
        (any? is-abba?)    
    )
)

(defn parsed-ip-address-supports-tls? [ip-address]
    (and 
        (boolean (some contains-abba? (:supernet ip-address))) 
        (not-any? contains-abba? (:hypernet ip-address)))
    )

(defn supports-tls? [ip-address]
    (-> ip-address
        parse-ip-address
        parsed-ip-address-supports-tls?
))

(defn count-tls-supported-ips [input]
    (->> input
        clojure.string/split-lines
        (filter supports-tls?)
        count
        )
    )