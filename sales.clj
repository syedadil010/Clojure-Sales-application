(ns sales_app)
    (:require 'clojure.string 'java.lang.StringSeq)
(def file_cus(slurp "src/cust.txt"))
(def file_prod(slurp "src/prod.txt"))
(def file_sales(slurp "src/sales.txt"))

(def customers ())
(def products ())
(def sales ())

(defn display []
  (println "\n*** Sales Menu ***")
  (println"------------------")
  (println"1. Display Customer Table\n2. Display Product Table\n3. Display Sales Table\n4. Total Sales Table\n5. Total Count for Product\n6. Exit\n")
)
(defn parse-entity [string]
  (def lines (clojure.string/split-lines string))
  (def temp1 ())
  (doseq [line lines]
    (def temp2 (clojure.string/split line #"\|"))
    (def temp1 (conj temp1 temp2))
   );(println DB)  
 temp1
 )
(defn file-slurp []
   (def customers (parse-entity file_cus))
   (def customers(sort customers))
   ;(println (sort customers))
   (def products (parse-entity file_prod))
   (def products(sort products))
   ;(println products)
   (def sales (parse-entity file_sales))
   (def sales (sort sales))
   ;(println sales)
)


(defn custprint []
  (doseq[cust customers]    
     (println (get cust 0)":[\"" (get cust 1) "\"\"" (get cust 2) "\"" (get cust 3) "\"]")
  )
)


(defn prodprint []
  (doseq[prod products]
   (println (get prod 0)":[\"" (get prod 1) "\"\"" (get prod 2) "\"]")
  )
)


(defn salesprint []
  (doseq[sal sales]
    (print  (get sal 0)":[\"")
    (doseq[cust customers]
      (if ( = (get sal 1) (get cust 0))
        (print(get cust 1) "\"\"")
      )
    )
    (doseq[prod products]
     ( if (= (get sal 2) (get prod 0))
        (println(get prod 1) "\"\"" (get sal 3) "\"]")
     )
    )
  )
)

(defn sum [cid]
  (str cid)
   (def total 0.0)
   (doseq [sal sales]
     (if(= cid (str (get sal 1)))
       (do
         (def itemc(Float/parseFloat(get sal 3)))
         (println itemc)
         (def pid (str(get sal 2)))
         (println pid)
         (println (type pid))
         (doseq [prod products]
           (def pi (str (get prod 0)))
           (print pi)
           (print (type pi))
           (if(= pid pi)
             (do
               (def price(Float/parseFloat((get prod 2))))
               (println price)
               (def pricet(* price itemc))
               (println pricet)
               (def total (+ total pricet))
               (println total)
             )
             (println "1st do" )
           )
         )
       )
       (println "end")
     )
   )
     
   total
)
   
   
 
(defn cussum[]
  (println "Enter Customer Name")
  (flush)
  (def input (read-line))
  (str input)
    (doseq[cust customers]
      (if (= input (str(get cust 1)))
        (def cid(get cust 0))
      )
    )
  (str cid)
  (def total 0.0)
  (doseq [sal sales]
     (if(= cid (str (get sal 1)))
       (do
         (def itemc(read-string(get sal 3)))
         (println itemc)
         (def pid (get sal 2))
         (str pid)
         (doseq [prods products]
           (def pi (get prods 0))
         (str pi)
         (if (= pid pi)
           (do
             (def price(get prods 2))
               ;(println price)
             (def pricen(read-string price))
             ;(println pricen)
             (def pricet 0.0)
               (def pricet(* pricen itemc))
               ;(println pricet)
             (def total (+ total pricet))
               
             )
         )
         )
       )
     )
  )
  (println input": $" total )
)


(defn salescou []
  (def prodst 0)
  (println "Enter Product Name")
  (flush)
  (def input (read-line))
  (str input)
  ;(print input)
  (doseq[prod products]
    (def x(get prod 1))
    (str x)
   ; (print x)
      (if (= input x)
         (do
          (doseq[sal sales]
          (def gp0(get prod 0))
          (str gp0)
          ;(print gp0)
          (def gs2 (get sal 2))
              (str gs2)
           ; (print gs2)
          (if(= gp0 gs2)
            (do
              (def pc(Integer/parseInt(get sal 3)))
             ; (print pc)
              (def prodst(+ prodst pc)) 
              
            )
          )
          )
         )
      )
  )
  (println input ":" prodst)
)

(defn menu []
  (display)
  (println  "Enter an option?") 
  (def selection (read-line))
    (cond 
    (= "6" selection) (println "Good Bye")
    (= "1" selection) (do(custprint)(menu))
    (= "2" selection) (do(prodprint)(menu))
    (= "3" selection) (do(salesprint)(menu))
    (= "4" selection) (do(cussum)(menu))
    (= "5" selection) (do(salescou)(menu))
    :else (do(println "Error")(menu))
    )
)

(file-slurp)
(menu)