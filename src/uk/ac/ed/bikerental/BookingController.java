package uk.ac.ed.bikerental;

import java.util.Collection;

public class BookingController {

    private Collection<Booking>allBookings;
    private ProviderController providerController;
    
    public BookingController(Collection<Booking> allBookings, ProviderController providerController) {
        
        this.allBookings = allBookings;
        this.providerController = providerController;
    }
    
    
    
}
