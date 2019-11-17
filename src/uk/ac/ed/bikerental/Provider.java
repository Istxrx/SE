package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

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
        // create Hashtable of bikesInStock for dateRange
        Hashtable<BikeType,Integer> bikesInStock = new Hashtable<>();
        Integer addOneMore;
        for(Bike bicycle : bikes) {
                if (bicycle.isAvailableOn(dateRange)) {
                    if (bikesInStock.get(bicycle.getType())==null) {
                        addOneMore=1;
                    }else {
                        addOneMore=bikesInStock.get(bicycle.getType())+1;
                    }
            
                    bikesInStock.put(bicycle.getType(), addOneMore);
                }
        }
        
        int satisfy = 0;
        // check if provider can satisfy quote
        Set<BikeType> keys = new HashSet<>(types.keySet());
        for(BikeType key : keys) {
            if (bikesInStock.get(key)>=types.get(key)) {
                satisfy=+1;
            } 
        }
        if (satisfy != types.size()) {
            return null;
        }
        
        //return quotes
        ArrayList<ArrayList<Bike>> allTypeCombinations= new ArrayList<>();
        for(BikeType key : keys) {
            ArrayList<Bike> sameType =
                    new ArrayList<>(this.getBikesOfSameTypeAvailable(key,dateRange));
            Comb.makeCombination(sameType,sameType.size(),types.get(key),allTypeCombinations);
            }
        return null;
    }
    
<<<<<<< HEAD
   
    public Collection<Bike> getBikesOfSameTypeAvailable(BikeType type,DateRange dateRange){
        Collection<Bike> sameType = new ArrayList<>();
        for (Iterator iterator = bikes.iterator(); iterator.hasNext();) {
            Bike bike = (Bike) iterator.next();
            if (bike.getType().equals(type) && bike.isAvailableOn(dateRange)) {
                sameType.add(bike);
            }
        }
        return sameType;
    }
    
    
=======
    public static ArrayList<ArrayList<Object>> getCombinations
        (Collection<Collection<Object>>A, Collection<Collection<Object>>B){
        
        ArrayList<ArrayList<Object>> C = new ArrayList<ArrayList<Object>>();
        
        for (Collection<Object> a : A) {
            for (Collection<Object> b : B) {
                ArrayList<Object> newList = new ArrayList<Object>();
                newList.addAll(a);
                newList.addAll(b);
                C.add(newList);
            }
        }
        return C;
        
    }
>>>>>>> branch 'master' of https://github.com/samuel117/SE
    
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
