package torontomu.ca.frontend;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import torontomu.ca.business.Business;
import torontomu.ca.helper.UserDatasXML;

@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        
        switch (hiddenParam) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean isAuthenticated = Business.isAuthenticated(username, password);
                if (isAuthenticated) {
                    // Generate JWT token
                    token = autho.createJWT("FrontEnd", username, 100000);

                    // Add token to cookies
                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);

                    // Forward to frontpageWithLogin.jsp
                    request.setAttribute("username", username);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Menu.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;
            case "signup":
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String password2 = request.getParameter("Password");
                String phone = request.getParameter("phone");

                // Call the Business class method to handle signup
                boolean isSignupSuccessful = Business.signupUser(fullName, email, password2, phone);

                if (isSignupSuccessful) {
                    // If signup is successful, forward to a success page
                    RequestDispatcher successDispatcher = request.getRequestDispatcher("signupSuccess.jsp");
                    successDispatcher.forward(request, response);
                } else {
                    // If signup fails, forward to a failure page
                    RequestDispatcher failureDispatcher = request.getRequestDispatcher("signupFailure.jsp");
                    failureDispatcher.forward(request, response);
                }
                break;
            case "search":
                UserDatasXML result;
                String query = request.getParameter("query");
                if (token.isEmpty()) {
                    result = retrieveServicesFromBackend(query, null);
                    request.setAttribute("bookResults", result);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithoutLogin.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("username", uname);
                    result = retrieveServicesFromBackend(query, token);
                    request.setAttribute("bookResults", result);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithLogin.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;
        }
    }

    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (!token.isEmpty()) {
            try {
                if (this.autho.verify(token).getKey()) {
                    return new AbstractMap.SimpleEntry<>(token, this.autho.verify(token).getValue());
                } else {
                    return new AbstractMap.SimpleEntry<>("", "");
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new AbstractMap.SimpleEntry<>("", "");
    }

    private UserDatasXML retrieveServicesFromBackend(String query, String token) {
        try {
            return Business.getServices(query, token);
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
