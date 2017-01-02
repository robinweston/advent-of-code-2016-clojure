(ns day7.core)

(defn- is-abba? [input]
    (and 
        (not= (first input) (second input))
        (= input (reverse input))))

(defn contains-abba? [input]
    (loop [str input]
        (if (< (count str) 4)
            false
            (if (is-abba? (take 4 str))
                true
                (recur (drop 1 str))
            )
        )))

(defn- parse-ip-address[ip-address]
    (let [matches (re-seq #"\w+" ip-address)]
        {
            :standard (take-nth 2 matches)
            :hypernet (take-nth 2 (rest matches))
        }))

(defn parsed-ip-address-supports-tls? [ip-address]
    (and 
        (boolean (some contains-abba? (:standard ip-address))) 
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