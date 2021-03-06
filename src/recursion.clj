(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll) (product (rest coll)))))

(defn singleton? [coll]
  (if (empty? coll) false
  (empty? (rest coll))))

(defn my-last [coll]
  (if (empty? (rest coll))
    (first coll)
    (my-last (rest coll))))

(defn max-element [a-seq]
  (if (empty? a-seq)
    nil
    (if (empty? (rest a-seq))
      (first a-seq)
      (max (first a-seq) (max-element (rest a-seq))))))

(defn seq-max [seq-1 seq-2]
  (if (> (count seq-1) (count seq-2))
    seq-1
    seq-2))

(defn longest-sequence [a-seq]
  (if (empty? a-seq)
    nil
    (if (empty? (rest a-seq))
      (first a-seq)
      (seq-max (first a-seq) (longest-sequence (rest a-seq))))))

(defn my-filter [pred? a-seq]
  (if (empty? a-seq)
    a-seq
    (if (pred? (first a-seq))
      (cons (first a-seq)  (my-filter pred? (rest a-seq)))
      (my-filter pred? (rest a-seq)))))

(defn sequence-contains? [elem a-seq]
  (cond
   (empty? a-seq)
     false
   (= elem (first a-seq))
     true
   :else
     (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
   (if (or (empty? a-seq) (not (pred? (first a-seq))))
     []
     (cons (first a-seq) (my-take-while pred? (rest a-seq)))))

(defn my-drop-while [pred? a-seq]
  (if (and (not (empty? a-seq)) (pred? (first a-seq)))
    (my-drop-while pred? (rest a-seq))
    a-seq))

(defn seq= [a-seq b-seq]
  (cond
   (and (empty? a-seq) (empty? b-seq))
     true
   (or (empty? a-seq) (empty? b-seq))
     false
   (= (first a-seq) (first b-seq))
     (seq= (rest a-seq) (rest b-seq))
   :else
     false
   ))

(defn my-map [f seq-1 seq-2]
   (if (or (empty? seq-1) (empty? seq-2))
     []
     (cons (f (first seq-1) (first seq-2)) (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (if (zero? k)
    1
    (* n (power n (- k 1)))))

(defn fib [n]
  (cond
   (= n 0)
    0
   (= n 1)
    1
   :else
     (+ (fib (- n 1)) (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (< how-many-times 1)
    []
    (cons what-to-repeat (my-repeat (- how-many-times 1) what-to-repeat))))


(defn my-range [up-to]
  (cond
   (= up-to 1)
    [0]
   (< up-to 1)
    []
   :else
    (cons (- up-to 1) (my-range (- up-to 1)))))

(defn tails [a-seq]
  (if (empty? a-seq)
    (cons a-seq [])
    (cons a-seq (tails (rest a-seq)))))

(defn inits [a-seq]
  (map reverse (reverse (tails (reverse a-seq)))))

(defn rotations [a-seq]
  (distinct (map concat (tails a-seq) (inits a-seq))))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq)
    freqs
    (my-frequencies-helper (assoc freqs (first a-seq) (+ (get freqs (first a-seq) 0) 1)) (rest a-seq))))

(defn my-frequencies [a-seq]
  (let [freqs {}]
  (my-frequencies-helper freqs a-seq)))

(defn un-frequencies [a-map]
  (if (empty? a-map)
    []
    (concat (repeat (first (reverse (first a-map))) (first (first a-map))) (un-frequencies (rest a-map)))))

(defn my-take [n coll]
  (if (empty? coll)
    ()
    (if (= n 0)
      []
      (cons (first coll) (my-take (- n 1) (rest coll))))))

(defn my-drop [n coll]
  (if (empty? coll)
    ()
    (if (= n 0)
      coll
      (my-drop (- n 1) (rest coll)))))

(defn halve [a-seq]
  (let [l (int (/ (count a-seq) 2))]
    (cons (my-take l a-seq) (cons (my-drop l a-seq) []))))

(defn seq-merge-helper [s-seq a-seq b-seq]
  (cond
   (empty? a-seq)
     (concat s-seq b-seq)
   (empty? b-seq)
     (concat s-seq a-seq)
   (< (first a-seq) (first b-seq))
     (cons (first a-seq) (seq-merge-helper s-seq (rest a-seq) b-seq))
   :else
     (cons (first b-seq) (seq-merge-helper s-seq a-seq (rest b-seq)))))

(defn seq-merge [a-seq b-seq]
  (let [s-seq []]
    (seq-merge-helper s-seq a-seq b-seq)))

(defn merge-sort [a-seq]
  (if (< (count a-seq) 2)
    a-seq
    (seq-merge (merge-sort (my-take (int (/ (count a-seq) 2)) a-seq)) (merge-sort (my-drop (int (/ (count a-seq) 2)) a-seq)))
))

(defn monotonically-f [op a-seq]
  (let [f (partial monotonically-f op)]
    (if (< (count a-seq) 2)
      true
      (and (op (first a-seq) (first (rest a-seq))) (f (rest a-seq))))))

(defn monotonic? [a-seq]
  (or (monotonically-f < a-seq)
      (monotonically-f > a-seq)))

(defn split-into-monotonics [a-seq]
  (if (empty? a-seq)
    a-seq
    (let [longest-monotonic-seq (first (reverse (my-take-while monotonic? (inits a-seq))))]
      (cons longest-monotonic-seq (split-into-monotonics (my-drop (count longest-monotonic-seq) a-seq))))))

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])

