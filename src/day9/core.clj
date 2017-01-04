(ns day9.core
(:require [useful-funcs :refer [str-insert any?]]))

(defn re-pos [re s]
        (loop [m (re-matcher re s)
               res {}]
          (if (.find m)
            (recur m (assoc res (.start m) (.group m)))
            res)))

(defn- parse-marker [raw-marker]
    (let [marker-text (val raw-marker)
        matches (re-find #"(\d+)x(\d+)" marker-text)]
        {
            :chars (read-string (second matches)) 
            :reps (read-string (nth matches 2))
            :start (key raw-marker) 
            :length (count marker-text)
            :raw-text marker-text
        }))

(defn- parse-markers [raw-markers]
    (map parse-marker raw-markers))

(defn- capture-marker-sequences [input marker]
    (->> input
            (drop (+ (:start marker) (:length marker)))
            (take (:chars marker))
            (repeat (:reps marker))
            flatten
            (apply str)
            (assoc marker :seq)
))

(defn- remove-subs [s start length]
    (str (subs s 0 start) (subs s (+ start length))))

(defn- apply-marker-sequence [input marker]
    (let [insertion-point (:start marker)]
        (-> (remove-subs input insertion-point (+ (:length marker) (:chars marker)))
            (str-insert (:seq marker) insertion-point))))

(defn- increment-marker-start-positions [markers increment]
    (map #(update % :start + increment) markers))

(defn- string-increment [marker]
    (- (count (:seq marker)) (:length marker) (:chars marker)))

(defn- apply-marker-sequences [input markers]
    (loop [remaining-markers markers decompressed input]     
        (if (seq remaining-markers)
            (let [current-marker (first remaining-markers)]
                (recur 
                    (increment-marker-start-positions (drop 1 remaining-markers) (string-increment current-marker)) 
                    (apply-marker-sequence decompressed current-marker))
            )
        decompressed
)))

(defn- apply-markers [input markers]
    (->> markers
        (map #(capture-marker-sequences input %))
        (apply-marker-sequences input)
))

(defn- markers-overlap? [a b]
    (let [a-start (:start a)
        a-end (+ a-start (:length a) (:chars a))
        b-start (:start b)]
        (< a-start b-start a-end))
    )

(defn- marker-is-overlapped? [markers marker]
    (any? #(markers-overlap? % marker) markers))

(defn- remove-overlapped-markers [markers]
    (remove #(marker-is-overlapped? markers %) markers))

(defn decompress [input]
    (->> input
        (re-pos #"\(\w+\)")
        (parse-markers)
        (remove-overlapped-markers)
        (apply-markers input)))

(defn decompression-count [input]
    (->> input
        decompress
        count))