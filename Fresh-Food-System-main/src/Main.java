import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        menu mn = new menu();
        DatabaseConn db = new DatabaseConn();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("To Search Enter 1");
            System.out.println("To Update Enter 2");
            System.out.println("To get reports Enter 3");
            System.out.println("To Exit, Enter exit");

            System.out.println("Enter your choice::");
            String choice = scan.next();

            switch(choice) {
                case "1": {
                    System.out.println("I am Search");
                    mn.searchSwitchCases(db);
                    break;
                }
                case "2": {
                    System.out.println("I am Update");
                    mn.updateSwitchCases(db);
                    break;
                }
                case "3": {
                    System.out.println("I am Analyze");
                    mn.analyseSwitchCases(db);
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