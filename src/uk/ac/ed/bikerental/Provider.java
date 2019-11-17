package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Objects;

public class Provider {
    
    private String name;
    private Integer ID;
    private Location address;
    private String phoneNumber;
    
    private Collection<Provider> partners;
    private Collection<Bike> bikes;
    
    private ValuationPolicy valuationPolicy;
    private PricingPolicy pricingPolicy;
    
    private DeliveryService deliveryService;
    
    public Provider(String name, Integer iD, Location address, String phoneNumber, 
            Collection<Provider> partners, Collection<Bike> bikes, ValuationPolicy valuationPolicy, 
            PricingPolicy pricingPolicy, DeliveryService deliveryService) {
        
        this.name = name;
        this.ID = iD;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.partners = partners;
        this.bikes = bikes;
        this.valuationPolicy = valuationPolicy;
        this.pricingPolicy = pricingPolicy;
        this.deliveryService = deliveryService;
    }
    
    @Override
    public boolean equals(Object other) {
        return this.ID.equals(((Provider) other).getID());
    }
   
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(this.ID);
    }
       
    public Integer getID() {
        return this.ID;
    }
    
    public Location getAddress() {
        return this.address;
    }

    public boolean isPartnerOf(Provider other) {
        return this.partners.contains(other);
    }
    
    public void addPartner(Provider other) {
        this.partners.add(other);
    }
    
    // assumes location is matching already
    public Collection<Quote> produceOffer(Hashtable<BikeType,Integer> types, DateRange dateRange){
        return null;
    }
    
    public void acceptReturn(Collection<Bike> returnedBikes) {
        // if these are own bikes
        if (this.bikes.containsAll(returnedBikes)) {
            for (Bike bike : returnedBikes) {
                bike.updateStatus();
            } 
        }
        // they belong to partners
        else {
            for (Bike bike : returnedBikes) {
                bike.updateStatus("with partner");
                deliveryService.scheduleDelivery(bike, this.address, bike.getOwner().getAddress(), 
                        LocalDate.now());
            }
        }
    }

}
