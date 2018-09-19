(defn factor?
  "Tells if the given 'b' is factor of 'a'"
  [a b]
  (zero? (mod a b)))

;; Not possible to produce infinite series
;; using this. Also as verified using the time-taken,
;; this is not optimal compared to the other
(defn triangle-series-0
  [n]
  (loop [triangle-ser [1] curr 2]
    ;(println (str triangle-ser ":" curr))
    ;(println (str (type triangle-ser) ":" (type curr)))
    (if (> curr n)
     triangle-ser
     (recur
       (conj triangle-ser (+ (last triangle-ser) curr))
       (inc curr)))))

(defn triangle-series
  "Returns an infinite lazy sequenze of triangle series."
  ([]
   ((fn next-element [curr-elem pos]
     (lazy-seq
       (cons
         curr-elem
         (next-element
           (+ curr-elem pos) (inc pos))))) 1 2))
  ;; Returns the first n digits of the triange series
  ([a]
   (take a (triangle-series))))

(defn factors
  "Gives all the numbers(including 1 and itself)
  that can divide the given number."
  [number]
  (filter
    #(factor? number %1)
    (range 1 (inc number))))

;; Took 12.5 hrs for the below call to give the output
(some #(when
        (> (count (factors %)) 500
         %))
      (triangle-series))
