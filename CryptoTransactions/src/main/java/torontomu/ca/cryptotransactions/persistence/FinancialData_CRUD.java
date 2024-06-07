/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "FinancialData_CRUD", urlPatterns = {"/FinancialData_CRUD"})
public class FinancialData_CRUD extends HttpServlet {
    
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
    public static List<FinancialData> getAllFinancialData() {
    List<FinancialData> financialDatas = new ArrayList<>();
    try {
        Connection connection = getConnection();
        String query = "SELECT fd.* FROM FinancialData fd JOIN Crypto c ON fd.DataID = c.FinancialDataDataID";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int dataID = resultSet.getInt("DataID");
            double volume = resultSet.getDouble("Volume");
            double price = resultSet.getDouble("Price");
            String symbol = resultSet.getString("Symbol");
            boolean watchlist = resultSet.getBoolean("Watchlist");
            String dName = resultSet.getString("Dname");
            String theType = resultSet.getString("The_type");

            FinancialData financialData = new FinancialData(dataID, volume, price, symbol, watchlist, dName, theType);
            financialDatas.add(financialData);
        }
        connection.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return financialDatas;
}

    
}
