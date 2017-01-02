(ns useful-funcs)

(defn in? 
  "true if coll contains elm"
  [coll elm]  
  (some #(= elm %) coll))

(def any? 
  (comp boolean some))

(defn char-seq->str [char-seq] 
  (apply str char-seq))