package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProvider {

    private Provider p1;
    private Collection<Bike> bikes1, bikes2;
    private Hashtable<BikeType, Integer> types1;

    
    @BeforeEach
    void setUp() throws Exception {
        
        bikes1 = new HashSet<>();
        bikes1.add(new Bike(
                1,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"mountain"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes1.add(new Bike(
                2,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"mountain"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes1.add(new Bike(
                3,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"mountain"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes1.add(new Bike(
                4,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"ebike"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes1.add(new Bike(
                5,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"ebike"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes1.add(new Bike(
                6,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"ebike"), 
                new HashSet<DateRange>(), 
                "shop"));
        types1 = new Hashtable<>();
        types1.put(new BikeType(null,"mountain"), 2);
        types1.put(new BikeType(null,"ebike"), 2);
        
        DefaultPricingPolicy DPP = new DefaultPricingPolicy();
        DPP.setDailyRentalPrice(new BikeType(null,"mountain"), new BigDecimal("10"));
        DPP.setDailyRentalPrice(new BikeType(null,"ebike"), new BigDecimal("5"));
        
        p1 = new Provider("Provider", null, null, null, null, 
                bikes1, 
                new DefaultValuationPolicy(new BigDecimal("0.1")), 
                DPP, 
                null);
        
        
        
    }
    
    @Test
    void testOffer() {
        DateRange d1 = new DateRange(LocalDate.of(2019, 1, 7),LocalDate.of(2019, 1, 10));
        int a = p1.produceOffer(types1, d1).size();
        assertEquals(9,a); 
    }
}
