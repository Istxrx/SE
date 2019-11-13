package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Hashtable;

public class DefaultPricingPolicy implements PricingPolicy{
    
    private Hashtable<BikeType,BigDecimal> pricingList;

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        pricingList.put(bikeType, dailyPrice);
    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        BigDecimal totalPrice = new BigDecimal("0");
        BigDecimal numberOfDays = new BigDecimal(duration.toDays());
        
        for (Bike bike : bikes) {
            BikeType type = bike.getType(); 
            BigDecimal dailyPrice = pricingList.get(type);
            totalPrice.add(dailyPrice.multiply(numberOfDays));
            
        }
        return totalPrice;
    }

}
