import java.sql.SQLException;
import java.util.Scanner;

public class searchMenu {
    public void searchEmployeeSwitchCases(DatabaseConn db) throws SQLException {
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("To go back to menu, enter back");
            if(scan.next().equals("back"))
                break;
            System.out.println("To Search Employee ID, Enter id");
            System.out.println("To Search Employee Name, Enter name");
            System.out.println("To Search All Employees, Enter all");
            System.out.println("To go back, Enter back");
            System.out.println("To Exit, Enter exit");

            System.out.println("Enter your choice::");
            String choice = scan.next();

            switch(choice) {
                case "id": {
                    System.out.println("Search by ID:");
                    db.searchEmpID();
                    break;
                }

                case "name":{
                    System.out.println("Search by Name:");
                    db.searchEmpName();
                    //db.getAdhocQueryDetails();
                    break;
                }
                case "all": {
                    System.out.println("Search all data:");
                    db.getAllEmployees();
                    //method call or logic for case 1
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

    public void searchCustomerSwitchCases(DatabaseConn db) throws SQLException {
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("To go back to menu, enter back");
            if(scan.next().equals("back"))
                break;
            System.out.println("To Search Customer ID, Enter id");
            System.out.println("To Search Customer Name, Enter name");
            System.out.println("To Search All Customers, Enter all");
            System.out.println("To go back, Enter back");
            System.out.println("To Exit, Enter exit");

            System.out.println("Enter your choice::");
            String choice = scan.next();

            switch(choice) {
                case "id": {
                    System.out.println("Search by ID:");
                    db.searchCustID();
                    break;
                }
                case "name":{
                    System.out.println("Search by Name:");
                    db.searchCustName();
                    break;
                }
                case "all": {
                    System.out.println("Search all data:");
                    db.getAllCustomers();
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
}
