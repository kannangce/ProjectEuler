(defn remove-factors
  "Remove the given factors in all the numbers of the given sequence"
  [factor seq]
  (if (= 1 factor)
   seq
   (map
     #(if (= (mod %1 factor) 0)
         (/ %1 factor) ;;factor
         %1) ;; not a factor)
     seq)))


(defn lcm
  "Finds the lcm of the numbers in the given sequece"
  [lcm-for]
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
            (rest (remove-factors (first remaining) remaining)))))))

; LCM of the numbers from 1 to 20
(lcm (range 1 21))

;; All of the below expected to give true
(= 182 (lcm [2 91]))

(= 11 (lcm [1 11]))

(= 143 (lcm [13 11]))

(= 16 (lcm [2 4 8 16]))
