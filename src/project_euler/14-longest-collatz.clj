(defn next-number
  "Gives the next number in the collatz sequence"
  [n]
  (if (even? n)
      (/ n 2)
      (+ 1 (* 3 n))))

(defn collatz-seq
  "Returns the collatz sequence starting from the given 'start'"
  [start]
  (loop [final-seq [start]]
    (if (= 0 (last final-seq))
     []
     (if (= 1 (last final-seq))
        final-seq
        (recur
          (conj
            final-seq
            (next-number (last final-seq))))))))

(defn key-for-max-val
 "Finds the key with the maximum value in the given map"
 [map-to-reduce]
 (key
   (first
    (reduce
        #(if (> (val (first %1)) (val (first %2)))
           %1
           %2)
        map-to-reduce))))

;; Find the key with maximum value
(key-for-max-val
  (map
    ;; Form a map with key as the start of number,
    ;; value as the count of the numbers in collatz-seq
    #(hash-map %1 (count (collatz-seq %1)))
    (range 1000000)))
