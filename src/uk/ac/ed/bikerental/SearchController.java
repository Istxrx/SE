package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class SearchController {
    
    private ProviderController providerController;
    private BookingController bookingController;
    private Collection<Quote> resultOfLastSearch;
        
    public SearchController(ProviderController providerController, BookingController bookingController) {
        this.providerController = providerController;
        this.bookingController = bookingController;
        this.resultOfLastSearch = null;
    }

    public void getQuotes (Map<BikeType, Integer> types, DateRange dateRange, Location location) {
        
        Collection<Quote> quotes = new HashSet<>();
        
        for (Provider provider : this.providerController.getAllProvidersInTheArea(location)) {
            quotes.addAll(provider.produceOffer(types, dateRange));
        }
        
        this.resultOfLastSearch = quotes;
    }

    
}
