(defn fib-seq
  "Generates the fibonacci sequence with that last digit
  is less than or equal to the given argument"
  [up-to]
  (loop [fib [1 2]]
   (if (or
          (>= (last fib) up-to)
          (> (fib-next-number fib) up-to))
    fib
    (recur (conj fib (fib-next-number fib))))))

(defn fib-next-number
  "Gives the next number in the collection representing a
  fibonacci series."
  [curr-seq]
  (if (or (empty? curr-seq) (= 1 (count curr-seq)))
  1
  (+
    (last curr-seq)
    (nth curr-seq (- (count curr-seq) 2)))))

;; The sum of all the even fibonacci numbers
;; within 4000000
(reduce
  +
  (filter
    #(= (mod %1 2) 0)
    (fib-seq 4000000)))
