/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torontomu.ca.stocktransactions.persistence;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import torontomu.ca.stocktransactions.helper.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
/**
 *
 * @author student
 */
@WebServlet(name = "Personal_Accounts_CRUD", urlPatterns = {"/Personal_Accounts_CRUD"})
public class Personal_Accounts_CRUD extends HttpServlet {
    
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
   
    public static AccountData getAccountData(String primaryUser) {
    AccountData accountData = null;
    System.out.print("Accessing Query");
    try {
        Connection connection = getConnection();
        String query = "SELECT * FROM Personal_Accounts WHERE PrimaryUser = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, primaryUser); // Set the primaryUser parameter
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int accountID = resultSet.getInt("AccountID");
            int accountPortfolio = resultSet.getInt("AccountPortfolio");
            String portfolioType = resultSet.getString("PortfolioType");
            double availableFunds = resultSet.getDouble("AvailableFunds");
            double totalBalance = resultSet.getDouble("TotalBalance");
            double allTimeReturn = resultSet.getDouble("AllTimeReturn");
            accountData = new AccountData(accountID, accountPortfolio, portfolioType, primaryUser, availableFunds, totalBalance, allTimeReturn);
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
    }
    return accountData;
}
    public static boolean UpdatePersonalAccountData(AccountData accountData) {
    boolean success = false;
    System.out.println("Accessing query for update Account Data");
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = getConnection();
        String query = "UPDATE Personal_Accounts SET AvailableFunds = ? WHERE PrimaryUser = ?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setDouble(1, accountData.getAvailableFunds());
        preparedStatement.setString(2, accountData.getPrimaryUser()); // Assuming PrimaryUser is unique

        int rowsAffected = preparedStatement.executeUpdate();
        success = rowsAffected > 0;

    } catch (SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
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