(defn square
  [x]
  (* x x))


(defn
  "Check if the triplet is matching the custom condition given"
  custom-pythagorean-triplet?
  [seq-to-check]
  (def a (nth seq-to-check 0))
  (def b (nth seq-to-check 1))
  (def c (nth seq-to-check 2))
  (and
    (= 1000.0 (reduce + seq-to-check))
    (= (Math/floor c) (Math/sqrt (+ (square a) (square b))))))


;; Do custom filter
(filter custom-pythagorean-triplet?
  ;; Flatten out the nested map using reduce
  (reduce into
        []
        (map
          (fn
            [x]
            (map
              ;; Possible triplets
              #(vector x %1 (Math/sqrt (+ (square x) (square %1))))
              ;; Values of b, given b > a
              (range x 1000)))
          ;; Values of a
          (range 1 1000))))
