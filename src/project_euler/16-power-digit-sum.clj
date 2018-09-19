(defn pow [a b] (reduce *' 1 (repeat b a)))

(reduce
  +
  (map
    #(Character/getNumericValue %1)
    (seq (str (pow 2 1000)))))
