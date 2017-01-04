(ns day8.core)

(defn- generate-y-x-coords [width height]
    (for [x (range width) y (range height)]
        [y x]))

(defn- rect [width height grid]
    (->> (generate-y-x-coords width height)
        (reduce #(assoc-in %1 %2 true) grid)        
))

(defn rotate-from-idx [idx offset size]
   (mod (- idx offset) size))

(defn- get-in-grid [x y grid]
    (get-in grid [y x]))

(defn- rotate-column-in-grid [x by grid]
    (let [grid-height (count grid)]
        (vec (map-indexed #(assoc %2 x (get-in-grid x (rotate-from-idx %1 by grid-height) grid)) grid))
    ))

(defn rotate-row [row by]
    (let [row-length (count row)]
        (vec (map-indexed (fn [idx _] (nth row (rotate-from-idx idx by row-length))) row))))

(defn- rotate-row-in-grid [y by grid]
    (update grid y #(rotate-row % by)))

(def commands
    [
        {:regex #"rect (\d+)x(\d+)" :fn rect}
        {:regex #"rotate column x=(\d+) by (\d+)" :fn rotate-column-in-grid}
        {:regex #"rotate row y=(\d+) by (\d+)" :fn rotate-row-in-grid}
    ]
)

(defn process-command [command-text grid]
    (reduce 
        #(if-let [matches (seq (re-find (:regex %2) command-text))]
            ((:fn %2) (read-string (second matches)) (read-string (nth matches 2)) %1)
            %1
        ) 
    grid commands))

(defn- create-empty-grid [width height]
    (vec (repeat height (vec (repeat width false)))))

(defn- count-row-pixels [row]
    (count (filter true? row)))

(defn- count-grid-pixels [grid]
    (reduce #(+ (count-row-pixels %2) %1) 0 grid))

(defn process-commands-then-count-pixels [grid-width grid-height command-texts]
    (count-grid-pixels 
        (reduce #(process-command %2 %1) (create-empty-grid grid-width grid-height) (clojure.string/split-lines command-texts))))