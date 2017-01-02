(ns day7.core)

(defn parse-ip-address [ip-address]
    (let [matches (re-seq #"\w+" ip-address)]
        {
            :supernet (take-nth 2 matches)
            :hypernet (take-nth 2 (rest matches))
        }))