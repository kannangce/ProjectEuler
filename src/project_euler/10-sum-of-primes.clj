(defn prime?
  "Verifies whether the given number is prime or not"
  [number]
  (if (or (= number 0) (= number 1)) false
   (do
    (if (<= number 2) true
     (nil?
      (some
       #(<= (mod number %1) 0)
       (range 2 (inc (Math/sqrt number)))))))))


;; Sum all of them
(reduce
  +
  ;; Filter all the prime numbers within 2M
  (filter prime? (range 2000000)))
