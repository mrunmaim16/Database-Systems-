--Ad-hoc sql queries

--Total working hours of managers and total amount of money given to them as salary 
select ej.jobtitle, sum(ej.workinghours) as Total_Employee_Working_Hours, 
sum(ej.workinghours)*avg(ej.payperhour) as Salary_Expenses
from FALL22_S004_9_employee e, FALL22_S004_9_employee_jobtitle ej, FALL22_S004_9_department d
where e.empID = d.empid and ej.jobtitle = e.jobtitle
group by ej.jobtitle;

/* 
JOB_TITLE   TOTAL_EMPLOYEE_WORKING_HOURS    SALARY_EXPENSES 
Manager	    160                             1920
*/

--Number of sales done in a week in the month of february that are only of In-store pickup order type
select so.day, count(day) as Day_wise_sales
from FALL22_S004_9_sales_order so, (select oid from FALL22_S004_9_Orders where otype like 'Pick up') o 
where o.oid = so.oid
and so.sodate like '%FEB%'
group by so.day;

/*
DAY         DAY_WISE_SALES
Sunday      2
Saturday    2
Tuesday     2
Thursday    2
Wednesday   2
Friday      2
Monday      2
*/

--Fetch top 5 highest product total with rating >=8
SELECT P.PID,P.PNAME,D.SELLINGPRICE*D.AVAILQUANTITY as PRODUCT_STOCK_AMOUNT,R.RATING 
from FALL22_S004_9_PRODUCT P,FALL22_S004_9_PRODUCT_PNAME_SELLINGPRICE_RATING R,FALL22_S004_9_PRODUCT_PNAME_SELLINGPRICE_QUANTITY D WHERE P.PNAME=D.PNAME AND P.PNAME=R.PNAME AND R.RATING>=8
ORDER BY PRODUCT_STOCK_AMOUNT DESC
FETCH FIRST 5 ROWS ONLY;

/*
PID     PNAME               PRODUCT_STOCK_AMOUNT    RATING
P1082   kulfi               478850.2                9
P1076   Sweet Cream Butter  215108.1                9
P1123   Cherry              91930.8                 9
P1100   Pumpkin             37609.02                8
P1059   Cream cheese        37596.15                8
*/

--Fetch 5 least rating products along with product stock amount
SELECT P.PID,P.PNAME,D.SELLINGPRICE*D.AVAILQUANTITY as PRODUCT_STOCK_AMOUNT,R.RATING 
from FALL22_S004_9_PRODUCT P,FALL22_S004_9_PRODUCT_PNAME_SELLINGPRICE_RATING R,FALL22_S004_9_PRODUCT_PNAME_SELLINGPRICE_QUANTITY D WHERE P.PNAME=D.PNAME AND P.PNAME=R.PNAME
ORDER BY R.RATING ASC
FETCH FIRST 5 ROWS ONLY;

/*
PID     PNAME               PRODUCT_STOCK_AMOUNT     RATING
P1092   Corn                48917.7                  1
P1077   Plant-based Butter  1959.25                  1
P1108   Lettuce             16943.78                 1
P1056   Lactose-Free Milk   27499.5                  1
P1009   Ground Beef         24842.8                  1
*/


--Fetch products that are delivered more than once in w mitchell st 
 select p.pName, COUNT(p.pName) as Total_sales_in_Mitchell_St from FALL22_S004_9_product_order pr
INNER JOIN FALL22_S004_9_product p ON p.pid=pr.pid
where pr.oid IN 
( SELECT ord.oid from FALL22_S004_9_order_details ord where ord.oid = pr.oid
 AND ord.del_status='Delivered' and ord.oid IN (
 SELECT o.oid from FALL22_S004_9_orders o where o.oid=ord.oid
 and o.custID IN (
    select c.custID from FALL22_S004_9_customer_address c WHERE
    c.streetno LIKE '%w%mitchell%street%'
 )
 )
 ) group by p.pName having count(p.pName) > 1;

