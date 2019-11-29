package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Invoice {
    
    private Integer uniqueID;
    private Provider provider;
    private Collection<Bike> bikes;
    private BigDecimal price;
    private BigDecimal deposit; 
    private DateRange dateRange;
    
    public Invoice(Integer uniqueID, Quote quote) {
        this.uniqueID = uniqueID;
        this.provider = quote.getProvider();
        this.bikes = quote.getBikes();
        this.price = quote.getPrice();
        this.deposit = quote.getDeposit();
        this.dateRange = quote.getDateRange();
    }
    

}
