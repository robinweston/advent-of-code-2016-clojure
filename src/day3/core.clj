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