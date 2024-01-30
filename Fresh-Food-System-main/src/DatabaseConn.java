import java.sql.*;
import java.util.Scanner;

class DatabaseConn {
    Connection con;
    Scanner scan = new Scanner(System.in);
    public  DatabaseConn() throws SQLException, ClassNotFoundException {
        String url = "jdbc:oracle:thin:@acaddbprod.uta.edu:1523/pcse1p.data.uta.edu";
        String username = "sxm4871";
        String password = "Sameersam2022";
        con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");
        Class.forName("oracle.jdbc.OracleDriver");
    }

    public void closeConnection() throws SQLException {
        con.close();
        System.out.println("Connection Closed....");
    }

    public void searchEmpID() throws SQLException {
        System.out.println("Enter employee id to be searched : ");
        String empId = scan.next();

        String query = "select emp.empid, emp.ssn, emp.empname, email.empemail, \n" +
                "phno.empphoneno, jt.jobtitle, jt.workinghours, jt.payperhour \n" +
                "from FALL22_S004_9_EMPLOYEE emp, FALL22_S004_9_EMPLOYEE_EMAIL email,\n" +
                "FALL22_S004_9_EMPLOYEE_PHONENO phno, FALL22_S004_9_EMPLOYEE_JOBTITLE jt\n" +
                "where emp.empid=email.empid and emp.empid=phno.empid\n" +
                "and emp.jobtitle=jt.jobtitle and emp.empid = \'" + empId + "\'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()) {
            String id = rs.getString(1);
            String ssn = rs.getString(2);
            String name = rs.getString(3);
            String email = rs.getString(4);
            String phno = rs.getString(5);
            String jobTitle = rs.getString(6);
            String wh = rs.getString(7);
            String pph = rs.getString(8);
            System.out.println("ID : " + id +
                    "\tSSN : " + ssn +
                    "\tName : " + name +
                    "\tEmail : " + email +
                    "\tPhone no. : " + phno +
                    "\tJob Title : " + jobTitle +
                    "\tWorking hours : " + wh +
                    "\tPay per hour : " + pph);
        }
        st.close();
    }

    public void searchEmpName() throws SQLException {
        System.out.println("Enter employee Name to be searched : ");
        String empFirstName = scan.nextLine();
        String query = "select * from FALL22_S004_9_EMPLOYEE where empname = \'" + empFirstName + "\'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.println(rs.next());
        String id = rs.getString(1);
        String ssn = rs.getString(2);
        String name = rs.getString(3);
        String jobTitle = rs.getString(4);
        System.out.println("ID : " + id +
                "\nSSN : " + ssn +
                "\nName : " + name +
                "\nJob Title : " + jobTitle);

        st.close();
    }

    public void getAllEmployees() throws SQLException {
        String query = "select * from FALL22_S004_9_EMPLOYEE";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4));
        }
        st.close();
    }

    public void searchCustID() throws SQLException {
        System.out.println("Enter Customer ID to be searched : ");
        String custId = scan.next();
        String query1 = "select customer.custid, customer.custname, email.custemail, phno.custphoneno \n" +
                "from FALL22_S004_9_CUSTOMER customer, FALL22_S004_9_CUSTOMER_EMAIL email, FALL22_S004_9_CUSTOMER_PHONENO phno\n" +
                "where customer.custid = email.custid and customer.custid = phno.custid and customer.custid = \'" + custId + "\'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        System.out.println(rs.next());
        String id = rs.getString(1);
        String name = rs.getString(2);
        String email = rs.getString(3);
        String phno = rs.getString(4);

        System.out.println("ID : " + id +
                "\tName : " + name +
                "\tEmail : " + email +
                "\tPhone no. : " + phno);
        st.close();
    }

    public void searchCustName() throws SQLException {
        System.out.println("Enter Customer First and Last Name to be searched : ");
        String custFirstName = scan.nextLine();
        String query = "select c.custid, c.custname, p.pname, o.otype, o.modeofpayment \n" +
                "from FALL22_S004_9_CUSTOMER c, FALL22_S004_9_ORDERS o, \n" +
                "FALL22_S004_9_PRODUCT_ORDER po, FALL22_S004_9_PRODUCT p\n" +
                "where c.custid=o.custid and\n" +
                "o.oid = po.oid and\n" +
                "po.pid=p.pid and\n" +
                "c.custname = \'" + custFirstName+ "\'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.println(rs.next());
        String id = rs.getString(1);
        String name = rs.getString(2);
        System.out.println("Customer ID : " + id +
                "\nName : " + name );
        while(rs.next()) {
            String product = rs.getString(3);
            String otype = rs.getString(4);
            String mop = rs.getString(5);
            System.out.println(
                    "PRODUCT : " + product +
                            "\tORDER TYPE : " + otype +
                            "\tMODE OF PAYMENT : " + mop);
        }
        st.close();
    }

    public void getAllCustomers() throws SQLException {
        String query = "select * from FALL22_S004_9_CUSTOMER";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString(1) + "\t" +
                    rs.getString(2));
        }
        st.close();
    }

    public void updateOrdertype() throws SQLException {
        System.out.println("Enter Order ID  for updation: ");
        String id = scan.next();
        scan.nextLine();
        System.out.println("Enter Order type");
        String otype = scan.next();

        String query1 = "update FALL22_S004_9_orders o set o.otype = \'" + otype + "\' where o.oid = \'" +id + "\'";
        String query2 = "Select * from FALL22_S004_9_orders o where o.oid = \'" +id + "\'";
        Statement st = con.createStatement();
        ResultSet  rs2 = st.executeQuery(query1);
        if(rs2.next()) {
            ResultSet resultSet = st.executeQuery(query2);
            System.out.println(resultSet.next());

            String name = resultSet.getString(1);
            String ordertype = resultSet.getString(2);
            System.out.println("Name:" + name + "\t" + "Order Type:" + ordertype);
            st.close();
        }

    }

    public void updateModeOfPayment(String pay) throws SQLException {
        String query2 = "Select * from FALL22_S004_9_orders";
        String query1 = "update FALL22_S004_9_orders o1 set o1.modeofpayment = ? where o1.modeofpayment in \n" +
                "(select o3.modeofpayment from FALL22_S004_9_orders o3 \n" +
                "group by o3.modeofpayment \n" +
                "having count(*) < 10 \n" +
                ")";
        PreparedStatement preparedStmt = con.prepareStatement(query1);
        preparedStmt.setString(1, pay);
        Statement st = con.createStatement();
        st.executeQuery(query2);
        ResultSet resultSet = st.executeQuery(query2);
        System.out.println(resultSet.next());
        while(resultSet.next()){
            String oid = resultSet.getString(1);
            String otype = resultSet.getString(2);
            String mop = resultSet.getString(3);
            String custid = resultSet.getString(4);
            System.out.println("Order ID : " + oid + "\t" +"Order type : " + otype +
                    "\t" +"Mode of Payment : " + mop +
                    "\t" +"Customer ID : " + custid);
        }
        st.close();
    }

    public void updateDelStatus() throws SQLException {
        System.out.println("Enter Delivery Status for updation for order with in-progress delivery status: ");
        String delstatus = scan.next();
        String query1 = "update FALL22_S004_9_ORDER_DETAILS set DEL_STATUS = \'" + delstatus + "\' where DEL_STATUS LIKE 'In%Progress'";
        String query2 = "Select * from FALL22_S004_9_ORDER_DETAILS";
        Statement st = con.createStatement();
        st.executeQuery(query1);
        ResultSet resultSet = st.executeQuery(query2);
        System.out.println(resultSet.next());
        while(resultSet.next()){
            String id = resultSet.getString(1);
            String status = resultSet.getString(2);
            String empid = resultSet.getString(3);
            String orderid = resultSet.getString(4);
            System.out.println(id+ "\t\t\t" +status+ "\t\t\t" +empid+ "\t\t\t" +orderid+ "\t\t\t");
        }
        st.close();
    }

    public void analyseOrderModeOfPayment() throws SQLException {
        System.out.println("Analysis of order data i.e. type of order and mode of payment of each customer by name ");
        String query = "select o.otype, o.modeofpayment, c.custname, count(o.modeofpayment) as count_of_mode_of_payment\n" +
                "from FALL22_S004_9_customer c, FALL22_S004_9_orders o\n" +
                "where c.custID = o.custID \n" +
                "group by rollup (o.otype, o.modeofpayment, c.custname)";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        System.out.println("Order type \t Mode of payment \t Customer \t\t\t count_of_mode_of_payment");

        while(resultSet.next()){
            String otype = resultSet.getString(1);
            String mop = resultSet.getString(2);
            String custname = resultSet.getString(3);
            String count = resultSet.getString(4);
            System.out.println(otype + "\t\t\t" + mop + "\t\t\t" + custname + "\t\t\t" + count);
        }
        st.close();
    }

    public void analyseMonthlySales(String orderType, String month) throws SQLException {
        System.out.println("Number of sales done in a week in this month that are of preferred order type");
        String query = "select so.day, count(day) as Day_wise_sales\n" +
                "from FALL22_S004_9_sales_order so, (select oid from FALL22_S004_9_Orders where otype like \'%" + orderType +"%\') o \n" +
                "where o.oid = so.oid\n" +
                "and so.sodate like '%" + month +"%'\n" +
                "group by so.day";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        System.out.println("Day \t\t\t Day_wise_sales");

        while(resultSet.next()){
            String day = resultSet.getString(1);
            String count = resultSet.getString(2);

            System.out.println(day + "\t\t\t" + count);
        }
        st.close();
    }

    public void analyseDailyDelivery(String delStatus, String day) throws SQLException {
        System.out.println("Analysis of delivery details based on delivery status, day and employee ");
        String query = "select od.del_status, so.day, e.empname \n" +
                "from FALL22_S004_9_employee e, FALL22_S004_9_order_details od, FALL22_S004_9_sales_order so\n" +
                "where e.empid = od.empid and od.oid = so.oid and od.del_status like \'%" + delStatus + "%\' and so.day=\'" + day + "\'\n" +
                "group by cube (od.del_status, so.day, e.empname)";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        System.out.println("Delivery Status \t Day \t\t Employee Name ");

        while(resultSet.next()){
            String status = resultSet.getString(1);
            String days = resultSet.getString(2);
            String emp = resultSet.getString(3);

            System.out.println(status + "\t\t\t" + days + "\t\t" + emp /*+ "\t\t" + cnt*/);
        }
        st.close();
    }

    public boolean searchAddr(String addr) throws SQLException {
        String query = "select * from fall22_s004_9_customer_address where  streetno LIKE \'%" + addr + "%\'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            return true;
        }
        st.close();
        return false;
    }

    public boolean searchOrderStatus(String ordStatus) throws SQLException {
        String query = "select * from fall22_s004_9_order_details where  del_status= \'" + ordStatus + "\'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            return true;
        }
        st.close();
        return false;
    }

    public void adhocProductAddr(String query) throws  SQLException {
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        while (resultSet.next()) {
            String product = resultSet.getString(1);
            String count = resultSet.getString(2);
            System.out.println("product: "+product+"\t\t\t\t\t"+"Total sales: "+count);
        }

        st.close();
    }

    public  void getdayanalysis() throws SQLException {
        System.out.println("Enter a day to generate the highest sold products: ");
        String day = scan.next();
        String query = "select p.pname,so.day,d.deptname from fall22_s004_9_product p, fall22_s004_9_sales_order so, fall22_s004_9_product_sales ps,\n" +
                "fall22_s004_9_sales s,fall22_s004_9_department d, fall22_s004_9_product_pname_sellingprice_quantity q\n" +
                "where p.pid = ps.pid and ps.sid = s.sid and p.deptid = d.deptid and so.day in (select day from fall22_s004_9_sales_order where so.day = \'" +day+ "\')\n" +
                "group by p.pname,so.day, d.deptname\n" +
                "order by (select sum( po.quantity) from fall22_s004_9_product_order po,fall22_s004_9_sales_order so where so.oid = po.oid) desc \n" +
                "fetch first 5 rows only";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.println(rs.next());
        while(rs.next()){
            String name = rs.getString(1);
            String alldays = rs.getString(2);
            String dept = rs.getString(3);
            System.out.println("Name: "+name+"\t\t\t\t\t"+"Day: "+alldays+"\t\t\t\t\t"+"department: "+dept);
        }
        st.close();
    }
}








