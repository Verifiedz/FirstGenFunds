package torontomu.ca.cryptotransactions.endpoint;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import torontomu.ca.cryptotransactions.helper.FinancialDataXML;
import torontomu.ca.cryptotransactions.helper.FinancialDataWrapper;
import torontomu.ca.cryptotransactions.business.CryptoBusiness;
import torontomu.ca.cryptotransactions.helper.UserNotFoundException;
@Path("crypto")
public class CryptoResource {
    
    private CryptoBusiness cryptobusiness = new CryptoBusiness();

    // Existing trade processing endpoint
     @POST
    @Path("/trade")
    @Consumes(MediaType.APPLICATION_XML)
   public Response processTrade(JAXBElement<TradeRequest> tradeRequestElement) {
    TradeRequest tradeRequest = tradeRequestElement.getValue();
    String status;
    String message;
    String message2 = "";
    double availableFunds = 0;
    
    try {
        // Get the available funds for the user
        
        
        // Process the trade request using your business logic...
        boolean tradeSuccess = cryptobusiness.processTrade(
            tradeRequest.getPrimaryUser(),
            tradeRequest.getDataID(),
            tradeRequest.getVolume(),
            tradeRequest.getPrice(),
            tradeRequest.getAvailableFunds()
        );
        availableFunds = cryptobusiness.getAvailableFunds(tradeRequest.getPrimaryUser());
        
        status = tradeSuccess ? "Success" : "Failure";
        message = tradeSuccess ? "Trade processed successfully" : "Trade could not be processed";
        message2 = tradeSuccess ? "AddedFunds": "Could not Process Request";
        
    } catch (UserNotFoundException e) {
        status = "Failure";
        message = e.getMessage();
        message2 = "User does not exist.";
    }
    
    String type = "purchase"; 
   
    
    TradeResponse response = new TradeResponse(
        status, 
        message, 
        tradeRequest.getDataID(), 
        tradeRequest.getVolume(), 
        tradeRequest.getPrice(),
        type,
        availableFunds,
        message2
    );
    
    String responseXML = FinancialDataXML.toXML(response);
    return Response.ok(responseXML, MediaType.APPLICATION_XML).build();
}

    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_XML)
    public Response getFinancialData() {
        FinancialDataWrapper financialDataWrapper = cryptobusiness.getAllFinancialData();
        String financialDataXML = FinancialDataXML.toXML(financialDataWrapper);
        return Response.ok(financialDataXML, MediaType.APPLICATION_XML).build();
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TradeRequest {
        @XmlElement
        private String primaryUser;
        @XmlElement
        private int dataID;
        @XmlElement
        private double volume;
        @XmlElement
        private double price;
        
        @XmlElement
        private double availablefunds;
        
        public TradeRequest(){
            //default Constructor
        }
        public String getPrimaryUser(){
            return this.primaryUser;
        }
        public int getDataID(){
            return this.dataID;
        }
        public double getVolume(){
            return this.volume;
        }
        public double getPrice(){
            return this.price;
        }
        public double getAvailableFunds(){
            return this.availablefunds;
        }
    }

    @XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public static class TradeResponse {
    @XmlElement
    private String status;
    @XmlElement
    private String message;
    @XmlElement
    private int dataID;
    @XmlElement
    private double volume;
    @XmlElement
    private double price;
    @XmlElement
    private String type;  
     
     @XmlElement
     private double availablefunds;
     @XmlElement
     private String message2;

    
    public TradeResponse() {}

    // Constructor with fields
    public TradeResponse(String status, String message, int dataID, double volume, double price, String type,double availablefunds, String message2) {
        this.status = status;
        this.message = message;
        this.dataID = dataID;
        this.volume = volume;
        this.price = price;
        this.type = type;
        this.availablefunds = availablefunds;
        this.message2 = message2;
    }
    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    
}
    
}
