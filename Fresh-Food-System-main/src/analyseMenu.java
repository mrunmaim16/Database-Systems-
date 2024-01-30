import java.sql.SQLException;
import java.util.Scanner;

public class analyseMenu {
    Scanner scan = new Scanner(System.in);
    public void analyseOrders(DatabaseConn db) throws SQLException {
        db.analyseOrderModeOfPayment();
    }
    public void analyseSales(DatabaseConn db) throws SQLException {
        System.out.println("Enter order type");
        String orderType = scan.next();
        System.out.println("Enter Month ");
        String month = scan.next();
        db.analyseMonthlySales(orderType, month);
    }
    public void analyseDelivery(DatabaseConn db) throws SQLException {
        System.out.println("Enter the status of the delivery \'Delivered\' or \'In_progress\'");
        String delStatus = scan.next();
        System.out.println("Enter Day ");
        String day = scan.next();
        db.analyseDailyDelivery(delStatus, day);
    }

    public void getdayanalysisDetails(DatabaseConn db) throws SQLException {
        System.out.println("Analysis on which department has more product sales on a given day");
        db.getdayanalysis();
    }
}
