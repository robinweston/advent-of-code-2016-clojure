(ns day2-clojure.core)

(def keypad
    [
        [1 2 3]
        [4 5 6]
        [7 8 9]
    ])

(def starting-position 
  {:x 1 :y 1})

(defn- find-key [position] 
    (get-in keypad [(:y position) (:x position)]))

(defn- valid-position? [position]
  (let [x (:x position)
        y (:y position)
  ]
  (and (>= x 0) (< x 3) (>= y 0) (< y 3))
))

(defn- safe-move [previous-position key update-fn]
    (let [next-position (update previous-position key update-fn)]
     (if (valid-position? next-position)
      next-position
      previous-position
     )))

(defn- move [current-position instruction]
    (case instruction
        \U (safe-move current-position :y dec)
        \L (safe-move current-position :x dec)
        \D (safe-move current-position :y inc)
        \R (safe-move current-position :x inc)
        ))

(defn- follow-instruction-line [starting-position instructions-line]
  (reduce move starting-position (seq instructions-line)))

(defn- find-line-finishing-positions [instruction-lines]
    (reduce 
      (fn [positions line] 
        (conj positions 
          (follow-instruction-line (if (empty? positions) starting-position (last positions)) line)))
      [] instruction-lines))

(defn find-keys [instructions-string]
  (let [instruction-lines (clojure.string/split-lines instructions-string)]
    (map find-key 
      (find-line-finishing-positions instruction-lines))
))
