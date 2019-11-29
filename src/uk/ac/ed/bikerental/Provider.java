package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
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
    private BigDecimal depositRate;

    private DeliveryService deliveryService;
    private BookingController bookingController;

    public Provider(String name, Integer iD, Location address, String phoneNumber,
            Collection<Provider> partners, Collection<Bike> bikes, ValuationPolicy valuationPolicy,
            PricingPolicy pricingPolicy,BigDecimal depositRate, DeliveryService deliveryService,
            BookingController bookingController) {

        this.name = name;
        this.ID = iD;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.partners = partners;
        this.bikes = bikes;
        this.valuationPolicy = valuationPolicy;
        this.pricingPolicy = pricingPolicy;
        this.depositRate = depositRate;
        this.deliveryService = deliveryService;
        this.bookingController = bookingController;
        
        for (Bike bike : bikes) {
            bike.setOwner(this);
        }
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
    
    public DeliveryService getDeliveryService() {
        return this.deliveryService;
    }

    public boolean isPartnerOf(Provider other) {
        return this.partners.contains(other);
    }
    
    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public void addPartner(Provider other) {
        this.partners.add(other);
    }

    // assumes location is matching already
    public Collection<Quote> produceOffer(Map<BikeType, Integer> types, DateRange dateRange) {
        
        Collection<Quote> quotes = new HashSet<>();
        
        // check if provider can satisfy quote
        Collection<BikeType> wantedTypes = new HashSet<>(types.keySet());
        for (BikeType wantedType : wantedTypes) {
            if(!(this.getBikesOfSameTypeAvailable(wantedType, dateRange).size()>=
                    types.get(wantedType))){
               return quotes; 
            }  
        }

        //continue to return quotes
        //splits available bikes into groups of same type
        
        ArrayList<ArrayList<Bike>> bikesOfTypes = new ArrayList<>();
        
        for(BikeType wantedType : wantedTypes) {
            ArrayList<Bike> bikesOfSameType =
                    new ArrayList<>(this.getBikesOfSameTypeAvailable(wantedType,dateRange));
            bikesOfTypes.add(bikesOfSameType);
            }
        
        //in each group of same type, create all possible r-combinations from this group
        
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
        
        //combine elements from each group to form final tuples
        
        ArrayList<ArrayList<Bike>> finalCombinations = 
                new ArrayList<>(rCombinationsOfBikesOfTypes.get(0));
        
        for (int i = 1; i < rCombinationsOfBikesOfTypes.size(); i++) {
            finalCombinations = 
                    Combinations.getCombinations(
                            finalCombinations,rCombinationsOfBikesOfTypes.get(i));
        }
        
        //create quotes for each corresponding tuple of combinations
        
        for (ArrayList<Bike> combination : finalCombinations) {
            quotes.add(new Quote(this, 
                            combination, 
                            calculatePrice(combination, dateRange), 
                            calculateDeposit(combination),
                            dateRange));
                            
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

    public void acceptReturn(Integer bookingID) {
        
        Booking booking = this.bookingController.getBookingByID(bookingID);
        Collection<Bike> returnedBikes = booking.getBikes();
        // if these are own bikes
        if (this == booking.getProvider()) {
            for (Bike bike : returnedBikes) {
                bike.updateStatus();
            }
        }
        // they belong to one of partners
        else {
            for (Bike bike : returnedBikes) {
                bike.updateStatus("with partner");
                deliveryService.scheduleDelivery(bike, this.address, bike.getOwner().getAddress(),
                        LocalDate.now());
            }
        }
    }
    
    public void registerPickup(Collection<Bike> bikes) {
        for (Bike bike : bikes) {
            bike.onPickup();
            bike.onDropoff();
        }
    }

    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        return this.pricingPolicy.calculatePrice(bikes, duration);
    }
    
    public BigDecimal calculateDeposit(Collection<Bike> bikes) {
        BigDecimal price = new BigDecimal("0");
        for (Bike bike : bikes) {
            price = price.add(depositRate.multiply(this.valuationPolicy.calculateValue(bike,
                    LocalDate.now())));
        }
        return price;
    }
}
    

