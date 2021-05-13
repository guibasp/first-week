(ns first-week.logic)
(defn reduce-by-category
  [[category purchase]]
  (if (not-empty purchase)
    {:category category,
   :amount-value (->> purchase
                      (map #(:amount-value % 0))
                      (reduce +))}
  {:category category,
   :amount-value 0}))

(defn calculate-amount-value-per-category
  [purchases]
  (->> purchases
       (group-by :category)
       (map reduce-by-category)))

(defn date-of
  [year month day]
  (java.time.LocalDate/of year month day))


(defn sum-bills-of-month
  [[month purchase]]
  {:month month :amount-month-value (->> purchase
                                         (map #(:amount-value % 0))
                                         (reduce +))})

(defn amount-value-of-purchase
  [purchase]
  (->> purchase
       (group-by #(.getMonth (:date-event % -1)))
       (map sum-bills-of-month)))