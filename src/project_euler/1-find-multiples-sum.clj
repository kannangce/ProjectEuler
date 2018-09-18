(defn is-divisible
 "Tests whether the given number(first argument) is divisible at least one of
 the numbers given(rest of the arguments)."
 [number-to-test & possible-factors]
 (not
  (nil?
    (some
      #(= (mod number-to-test %1) 0)
      possible-factors))))

;; Sum of all the numbers under 1000
;; that are divisible by either 3 or 5
(reduce + (filter
           #(is-divisible %1 3 5)
           (range 1 1000)))
