package torontomu.ca.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import torontomu.ca.helper.UserData;
import torontomu.ca.helper.UserDatasXML;
import javax.ws.rs.core.Response;

/**
 * Business logic class for the financial portfolio frontend.
 * This class handles authentication, service retrieval, and user signup.
 */
public class Business {

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the user is authenticated, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    public static boolean isAuthenticated(String username, String password) throws IOException {
        // Make API call to authentication endpoint
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/SignUp-1.0-SNAPSHOT/webresources/signup/login");
        Invocation.Builder invocationBuilder = webTarget
                .queryParam("username", username)
                .queryParam("password", password)
                .request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.post(null);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Signs up a new user with the provided information.
     *
     * @param fullName The full name of the user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @param phone The phone number of the user.
     * @return true if the signup is successful, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    public static boolean signupUser(String fullName, String email, String password, String phone) throws IOException {
        // Make API call to signup endpoint
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/SignUp-1.0-SNAPSHOT/webresources/signup/signup");
        Invocation.Builder invocationBuilder = webTarget
                .queryParam("fullName", fullName)
                .queryParam("email", email)
                .queryParam("password", password)
                .queryParam("phone", phone)
                .request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.post(null);
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves services based on the provided query and authentication token.
     *
     * @param query The query to search for services.
     * @param token The authentication token of the user.
     * @return The retrieved services.
     * @throws IOException If an I/O error occurs.
     */
    public static UserDatasXML getServices(String query, String token) throws IOException {
        try {
            // Make API call to services endpoint
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target("http://localhost:8080/SignUp-1.0-SNAPSHOT/webresources/signup")
                    .path(query)
                    .queryParam("token", token);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
            Response response = invocationBuilder.get();
            InputStream is = response.readEntity(InputStream.class);
            String xml = IOUtils.toString(is, "UTF-8");
            return parseXmlToUserDatas(xml);
        } catch (JAXBException e) {
            // Handle JAXBException
            e.printStackTrace(); // or any other error handling mechanism
            return null; // or throw a custom exception
        }
    }

    /**
     * Parses the XML string to UserDatasXML object.
     *
     * @param xml The XML string to parse.
     * @return The parsed UserDatasXML object.
     */
    private static UserDatasXML parseXmlToUserDatas(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserDatasXML.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (UserDatasXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
    }
}