/*
PNAME                       TOTAL_SALES_IN_MITCHELL_ST
Pumpkin                     2
Milk                        4
Butter                      3
Sour Cream & Chive Chicken  2
Red pepper/red bell pepper  2
Swiss Yogurt                2
Choco chip cookie icecream  2
*/

--Analyze order data i.e. type of order and mode of payment of each customer by name 
select o.otype, o.modeofpayment, c.custname, count(o.modeofpayment) as count_of_mode_of_payment
from FALL22_S004_9_customer c, FALL22_S004_9_orders o
where c.custID = o.custID 
group by cube (o.otype, o.modeofpayment, c.custname);

/*
OTYPE       MODEOFPAYMENT       CUSTNAME                                    COUNT_OF_MODE_OF_PAYMENT
(null)      (null)              (null)                                      50
(null)      (null)              A BAUM                                      1
(null)      (null)              KL Rahul                                    1
(null)      (null)              S TURNER                                    1
(null)      (null)              JQ RIFAII                                   1
.           .                   .
.           .                   .
.           .                   .
Delivery    Credit Card         Barinder Sran                               1
Delivery    Credit Card         I CLEMENS RUE D EGMONT                      1
Delivery    Credit Card         JORGE RUNNAZZO SALGUERO                     1
Delivery    Credit Card         MC BORGSTEEDE BIRMOERSTRAAT                 1
Delivery    Credit Card         GUNTHER SPIELMANN MECHITARISTENGASSE1       1
All Rows Fetched: 215 in 0.135 Seconds.
*/

--Analyze delivery details based on delivery status, day on which the items are delivered by an employee where delivery status is 'delivered' 
select od.del_status, so.day, e.empname, count(od.del_id) as count_of_delivery_ids
from FALL22_S004_9_employee e, FALL22_S004_9_order_details od, FALL22_S004_9_sales_order so
where e.empid = od.empid and od.oid = so.oid and od.del_status like 'Delivered'
group by rollup (od.del_status, so.day, e.empname);

/*
DEL_STATUS      DAY             EMPNAME         COUNT_OF_DELIVERY_IDS
Delivered       Friday	        PAUL TIMOTHY    1
Delivered       Friday	        (NULL)          2
Delivered       Monday	        Cierra King     1
Delivered       Monday	        Michael Gray    2
Delivered       Monday	        PAUL TIMOTHY    1
.               .               .
.               .               .
.               .               .
.               .               .
Delivered       Wednesday       KEVIN HILL      1
Delivered       Wednesday       Tania Dyer      2
Delivered       Wednesday       ROSE SUMMERS    1
Delivered       Wednesday       JENNIFER HUETTE 2
Delivered       Wednesday       (NULL)          6
All Rows Fetched: 36 in 0.049 Seconds
*/


--Total number of quantities sold for a relevent product of an order
SELECT pid, oid, quantity,
    SUM(quantity) OVER(PARTITION BY pid) AS Total,
    AVG(quantity) OVER(PARTITION BY pid) AS "Avg", 
    COUNT(quantity) OVER(PARTITION BY pid) AS "Count",  
    MIN(quantity) OVER(PARTITION BY pid) AS "Min",  
    MAX(quantity) OVER(PARTITION BY pid) AS "Max"
FROM FALL22_S004_9_product_order;

/*
PID         OID         QUANTITY        TOTAL       AVG         COUNT       MIN     MAX
P1001       ORD134      4               7           2.333333    3           1       4
P1001       ORD122      2               7           2.333333    3           1       4
P1001       ORD138      1               7           2.333333    3           1       4
P1002       ORD124      5               7           3.5         2           2       5
P1002       ORD141      2               7           3.5         2           2       5
.           .           .               .           .           .           .       .           
.           .           .               .           .           .           .       .   
.           .           .               .           .           .           .       .
.           .           .               .           .           .           .       .
P1141       ORD120      1               1           1           1           1       1
P1142       ORD116      3               3           3           1           3       3
P1143       ORD116      3               3           3           1           3       3
P1144       ORD112      2               2           2           1           2       2
P1145       ORD110      5               5           5           1           5       5
All Rows Fetched:300 in 0.354 Seconds
*/