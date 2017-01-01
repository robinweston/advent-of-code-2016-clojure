(ns day6.core)

(defn- character-frequency-reduce [frequencies word]
    (loop [i 0 freqs frequencies]
        (if (< i (count word))            
            (recur 
                (inc i)
                (update-in freqs [i (nth word i)] (fnil inc 0)))
            freqs
        )
    )
)


(defn character-frequency [input]
    (->> input
        clojure.string/split-lines
        (reduce character-frequency-reduce [])
    )
        
)

(defn- max-character [frequencies]
    (key (apply max-key val frequencies))
    )

(defn- min-character [frequencies]
    (key (apply min-key val frequencies))
    )

(defn- decode-message-with-character-map [input char-map-fn]
    (->> 
        input
        character-frequency
        (map char-map-fn)
        (apply str))
    )

(defn decode-message [input]
    (decode-message-with-character-map input max-character))

(defn decode-advanced-message [input]
    (decode-message-with-character-map input min-character))