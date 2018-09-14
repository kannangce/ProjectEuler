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

(defn prime-factor?
  "Tells if the given 'number' is divisible by 'factor' and
  if the 'factor' is a prime number"
 [number factor]
 (and (prime? factor) (<= (mod number factor) 0)))

(defn largest-factor
  "Finds the largest factor of the given number"
  [number]
  (first
    (filter
      #(prime-factor? number %1)
      (range (Math/ceil (Math/sqrt number)) 1 -1))))


;; Largest factor for 600851475143
(largest-factor 600851475143)
