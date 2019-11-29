package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class BookingController {

    private Collection<Booking>allBookings;
    private ProviderController providerController;
    // "temporary development measure" to ensure uniqueness of IDs
    private Integer IdCounter;
    
    
    public BookingController(Collection<Booking> allBookings,
            ProviderController providerController) {
        
        this.allBookings = allBookings;
        this.providerController = providerController;
        this.IdCounter = 0;
    }
    
    public Booking getBookingByID(Integer ID) {
        
        for (Booking booking : allBookings) {
            if (booking.getID() == ID) {
                return booking;
            }
        }
        return null;
    }
    
    public Collection<Quote> getQuotes (Map<BikeType, Integer> types, DateRange dateRange,
            Location location) {
        
        Collection<Quote> quotes = new HashSet<>();
        Collection<Provider> providers = this.providerController.getAllProvidersInTheArea(location);
        
        for (Provider provider : providers) {
            Collection<Quote> quotesFromProvider = provider.produceOffer(types, dateRange);
            quotes.addAll(quotesFromProvider);
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
        
        for (Bike bike : quote.getBikes()) {
            
            bike.makeUnavailable(quote.getDateRange());
        }
        
        Invoice summary = new Invoice(IdCounter,quote);
        ++this.IdCounter;
        
        return summary;
    }
}
