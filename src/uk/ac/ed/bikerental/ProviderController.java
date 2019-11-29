package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;

public class ProviderController {

    private Collection<Provider> allProviders;
    
    public ProviderController(Collection<Provider> allProviders) {
        this.allProviders = allProviders;
    }

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
    
    public void registerNewProvider(Provider provider) {
        this.allProviders.add(provider);
    }
    
    public void registerPartnership(Provider provider1,Provider provider2) {
        provider1.addPartner(provider2);
        provider2.addPartner(provider1);
    }
}
