package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Quote {

    private Provider provider;
    private Collection<Bike> bikes;
    private BigDecimal price;
    private BigDecimal deposit; 
    private DateRange dateRange;
    
    public Quote(Provider provider, Collection<Bike> bikes, BigDecimal price, BigDecimal deposit,
            DateRange dateRange) {
        this.provider = provider;
        this.bikes = bikes;
        this.price = price;
        this.deposit = deposit;
        this.dateRange = dateRange;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public Collection<Bike> getBikes() {
        return this.bikes;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getDeposit() {
        return this.deposit;
    }
    
    public DateRange getDateRange() {
        return this.dateRange;
    }
}
