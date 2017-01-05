(ns day9.core
(:require [useful-funcs :refer [any?]]))

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
        }))

(defn- parse-markers [raw-markers]
    (map parse-marker raw-markers))

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

(defn- multiply-reps-for-markers-that-overlap [markers marker]
    (->> 
        (filter #(markers-overlap? % marker) markers)
        (reduce #(* %1 (:reps %2)) 1)    
    ))

(defn- multiply-marker-reps-if-overlapped [markers marker]
    (update marker :reps * (multiply-reps-for-markers-that-overlap markers marker)))

(defn- marker-is-before-another-marker? [input marker]
    (= (str (nth input (+ (:start marker) (:length marker)))) "("))

(defn- combine-overlapped-markers [input markers]
    (->> markers
        (map #(multiply-marker-reps-if-overlapped markers %))
        (map #(if (marker-is-before-another-marker? input %) (assoc % :chars 0) %))
    ))

(defn- calculate-new-input-length [input markers]
    (+ (count input)
        (reduce #(- %1 (:length %2)) 0 markers)
        (reduce #(+ %1 (* (dec (:reps %2)) (:chars %2))) 0 markers)
    ))

(defn decompressed-length [input]
    (->> input
        (re-pos #"\(\w+\)")
        (parse-markers)
        (remove-overlapped-markers)
        (calculate-new-input-length input)))

(defn super-decompressed-length [input]
    (->> input
        (re-pos #"\(\w+\)")
        (parse-markers)
        (combine-overlapped-markers input)
       (calculate-new-input-length input)
    ))