package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class BookingController {

    private Collection<Booking>allBookings;
    private ProviderController providerController;
    // "temporary development measure" to ensure uniqueness of IDs
    private Integer IdCounter;
    
    
    public BookingController(Collection<Booking> allBookings, ProviderController providerController) {
        
        this.allBookings = allBookings;
        this.providerController = providerController;
        this.IdCounter = 0;
    }
    
    public Collection<Quote> getQuotes (Map<BikeType, Integer> types, DateRange dateRange, Location location) {
        
        Collection<Quote> quotes = new HashSet<>();
        
        for (Provider provider : this.providerController.getAllProvidersInTheArea(location)) {
            quotes.addAll(provider.produceOffer(types, dateRange));
        }
        
        return quotes;
    }
    
    public void addBooking(Booking booking) {
        this.allBookings.add(booking);
    }
    
    //assumes the customer has provided details and preferences
    public Invoice bookQuote(Quote quote, Customer customer, boolean isDelivery) {
        
        addBooking(new Booking(customer, quote.getProvider(), this.IdCounter, quote.getBikes(), 
                quote.getDateRange(), quote.getPrice(), quote.getDeposit(), isDelivery));
        
        ++this.IdCounter;
        
        if (isDelivery) {
            
            DeliveryService deliveryService = quote.getProvider().getDeliveryService();
            for (Bike bike : quote.getBikes()) {
                deliveryService.scheduleDelivery(
                        bike,
                        quote.getProvider().getAddress(),
                        customer.getAddress(),
                        quote.getDateRange().getStart());
            }
        }
        
        return null;
    }
}
