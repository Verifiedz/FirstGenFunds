package torontomu.ca.cryptotransactions.persistence;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import torontomu.ca.cryptotransactions.helper.*;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;

/**
 *
 * @author student
 */
@WebServlet(name = "Transaction_CRUD", urlPatterns = {"/Transaction_CRUD"})

public class Transaction_CRUD extends HttpServlet {

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRYPTOTRANS_FGF?autoReconnect=true&useSSL=false", "root", "student123");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;

    }
        public static boolean UpdateTransactionData(double amount, String type, double price, String timeStamp) {
        boolean success = false;
        System.out.println("Accessing query");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "UPDATE Transactions SET Amount = ?, Tran_Type = ?, Price = ?, Dates = ? WHERE SomeIdColumn = ?";

            preparedStatement = connection.prepareStatement(query);

        
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, type);
            preparedStatement.setDouble(3, price);
            preparedStatement.setString(4, timeStamp);
           
            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }
    public static boolean createTransaction(int portfolioPortfolioID, double amount, double price, String tranType) {
    boolean success = false;
    System.out.println("Inserting new transaction into the database");
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = getConnection();
        String query = "INSERT INTO Transactions (Dates, Amount, Tran_Type, Status, PortfolioPortfolioID, Price) VALUES (NOW(), ?, ?, 'Pending', ?, ?)";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setDouble(1, amount);
        preparedStatement.setString(2, tranType);
        preparedStatement.setInt(3, portfolioPortfolioID);
        preparedStatement.setDouble(4, price);

       
        int rowsAffected = preparedStatement.executeUpdate();
        success = rowsAffected > 0;

    } catch (Exception e) {
        System.out.println(e);
    } finally {
     
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return success;
}


}
