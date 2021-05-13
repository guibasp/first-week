(ns first-week.db
  (:require [first-week.logic :as f.logic]))

(defn get-all-customers
  []
  [
   {
      :customer-id 15
      :cpf "000000000191"
      :name "Pedro Paulo Santos"
      :email "pedro.paulo@gmail.com"
    }
   {
      :customer-id 16
      :cpf "000000000001"
      :name "Maria Claudia Peres"
      :email "maria.peres@gmail.com"
    }])


(defn get-customer-by-cpf
  [cpf]
  (filter #(= (:cpf %) cpf)) (get-all-customers))


;Collection that will storage credit card data

(def credit-balance
  [{
    :customer-id  15
    :credit-limit 100000
    :card         {
                   :number    "1111111111111111"
                   :cvv       "111"
                   :expiry-in "12/29"
                   :debit     true
                   :credit    true
                   :enable true
                   }
    }
   {
    :customer-id  16
    :credit-limit 10000
    :card         {
                   :number    "2222222222222222"
                   :cvv       "222"
                   :expiry-in "01/27"
                   :debit     false
                   :credit    true
                   }
    }]
  )

(defn get-credit-balance
  [customer-id]
  (->> credit-balance
       (filter #(= (:customer-id %) customer-id))))


;purchase database
(def purchase-database
  [{
    :customer-id 15
    :date-event (f.logic/date-of 2021 01 01)
    :amount-value 900
    :merchant "Coffe & You"
    :category "Food"
    }{
      :customer-id 15
      :date-event (f.logic/date-of 2021 01 02)
      :amount-value 1100
      :merchant "Coffe & You"
      :category "Food"
    }{
      :customer-id 15
      :date-event (f.logic/date-of 2021 01 03)
      :amount-value 950
      :merchant "Coffe & You"
      :category "Food"
    }{
      :customer-id 16
      :date-event (f.logic/date-of 2021 01 01)
      :amount-value 350
      :merchant "Coffe & You"
      :category "Food"
    }{
      :customer-id 16
      :date-event (f.logic/date-of 2021 01 02)
      :amount-value 830
      :merchant "Coffe & You"
      :category "Food"
    }{
      :customer-id 15
      :date-event (f.logic/date-of 2021 01 01)
      :amount-value 3100
      :merchant "Netflix"
      :category "Entertainment"
    }{
      :customer-id 15
      :date-event (f.logic/date-of 2021 01 01)
      :amount-value 4100
      :merchant "Play and fun"
      :category "Entertainment"
    }{
      :customer-id 16
      :date-event (f.logic/date-of 2021 01 02)
      :amount-value 4100
      :merchant "Salgados do Zé"
      :category "Food"
    }{
      :customer-id 16
      :date-event (f.logic/date-of 2021 01 04)
      :amount-value 2100
      :merchant "Salgados do Zé"
      :category "Food"
    }{
      :customer-id 16
      :date-event (f.logic/date-of 2021 02 11)
      :amount-value 1100
      :merchant "Salgados do Zé"
      :category "Food"}])

(defn get-all-purchase
  []
  purchase-database)

(defn find-customers-purchase
  [customer-id]
  (filter #(= (:customer-id %) customer-id) purchase-database))

(defn find-purchase-by-date-range
  [start end]
   (if (or (nil? start) (nil? end))
     nil
     (->> (get-all-purchase)
          (filter #(and (.isBefore start (:date-event %)) (.isAfter end (:date-event %)))))))

(defn find-purchases-by-merchant
  [merchant-name]               ; can replace this for merchant id in the future
  (filter #(= (:merchant %) merchant-name) (get-all-purchase)))

(defn find-purchases-by-value
  [value]
  (filter #(= (:amount-value %) value) (get-all-purchase)))