(ns day2.core)

(def simple-keypad
    [
        [1 2 3]
        [4 5 6]
        [7 8 9]
    ])

(def simple-starting-position 
  {:x 1 :y 1})

(def complex-keypad
  [
    [nil  nil 1   nil   nil]
    [nil  2   3   4     nil]
    [5    6   7   8     9  ]
    [nil  \A  \B  \C    nil]
    [nil  nil \D  nil   nil]
  ]
  )
(def complex-starting-position 
  {:x 0 :y 2})

(defn- find-key [position keypad] 
    (get-in keypad [(:y position) (:x position)]))

(defn- valid-position? [position keypad]
  (let [x (:x position)
        y (:y position)
        keypad-height (count keypad)
        keypad-width (count (first keypad))]
  (and 
    (>= x 0) (< x keypad-width) (>= y 0) (< y keypad-height)
    (not (nil? (find-key position keypad))))
))

(defn- safe-move [previous-position key update-fn keypad]
    (let [next-position (update previous-position key update-fn)]
     (if (valid-position? next-position keypad)
      next-position
      previous-position
     )))

(defn- move [current-position instruction keypad]
    (case instruction
        \U (safe-move current-position :y dec keypad)
        \L (safe-move current-position :x dec keypad)
        \D (safe-move current-position :y inc keypad)
        \R (safe-move current-position :x inc keypad)
        ))

(defn- follow-instruction-line [starting-position instructions-line keypad]
  (reduce #(move %1 %2 keypad) starting-position (seq instructions-line)))

(defn- find-line-finishing-positions [instruction-lines starting-position keypad]
    (reduce 
      (fn [positions line] 
        (conj positions 
          (follow-instruction-line (if (empty? positions) starting-position (last positions)) line keypad)))
      [] instruction-lines))

(defn find-keys [keypad starting-position instructions-string ]
  (let [instruction-lines (clojure.string/split-lines instructions-string)]
    (map #(find-key %1 keypad)
      (find-line-finishing-positions instruction-lines starting-position keypad))
))

(def find-keys-part-1 (partial find-keys simple-keypad simple-starting-position))

(def find-keys-part-2 (partial find-keys complex-keypad complex-starting-position))
