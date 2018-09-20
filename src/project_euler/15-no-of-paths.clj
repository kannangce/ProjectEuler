;; This is a naive approach. For optimized one
;; refer to the 15-no-of-paths-optimized.clj
(def x-end 20)
(def y-end 20)

(defn down
  "Do a down walk and return the resultant coordinate."
   ([x y]
    [(inc x) y])

   ([[x y]]
    (down x y)))

(defn right
   "Do a right walk and return the resultant coordinate."
   ([x y]
    [x (inc y)])
   ([[x y]]
    (right x y)))


(defn terminal?
  "Tells if the given coordinate is terminal or not. A terminal can be defined
  as a corrdiate beyond which the we need not check"
  [[x y]]
  (or
    (or (> x x-end) (> y y-end))
    (and (= x x-end) (= y y-end))))

(defn destination?
  "Tells if the given corrdiate is the end or not."
  [[x y]]
  (and (= x x-end) (= y y-end)))

(defn path-score
  "Returns the score of the current co-ordinate.
  1 if it is 'destination?' 0 otherwise"
  [[x y]]
  (if (destination? [x y]) 1 0))

(defn no-of-paths
  "Finds the number of paths from the given 'curr' co-ordinate to the
  end co-ordinates as confirmed by 'destination?'"
  [curr]
  (println (str curr " : " (terminal? curr) " : " (path-score curr)))
  (if (terminal? curr) ;; Check if further walk not required.
    (path-score curr) ;; If not required return the score.
    (+ ;; If required walk down and right and calcuate the scores
      (no-of-paths (down curr))
      (no-of-paths (right curr)))))

(no-of-paths [1 1])
