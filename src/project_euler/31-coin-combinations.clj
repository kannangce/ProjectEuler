;; TODO Scope for memoization
(defn total-val
  [coins denominations]
  (reduce + (map * coins denominations)))

(defn max-constraint
  [expected-val coins-list]
  (fn [coin-indx coin-cnt]
    (>= expected-val
      (* coin-cnt (coins-list coin-indx)))))

(defn next-possible?
  [expected-val denominations coin-list]
  (let [max-coin-indx (dec (count coin-list))]
    ((max-constraint expected-val coin-list)
     max-coin-indx
     (denominations max-coin-indx))))

(defn next-possibility
  [coin-denom max-constraint]
  (loop [curr-denomiation coin-denom curr-index 0]
    (let [curr-coin-cnt (coin-denom curr-index)]
      (if (max-constraint curr-index curr-coin-cnt)
        (update curr-denomiation curr-index inc)
        (recur (assoc curr-denomiation curr-index 0) (inc curr-index))))))

(defn all-denominations
  [final-value coins]
  (let [final-value final-value
        coins coins
        max-denom-count (quot final-value (apply min coins))]
    (loop [all-denomiations (vector)
           curr-denominations (vec (replicate (count coins) 0))]
          (if-not (next-possible? final-value curr-denominations coins)
            all-denomiations
            (recur
              (if (= final-value (total-val coins curr-denominations))
                  (conj all-denomiations curr-denominations)
                  all-denomiations)
              (next-possibility
                curr-denominations
                (max-constraint final-value coins)))))))


(time (count (all-denominations 200 [1 2])))
; "Elapsed time: 135.465971 msecs"
; user=>
; 101

(time (count (all-denominations 200 [1 2 5])))
; "Elapsed time: 7437.966924 msecs"
; user=>
; 2081

(time (all-denominations 200 [1 2 5 10 20 50 100 200]))
; Runs forever
