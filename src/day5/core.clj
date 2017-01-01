(ns day5.core)

(import 'java.security.MessageDigest
        'java.math.BigInteger)

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defn is-interesting-hash? [hash]
  (every? #(= \0 %) (take 5 hash)))

(defn- add-next-password-char-if-valid [door-id password integer]
  (let [hash-value (md5 (str door-id integer))]
    (if (is-interesting-hash? hash-value)
      (str password (nth hash-value 5))
      password
    )
))

(defn calculate-door-password [door-id]
  (loop [password "" integer 0]
    (if (= (count password) 8)
      password
      (recur (add-next-password-char-if-valid door-id password integer) (inc integer))
    )))

(def password-char-unknown \-)

(defn char->int [char] 
  (Character/digit char 10))

(defn- complex-password-from-hash [password hash]
  (let [password-index (char->int (nth hash 5))]
    (if (and (<= 0 password-index 7) (= (nth password password-index) password-char-unknown))
      (let [new-password (assoc password password-index (nth hash 6))] 
        (println (apply str new-password))
        new-password      
      )
      password
    )))

(defn- add-next-complex-password-char-if-valid [door-id password integer]
  (let [hash-value (md5 (str door-id integer))]
    (if (is-interesting-hash? hash-value)
      (complex-password-from-hash password hash-value)
      password
    )
))

(defn calculate-complex-door-password [door-id]
  (loop [password (vec (replicate 8 \-)) integer 0]
    (if (some #(= password-char-unknown %) password)
      (recur (add-next-complex-password-char-if-valid door-id password integer) (inc integer))
      (apply str password)      
    )))
