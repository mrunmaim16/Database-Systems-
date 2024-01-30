--Update Scripts 

--Adding new coloumn deptID (that refers to fall22_s004_9_department) to the Fall22_S004_9_EMPLOYEE
ALTER TABLE Fall22_S004_9_EMPLOYEE
ADD  deptID VARCHAR(10);

ALTER TABLE Fall22_S004_9_EMPLOYEE
ADD CONSTRAINT FALL22_S004_9_deptID
FOREIGN KEY (deptID) REFERENCES fall22_s004_9_department(deptID);

--Updating the values for deptID in Fall22_S004_9_EMPLOYEE table
update fall22_s004_9_employee set deptID = null;
update fall22_s004_9_employee set deptID = 'DEPT001' where empid='E004';
update fall22_s004_9_employee set deptID = 'DEPT002' where empid='E018';
update fall22_s004_9_employee set deptID = 'DEPT003' where empid='E050';
update fall22_s004_9_employee set deptID = 'DEPT004' where empid='E041';

update fall22_s004_9_employee set deptID = 'DEPT001' 
where empid in ('E001', 'E002', 'E003', 'E046', 'E047','E048', 'E049', 'E050', 'E019', 'E020', 'E021');

update fall22_s004_9_employee set deptID = 'DEPT002' 
where empid in ('E005','E006','E007','E008','E009','E010', 'E011', 'E012','E013', 'E014', 'E015', 'E016', 'E017');

update fall22_s004_9_employee set deptID = 'DEPT003' 
where empid in ('E042', 'E043', 'E044', 'E045', 'E035', 'E036', 'E037', 'E038', 'E039', 'E040', 'E022', 'E023');

update fall22_s004_9_employee set deptID = 'DEPT003' 
where empid in ('E024', 'E025', 'E026', 'E027', 'E028', 'E029', 'E030', 'E031', 'E032', 'E033', 'E034');


--Updating the working hours of manager from 40 to 41
update FALL22_S004_9_employee_jobtitle set workinghours = 41 where jobtitle like 'Manager';


--Updating order type of an order with oid = ORD120 from delivery to pick up
update FALL22_S004_9_orders set otype = 'Pick up' where oid = 'ORD120'; 


--Updating available quantity of kulfi to from 98732 to 81562
UPDATE FALL22_S004_9_PRODUCT_PNAME_SELLINGPRICE_QUANTITY SET availQuantity = '81562' WHERE pname = 'kulfi';


--Updating order data i.e. mode of payment to 'Apple pay' for those records whose count of mode of payment is less than 10
update FALL22_S004_9_orders o1 set o1.modeofpayment = 'Apple Pay' where o1.modeofpayment in 
(select o3.modeofpayment from FALL22_S004_9_orders o3 
group by o3.modeofpayment 
having count(*) < 10 
);


--Updating delivery status to 'Rejected-unknown reason' for orders with delivery ids ('DEL0002', 'DEL0019', 'DEL0021', 'DEL0040', 'DEL0030')
update FALL22_S004_9_order_details od 
set od.del_status = 'Rejected-unknown reason' 
where od.del_id in ('DEL0002', 'DEL0019', 'DEL0021', 'DEL0040', 'DEL0030');

--Updating the customer address from mitchell street to 101 center
UPDATE fall22_s004_9_customer_address
SET streetno='101 center'
WHERE streetno LIKE '%w%mitchell%street%,1%';

--Deleting the details of the customer whose name is 'Vijay Shankar'
delete from fall22_s004_9_customer_email where fall22_s004_9_customer_email.custID='C1041';

--Deleting the details of cherry product
delete from FALL22_S004_9_PRODUCT where PNAME = 'Cherry';