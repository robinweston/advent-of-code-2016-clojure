(ns day4.core)

(defn extract-room-info [input]
    (let [match (re-find #"(.+)-(\d+)\[(.+)\]" input)]
    {
        :name (second match)
        :sector-id (Integer/parseInt (nth match 2))
        :checksum (nth match 3)
    }))

(defn- ordered-letter-frequencies [input]
    (let [occurences (dissoc (frequencies input) \-)
         compare-fn (fn [key1 key2] (compare [(get occurences key2) key1] [(get occurences key1) key2]))]
        (into (sorted-map-by compare-fn) occurences)
))

(defn generate-checksum [room-name]
    (->> room-name
        ordered-letter-frequencies
        keys
        (take 5)
        clojure.string/join
    ))

(defn is-valid-room? [room]
    (= (:checksum room) (generate-checksum (:name room))))

(defn- filter-valid-rooms [input]
    (->> input
        clojure.string/split-lines
        (map extract-room-info)
        (filter is-valid-room?)
))

(defn sector-ids-sum-for-valid-rooms [input]
    (->> input
        filter-valid-rooms
        (map :sector-id)
        (reduce +)
))

(defn- shift-word [word offset]
    (let [alphabet "abcdefghijklmnopqrstuvwxyz"
          lookup (assoc (zipmap alphabet (drop offset (cycle alphabet))) \- " ")]
    (->> word
        seq
        (map #(get lookup %))
        clojure.string/join
)))

(defn decrypt-name [room-info]
    (shift-word (:name room-info) (:sector-id room-info)))

(defn decrypt-all-valid-rooms [input]
    (->> input
        filter-valid-rooms
        (map #(hash-map :decrypted-name (decrypt-name %) :sector-id (:sector-id %)))
))