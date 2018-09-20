(defn pow [a b] (reduce *' 1 (repeat b a)))

(reduce
  +
  (map
    #(Character/getNumericValue %1)
    (str (pow 2 1000))))
