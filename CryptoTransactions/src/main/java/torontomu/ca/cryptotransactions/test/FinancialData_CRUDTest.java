package torontomu.ca.cryptotransactions.test;

import torontomu.ca.cryptotransactions.helper.FinancialData;
import torontomu.ca.cryptotransactions.persistence.FinancialData_CRUD;
import java.io.IOException;
import java.io.PrintWriter;  // Import statement for PrintWriter

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FinancialData_CRUDTest")
public class FinancialData_CRUDTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        List<FinancialData> financialDataList = FinancialData_CRUD.getAllFinancialData();

        if (financialDataList != null && !financialDataList.isEmpty()) {
            out.println("The list contains financial data. Here are the details:");

            for (FinancialData financialData : financialDataList) {
                out.println("DataID: " + financialData.getDataID() +
                            ", Volume: " + financialData.getVolume() +
                            ", Price: " + financialData.getPrice() +
                            ", Symbol: " + financialData.getSymbol() +
                            ", Watchlist: " + financialData.isWatchlist() +
                            ", DName: " + financialData.getDName() +
                            ", TheType: " + financialData.getTheType());
            }
        } else {
            out.println("The list is null or empty.");
        }
    }
}
