package project;

import java.sql.*;
import java.util.Scanner;

public class BalanceCheck {
    public void checkbalance() throws SQLException {
        Scanner sc = new Scanner(System.in);

        // Connect to your database
        Connection c = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bankdb",
            "root",
            "1234"
        );
        Statement st = c.createStatement();

        System.out.println("Enter Account Number:");
        int acc_number = sc.nextInt();

        // Query to get the current balance
        String query = "SELECT Available_balance FROM Bank WHERE Account_number = " + acc_number;
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            System.out.println("Current Balance: ₹" + rs.getDouble(1));
        } else {
            System.out.println("Account not found!");
        }

        c.close();
    }
}
