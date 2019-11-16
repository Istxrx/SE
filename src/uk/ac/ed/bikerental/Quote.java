package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Quote {

    private Provider provider;
    private Collection<Bike> bikes;
    private BigDecimal price;
    private BigDecimal deposit; 
    
    public Quote(Provider provider, Collection<Bike> bikes, BigDecimal price, BigDecimal deposit) {
        this.provider = provider;
        this.bikes = bikes;
        this.price = price;
        this.deposit = deposit;
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
}
