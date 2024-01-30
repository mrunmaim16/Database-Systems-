import java.sql.SQLException;
import java.util.Scanner;

public class updateMenu {
    public void updatePayment(DatabaseConn db) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter new payment method for updation: ");
        String pay = scan.next();
        db.updateModeOfPayment(pay);
    }

    public void updateOrder(DatabaseConn db) throws SQLException {
        db.updateOrdertype();
    }

    public void updateDeliveryDetails(DatabaseConn db) throws SQLException {
        db.updateDelStatus();
    }

}
