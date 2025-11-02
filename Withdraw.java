package project;

import java.sql.*;
import java.util.Scanner;

public class Withdraw {
    public void withdrawAmount() throws SQLException {
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

        System.out.println("Enter Amount to Withdraw:");
        double d = sc.nextDouble();

        String q = "SELECT Available_balance FROM Bank WHERE Account_number = " + a;
        ResultSet rs = st.executeQuery(q);

        if (rs.next()) {
            double currentBalance = rs.getDouble(1);

            if (currentBalance < d) {
                System.out.println("❌ Insufficient Funds! Current Balance: ₹" + currentBalance);
            } else {
                double newBalance = currentBalance - d;
                String updateQuery = "UPDATE Bank SET Available_balance = " + newBalance +
                                     " WHERE Account_number = " + a;
                st.executeUpdate(updateQuery);
                System.out.println("✅ Withdraw Successful! New Balance: ₹" + newBalance);
            }
        } else {
            System.out.println("Account not found!");
        }

        c.close();
    }
}
