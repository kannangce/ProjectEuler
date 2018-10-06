;; The first 2 are not scalable for matrix of size 20 X 20. Hence trying a
;; different approach.
;; Before proceeding to the solution, trying to explain
;; how I arrived at this solution,
;; I have generated the 'path-count-matrix' (no of paths from a specific point
;; to the bottom-right of the matrix) for a 10X10 matrix.


;; To interpret the below matrix,
;; - The value at the matrix is the path-count of that specific point to the bottom right.
;; - The value is represented as a 2d array in programming, hence x takes you to
;;   the row and y takes you to the specific cell in the column.
;; - Each [x,y] here represents an edge in the n X n square given in the problem.
;;
;; For ex a value at a point 56 means, there are 56 possible ways from that point
;; to the right-bottom, using only bottom and right.

;; |------------------------------------------------------------------------------------------------|
;; |48620  |   24310  |   11440  |   5005   |   2002   |   715   |   220  |   55   |   10   |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |24310  |   12870  |   6435   |   3003   |   1287   |   495   |   165  |   45   |   9    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |11440  |   6435   |   3432   |   1716   |   792    |   330   |   120  |   36   |   8    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |5005   |   3003   |   1716   |   924    |   462    |   210   |   84   |   28   |   7    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |2002   |   1287   |   792    |   462    |   252    |   126   |   56   |   21   |   6    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |715    |   495    |   330    |   210    |   126    |   70    |   35   |   15   |   5    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |220    |   165    |   120    |   84     |   56     |   35    |   20   |   10   |   4    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |55     |   45     |   36     |   28     |   21     |   15    |   10   |   6    |   3    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |10     |   9      |   8      |   7      |   6      |   5     |   4    |   3    |   2    |   1   |
;; |------------------------------------------------------------------------------------------------|
;; |  1    |   1      |    1     |     1    |     1    |     1   |    1   |    1   |   1    |   1   |
;; |------------------------------------------------------------------------------------------------|

;; As we can notice in the above matrix, value in the each cell can be calculated as below
;; -------------------------------------------
;; "path count at each point" = (path count of right point) +
;;                               (path count of bottom point)
;; -------------------------------------------

;; And path count at borders are 1, since there is only one way for them to reach
;; bottom righ, just using right/bottom.

(def x-max 20)
(def y-max 20)

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


(defn in-boundry?
  "Tells if the given coordinate is terminal or not. A terminal can be defined
  as a corrdiate beyond which the we need not check"
  [[x y]]
  (and
    (<= x x-max)
    (<= y y-max)))


(defn border?
  "Tells if the given corrdiate is at the border."
  [[x y]]
  (or
    (= x x-max)
    (= y y-max)))


(def path-count
  "Gets the path count from the top-left to the bottom-right for the matrix of
  given x,y dimension"
  (memoize (fn [[x y]]
             (if (in-boundry? [x y])
                (if (border? [x y])
                  1
                  (+
                    (path-count (down x y))
                    (path-count (right x y))))
               0))))

(time (path-count [0 0]))
