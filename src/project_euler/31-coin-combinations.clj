;; THIS IS A IN PROGRESS CODE. YET TO BE COMPLETED

(defn all-denominations
  [final-value coins]
  (let [final-value final-value
        coins coins
        max-denom-count (quot final-value (apply min coins))]
    (loop [all-denomiations []
           curr-coin-indx 0
           curr-cnt 0
           curr-denominations (conj [] curr-cnt)]

      (println (str all-denomiations "-" curr-coin-indx "-" curr-cnt "-" curr-denominations))

      (let [curr-total-val (total-val coins curr-denominations)
            is-last-coin (= curr-coin-indx (dec (count coins)))]
        (println (str curr-total-val "-" is-last-coin))
        (if (and is-last-coin (> curr-cnt max-denom-count))
          all-denomiations
          (recur
            (if (= final-value curr-total-val) (into all-denomiations curr-denominations) all-denomiations)
            (if (> curr-cnt max-denom-count) (inc curr-coin-indx) curr-coin-indx)
            (if (> curr-cnt max-denom-count) 0 (inc curr-cnt))
            (if (> curr-cnt max-denom-count)
              (conj curr-denominations 0)
              (conj
                (into [] (butlast curr-denominations))
                (inc curr-cnt)))))))))


;; TODO Scope for memoization
(defn total-val
  [coins denominations]
  (reduce + (map * coins denominations)))

(all-denominations 10 [1 2])
