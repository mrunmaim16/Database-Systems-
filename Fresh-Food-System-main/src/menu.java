import java.sql.SQLException;
import java.util.Scanner;

public class menu {
    public void searchSwitchCases(DatabaseConn db) throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        searchMenu sm = new searchMenu();
        while(true){
            System.out.println("To go back to menu, enter back");
            if(scan.next().equals("back"))
                break;

            System.out.println("To Search Employee, Enter emp");
            System.out.println("To Search Customer, Enter cust");
            System.out.println("To Exit, Enter exit");

            System.out.println("Enter your choice::");
            String choice = scan.next();

            switch(choice){
                case "emp": {
                    System.out.println("Search Employee");
                    sm.searchEmployeeSwitchCases(db);
                    break;
                }
                case "cust": {
                    System.out.println("Search Customer");
                    sm.searchCustomerSwitchCases(db);
                    break;
                }
                case "exit": {
                    System.out.println("Exiting the application");
                    db.closeConnection();
                    System.exit(0);
                }
                default: System.out.println("Incorrect input! Please re-enter choice from our menu");
            }
        }
    }

    public void updateSwitchCases(DatabaseConn db) throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        updateMenu um = new updateMenu();
        while(true){
            System.out.println("To go back to menu, enter back");
            if(scan.next().equals("back"))
                break;
            System.out.println("To update an Order, Enter ord");
            System.out.println("To update a Payment methods for orders with count less than 10, Enter pay");
            System.out.println("To update an Delivery details, Enter del");
            System.out.println("To Exit, Enter exit");

            System.out.println("Enter your choice::");
            String choice = scan.next();

            switch(choice){
                case "ord": {
                    System.out.println("Order: ");
                    um.updateOrder(db);
                    break;
                }
                case "pay": {
                    System.out.println("Payment methods:");
                    um.updatePayment(db);
                    break;
                }
                case "del": {
                    System.out.println("Delivery details:");
                    um.updateDeliveryDetails(db);
                    break;
                }
                case "exit": {
                    System.out.println("Exiting the application");
                    db.closeConnection();
                    System.exit(0);
                }
                default: System.out.println("Incorrect input! Please re-enter choice from our menu");
            }
        }
    }

    public void analyseSwitchCases(DatabaseConn db) throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        analyseMenu am = new analyseMenu();
        while(true){
            System.out.println("To go back to menu, enter back");
            if(scan.next().equals("back"))
                break;
            System.out.println("To get report on Order details, Enter ord");
            System.out.println("To get the report on monthly sales, enter sales");
            System.out.println("To get the report on daily delivery details, enter del");
            System.out.println("To get the report on Product details of an customer Address, enter prod");
            System.out.println("To get the report on Department wise sales, enter dept");
            System.out.println("To Exit, Enter exit");

            System.out.println("Enter your choice::");
            String choice = scan.next();

            switch(choice){
                case "ord": {
                    System.out.println("Orders:");
                    am.analyseOrders(db);
                    break;
                }
                case "sales": {
                    System.out.println("Sales:");
                    am.analyseSales(db);
                    break;
                }
                case "del": {
                    System.out.println("Delivery details:");
                    am.analyseDelivery(db);
                    break;
                }
                case "prod": {
                    System.out.println("Product details:");
                    analyseProductCustomers(db);
                    break;
                }
                case "dept": {
                    System.out.println("Department sales:");
                    am.getdayanalysisDetails(db);
                    break;
                }
                case "exit": {
                    System.out.println("Exiting the application");
                    db.closeConnection();
                    System.exit(0);
                }
                default: System.out.println("Incorrect input! Please re-enter choice from our menu");
            }
        }
    }

    public void analyseProductCustomers(DatabaseConn db) throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        String orderStatus = "";
        String addr = "";
        while(true){
            System.out.println("To go back to menu, enter back");
            if(scan.next().equals("back"))
                break;
            System.out.println("To exit , enter exit");
            if(scan.next().equals("exit")) {
                System.out.println("Exiting the application");
                db.closeConnection();
                System.exit(0);
            }

            if(orderStatus == "") {
                System.out.println("Enter order status Delivered or In_Progress");
                orderStatus=scan.next();
                if(!db.searchOrderStatus(orderStatus)) {
                    System.out.println("Enter a valid order status");
                    orderStatus="";
                }
            }
            if(orderStatus != "" && addr == "") {
                System.out.println("Enter customer address");
                scan.nextLine();
                addr = scan.nextLine();
                addr = addr.replace(" ","%");
                System.out.println("addr "+addr);
                if(!db.searchAddr(addr)) {
                    System.out.println("Please enter a valid address");
                    addr="";
                } else {
                    String query = " select p.pName, COUNT(p.pName) as Total_sales_in_Mitchell_St from FALL22_S004_9_product_order pr\n" +
                            "INNER JOIN FALL22_S004_9_product p ON p.pid=pr.pid " +
                            "where pr.oid IN ( SELECT ord.oid from FALL22_S004_9_order_details ord where ord.oid = pr.oid\n" +
                            " AND ord.del_status=\'"+orderStatus+"\' and ord.oid IN ( " +
                            " SELECT o.oid from FALL22_S004_9_orders o where o.oid=ord.oid " +
                            " and o.custID IN (select c.custID from FALL22_S004_9_customer_address c WHERE " +
                            "    c.streetno LIKE \'"+"%"+addr+"%\' " +"))) group by p.pName having count(p.pName) > 1";
                    db.adhocProductAddr(query);
                    break;
                }
            }

        }
    }
}
