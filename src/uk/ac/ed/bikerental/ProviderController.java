package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;

public class ProviderController {

    private Collection<Provider> allProviders;
    private BookingController bookingController;
    
    public Collection<Provider> getAllProviders() {
        return this.allProviders;
    }
    
    public Collection<Provider> getAllProvidersInTheArea(Location address) {
        Collection<Provider> providersInTheArea = new ArrayList<>();
        for(Provider Provider : allProviders ) {
            if (Provider.getAddress().isNearTo(address)){
                providersInTheArea.add(Provider);
            }
        }
        return providersInTheArea;
    }
}
