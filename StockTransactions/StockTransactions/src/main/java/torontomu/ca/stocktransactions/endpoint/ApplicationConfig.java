package torontomu.ca.stocktransactions.endpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

@ApplicationPath("api") // This sets the path to access the API. e.g., http://localhost:8080/[YourAppContext]/api
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(torontomu.ca.stocktransactions.endpoint.StockResource.class);
        
    }
}
