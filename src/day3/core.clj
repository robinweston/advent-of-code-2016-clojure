(ns day3.core)

(defn- parse-lengths [input-line]
    (map read-string 
        (filter #(not (clojure.string/blank? %1))
            (clojure.string/split input-line #"\s+")))
)

(defn- is-possible-triangle? [lengths]
    (let [sorted-lengths (sort lengths)]
        (> (+ (first sorted-lengths) (second sorted-lengths)) (nth sorted-lengths 2))))

(defn count-triangles [input]
    (count 
        (filter is-possible-triangle? 
            (map parse-lengths 
                (clojure.string/split-lines input))))) 

(defn- convert-set-of-horizontal-lengths-to-vertical [lengths]
    (let [first-length (first lengths)
        second-length (second lengths)
        third-length (nth lengths 2)
        map-fn #(vector (nth first-length %) (nth second-length %) (nth third-length %))
    ]
    (map map-fn (range 3))
))

(defn- to-vertical-lengths [lengths]
    (apply concat (map convert-set-of-horizontal-lengths-to-vertical (partition 3 3 lengths))))

(defn count-vertical-triangles [input]
    (count 
        (filter is-possible-triangle? 
            (to-vertical-lengths 
                (map parse-lengths 
                    (clojure.string/split-lines input))))))