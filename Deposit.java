package project;

import java.sql.*;
import java.util.*;

public class Deposit {
    public void Depositbalance() throws SQLException {
        Scanner sc = new Scanner(System.in);

        // Connect to your database
        Connection c = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bankdb",
            "root",
            "1234"
        );
        Statement st = c.createStatement();

        System.out.println("Enter Account Number:");
        int a = sc.nextInt();

        System.out.println("Enter Amount to Deposit:");
        double d = sc.nextDouble();

        // Fetch the current balance from Bank table
        String s = "SELECT Available_balance FROM Bank WHERE Account_number = " + a;
        ResultSet rs = st.executeQuery(s);

        // Update the balance
        if (rs.next()) {
            double newBalance = rs.getDouble(1) + d;

            String updateQuery = "UPDATE Bank SET Available_balance = " + newBalance +
                                 " WHERE Account_number = " + a;
            st.executeUpdate(updateQuery);

            System.out.println("Deposited Successfully! New Balance: " + newBalance);
        } else {
            System.out.println("Account not found!");
        }

        // Always close your connection
        c.close();
    }
}
