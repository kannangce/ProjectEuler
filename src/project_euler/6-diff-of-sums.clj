(defn sum
  "Sum of first n numbers"
  [n]
  (/ (* n (+ n 1)) 2))

(defn sum-of-squares-n
  "Sum of squares of first n numbers"
  [n]
  (/ (* n (+ n 1) (+ (* 2 n) 1)) 6))

(defn find-diff
  "Difference of 'squares of sum of first n numbers' and
  'sum of square of first n numbers'"
  [n]
  (def sum-n (sum n))
  (- (* sum-n sum-n) (sum-of-squares-n n)))

;; Difference between square of sum of first n numbers
;; and sum of square of first n numbers
(find-diff 100)
