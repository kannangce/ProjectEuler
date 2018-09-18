(defn factor?
  "Tells if the given 'b' is factor of 'a'"
  [a b]
  (zero? (mod a b)))

;; Not scalable for producing infinite series
(defn triangle-series
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
  [number]
  (filter
    #(factor? number %1)
    (range 1 (inc number))))

(some #(when
        (> (count (factors %)) 5
         %))
      (triangle-series))



(some
  #(when (= 4 %) %)
  [1 2 3 4])

(some #(neg? %1) [-2 -1 0 1 2 3])
