(ns useful-funcs)

(defn in? 
  "true if coll contains elm"
  [coll elm]  
  (some #(= elm %) coll))

(def any? 
  (comp boolean some))

(defn char-seq->str [char-seq] 
  (apply str char-seq))

(defn str-insert
  "Insert c in string s at index i."
  [s c i]
  (str (subs s 0 i) c (subs s i)))