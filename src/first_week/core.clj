(ns first-week.core
  (:require [first-week.db :as f.db]
            [first-week.logic :as f.logic]))


(println "\nFind user by cpf")
(->> (f.db/get-customer-by-cpf "000000000191")
     println)

(println "\nFind credit card by customer-id")
(->> (f.db/get-credit-balance 15)
     println)

(println "\nReduce by category all purchase in database")
;reduce by category all purchase in database
(->> (f.logic/calculate-amount-value-per-category (f.db/get-all-purchase))
     println)

(println "\nreduce by category purchases of customer 15")
(->> (f.logic/calculate-amount-value-per-category (f.db/find-customers-purchase 15))
     println)

(println "\nReduced by category and sort desc by value")
(->> (f.logic/calculate-amount-value-per-category (f.db/find-customers-purchase 15))
     (sort-by :amount-value)
     reverse
     println)

(println "\nPurchases filtered by dates range")
(->> (f.db/find-purchase-by-date-range (f.logic/date-of 2021 1 1) (f.logic/date-of 2021 1 31))
     (f.logic/amount-value-of-purchase)
     println)

(println "\nPurchase of customer 16 and group-by month")
(->> (f.db/find-customers-purchase 16)
     (f.logic/amount-value-of-purchase)
     println)

(println "\nFind purchase by merchant name")
(->> (f.db/find-purchases-by-merchant "Salgados do Zé")
     println)

(println "\nFind purchase by value")
(->> (f.db/find-purchases-by-value 1100)
     println)