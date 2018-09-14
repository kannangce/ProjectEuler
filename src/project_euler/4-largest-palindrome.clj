(require '[clojure.string :as str-util])

(defn palindrom?
  "Checks whether the given number is plaindrom or not"
  [number]
  (def as-str (str number))
  (= as-str (str-util/reverse as-str)))

(defn product
  "Gives the cartesian product of the given sequences"
 [seq1 seq2]
 (flatten
  (map
   (fn [x]
     (map
       (fn [y]
         ;(print (str x "-" y))
         (* x y))
       seq2))
   seq1)))

;; Gets the max of all the plaindrom numbers
;; from the product of all 3 digit numbers
(reduce
  #(max %1 %2)
  (filter
    palindrom?
    (product (range 100 1000) (range 100 1000))))
