(defn factors-of-lcm
  [& numbers])

(defn remove-factors
  [factor seq]
  (if (= 1 factor)
   seq
   (map
     #(if (= (mod %1 factor) 0)
         (/ %1 factor) ;;factor
         %1) ;; not a factor)
     seq)))

(def lcm-for (vec (range 1 11)))

(loop [lcd 1 remaining lcm-for]

  (if (= 0 (count remaining))
    lcd
    (do
      (def curr-number (first remaining))
      (def lcd curr-number)
      (def multiplier
        (if (= 0 (mod lcd curr-number))
          1
          curr-number))

      (recur
          (* lcd curr-number)
          (rest (remove-factors (first remaining) remaining))))))
