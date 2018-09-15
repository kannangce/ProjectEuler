(defn prime?
  "Verifies whether the given number is prime or not"
  [number]
  (if (= number 0) false
   (do
    (if (<= number 2) true
     (nil?
      (some
       #(<= (mod number %1) 0)
       (range 2 (inc (Math/sqrt number)))))))))

(last (vec (take 10002 (filter prime? (range)))))
