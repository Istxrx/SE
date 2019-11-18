package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class SearchController {
    
    private ProviderController providerController;
    private BookingController bookingController;
    
    public Collection<Quote> getQuotes (Map<BikeType, Integer> types, DateRange dateRange) {
        
        Collection<Quote> quotes = new HashSet<>();
        
        for (Provider provider : this.providerController.getAllProviders()) {
            quotes.addAll(provider.produceOffer(types, dateRange));
        }
        
        return quotes;
    }

}
