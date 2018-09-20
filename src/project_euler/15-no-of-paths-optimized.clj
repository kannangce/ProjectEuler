;; The idea here is , we will represent the grid as 'n X n' matrix.
;; Starting from the bottom-right (ie, position [(n-1) (n-1)]) we will
;; recursively do a 'up' and 'left' navigation until the terminal node.
;; Whenever the navigation crosses a particular point, the value in that point
;; will be Increment.
;; Each navigation that crosses a point will be unique path from the
;; right-bottom node of the matrix.


;; When the navigation is completed, the one at the top-left will be the number
;; of paths from 'top-left' to 'bottom-right'

;; NOTE: The total number of navigation happens is only the total number of the paths
;; available.

(def x-end 0)
(def y-end 0)

(defn up
  "Do a up walk and return the resultant coordinate."
   ([x y]
    [(dec x) y])

   ([[x y]]
    (up x y)))

(defn left
   "Do a left walk and return the resultant coordinate."
   ([x y]
    [x (dec y)])
   ([[x y]]
    (left x y)))


(defn in-boundry?
  "Tells if the given coordinate is terminal or not. A terminal can be defined
  as a corrdiate beyond which the we need not check"
  [[x y]]
  (and
    (>= x x-end)
    (>= y y-end)))


(defn destination?
  "Tells if the given corrdiate is the end or not."
  [[x y]]
  (and
    (= x x-end)
    (= y y-end)))

(defn path-score
  "Returns the score of the current co-ordinate.
  1 if it is 'destination?' 0 otherwise"
  [[x y]]
  (if (destination? [x y]) 1 0))

(defn get
  "Gets the element in the global matrix for the given x and y.
  0 if it is not available.
  This is a special case of get, since we
  are going to multiply the number, we would return a 0 when the number is not
  available, thus making the result to 0.
  The x and y is not to interpreted in the sense of comparing  what you see to
  the actual matrix. But as a programming matrix."
  ([given-matrix [x y]]
   ;(println x y)
   (if (in-boundry? [x y])
     (nth (nth given-matrix x) y)
    0))

  ([given-matrix x y]
   (get given-matrix [x y])))


(defn update-val
  "Increment the value at the given [x y] in the given matrix
   and returns it"
  [dist-array [x y]]
  ;(println (str "[" x y "]" dist-array ":" score))
  (assoc-in
    dist-array
    [x y]
    (inc (get dist-array [x y]))))



(defn populate-path-count
  "Populate each position with the number of paths to the right-bottom of
  the matrix starting the respective position."
  [dist-array]
  ((fn populate-path [curr-matrix [x y]]

    (if (in-boundry? [x y])
     (do
         ;; Update the score in the current position
         (def curr-dist-array
           (update-val curr-matrix [x y]))

         ;; Do left traversal and store the result
         (def left-traversed
           (populate-path
             curr-dist-array
             (left [x y])))

         ;; On top of above result do the left traversal
         ;; and return the final result
         (populate-path
           left-traversed
           (up [x y])))
     ;; if we are out of the matrix just return it
     curr-matrix))
   dist-array ;; initial array
   ;; start from bottom right
   [(dec (count dist-array)) (dec (count (first dist-array)))]))

(defn get-initial-matrix
  "Gets the initial n x n matrix with all values as 0"
  [n]
  (vec (for [ x (range n)]
         (vec (for [ y (range n)]
                0)))))

(time
  ;; Gets the no-of-paths from top-left
  (get
    ;; Populate the path count
    (populate-path-count
      ;; Gets the initial matrix with 0 scores
      (get-initial-matrix 20))
    0 0))
