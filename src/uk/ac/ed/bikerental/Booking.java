package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class Booking {

    private Customer customer;
    private Provider provider;
    private Integer ID;
    private Collection<Bike> bikes;
    private DateRange dateRange;
    private BigDecimal price;
    private BigDecimal deposit;
    private boolean delivery;
    
    public Booking(Customer customer, Provider provider, Integer iD, Collection<Bike> bikes, 
            DateRange dateRange, BigDecimal price, BigDecimal deposit, boolean delivery) {

        this.customer = customer;
        this.provider = provider;
        this.ID = iD;
        this.bikes = bikes;
        this.dateRange = dateRange;
        this.price = price;
        this.deposit = deposit;
        this.delivery = delivery;
    }
    
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(this.ID);
    }
    
    @Override
    public boolean equals(Object other) {
        return this.ID.equals(((Booking) other).getID());
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public Integer getID() {
        return this.ID;
    }

    public Collection<Bike> getBikes() {
        return this.bikes;
    }

    public DateRange getDateRange() {
        return this.dateRange;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getDeposit() {
        return this.deposit;
    }

    public boolean isDelivery() {
        return this.delivery;
    }

}
