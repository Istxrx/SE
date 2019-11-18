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

    public Provider(String name, Integer iD, Location address, String phoneNumber, Collection<Provider> partners,
            Collection<Bike> bikes, ValuationPolicy valuationPolicy, PricingPolicy pricingPolicy,
            DeliveryService deliveryService) {

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
    public Collection<Quote> produceOffer(Hashtable<BikeType, Integer> types, DateRange dateRange) {
        
        Collection<Quote> quotes = new HashSet<>();
        
        // check if provider can satisfy quote
        Set<BikeType> wantedTypes = new HashSet<>(types.keySet());
        for (BikeType wantedType : wantedTypes) {
            if(!(this.getBikesOfSameTypeAvailable(wantedType, dateRange).size()>=
                    types.get(wantedType))){
               return quotes; 
            }  
        }

        //return quotes
        
        ArrayList<ArrayList<Bike>> bikesOfTypes = new ArrayList<>();
        
        for(BikeType wantedType : wantedTypes) {
            ArrayList<Bike> bikesOfSameType =
                    new ArrayList<>(this.getBikesOfSameTypeAvailable(wantedType,dateRange));
            bikesOfTypes.add(bikesOfSameType);
            }
        
        ArrayList<ArrayList<ArrayList<Bike>>> rCombinationsOfBikesOfTypes = new ArrayList<>();
        
        for (ArrayList<Bike> listOfBikes : bikesOfTypes) {
            
            int r = types.get(listOfBikes.get(0).getType());
            if (r>1){
            rCombinationsOfBikesOfTypes.add(
                    Combinations.getRCombinations(listOfBikes,r));
            }else {
                rCombinationsOfBikesOfTypes.add(Combinations.splitToElements(listOfBikes));
            }
        }
        
        ArrayList<ArrayList<Bike>> finalCombinations = 
                new ArrayList<>(rCombinationsOfBikesOfTypes.get(0));
        
        for (int i = 1; i < rCombinationsOfBikesOfTypes.size(); i++) {
            finalCombinations = 
                    Combinations.getCombinations(
                            finalCombinations,rCombinationsOfBikesOfTypes.get(i));
        }
        
        for (ArrayList<Bike> combination : finalCombinations) {
            quotes.add(
                    new Quote(this, 
                            combination, 
                            this.calculatePrice(combination, dateRange), 
                            this.calculateDeposit(combination)));
        }
        
        return quotes;
    }

    public Collection<Bike> getBikesOfSameTypeAvailable(BikeType type, DateRange dateRange) {
        Collection<Bike> sameType = new ArrayList<>();
        for (Bike bike : bikes) {
            if (bike.getType().equals(type) && bike.isAvailableOn(dateRange)) {
                sameType.add(bike);
            }
        }
        return sameType;
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

    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        return this.pricingPolicy.calculatePrice(bikes, duration);
    }
    
    public BigDecimal calculateDeposit(Collection<Bike> bikes) {
        BigDecimal price = new BigDecimal("0");
        for (Bike bike : bikes) {
            price = price.add(this.valuationPolicy.calculateDeposit(bike, bike.getAge()));
        }
        return price;
    }
}
    

