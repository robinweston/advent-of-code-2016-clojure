(ns day1.core)

(def north 0)
(def east 1)
(def south 2)
(def west 3)

(defn- coords-diff [position]
    (get {
        north [0 1]
        east [1 0]
        south [0 -1]
        west [-1 0]
    } (:facing position)))

(defn- update-facing [direction change-fn]
    (mod (change-fn direction) 4))

(defn- turn [position instruction]
    (update position :facing #(update-facing % (if (= (first instruction) \R) inc dec)))
)

(defn- multiply-vec [v op]
    (vec (map #(* op %) v)))

(defn- add-vecs [v1 v2]
    (vec (map + v1 v2)))

(defn- update-position-coords [coords diff multiplier]
    (add-vecs coords (multiply-vec diff multiplier)))

(defn- move [position instruction]
    (->> (read-string (subs instruction 1))
        inc
        (range 1)
        (map #(update position :coords update-position-coords (coords-diff position) %))
    )
)

(defn- follow-instruction [position instruction]
    (move (turn position instruction) instruction))

(defn- follow-instructions [instructions]
    (reduce 
        (fn [past-positions instruction] (concat past-positions (follow-instruction (last past-positions) instruction))) 
        [{:facing north :coords [0 0]}] 
        instructions
    ))

(defn- instructions->path [instructions]
    (->> instructions
        (re-seq #"[R|L]\d+")
        follow-instructions))

(defn- distance-to-position [position]
    (->> position
        :coords
        (reduce #(+ %1 (Math/abs %2)) 0)
))

(defn shortest-distance [input]
    (->> input 
        instructions->path        
        last
        distance-to-position
    ))

(defn- first-position-visited-twice [positions]
    (loop [[x & xs] positions seen #{}]
        (if (contains? seen (:coords x))
            x
            (recur xs (conj seen (:coords x)))
        )
))

(defn shortest-distance-to-first-position-visited-twice [input]
    (->> input 
        instructions->path
        first-position-visited-twice 
        distance-to-position
))

