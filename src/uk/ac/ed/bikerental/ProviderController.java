package uk.ac.ed.bikerental;

import java.util.Collection;

public class ProviderController {

    private Collection<Provider> allProviders;
    private BookingController bookingController;
    
    public Collection<Provider> getAllProviders() {
        return this.allProviders;
    }
}
