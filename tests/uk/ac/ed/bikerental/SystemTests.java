package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {
    // You can add attributes here
    
    private Provider p1,p2,p3;
    private Collection<Bike> bikes1, bikes2, bikes3;
    private HashMap<BikeType, Integer> types1,types2,types3;
    private Location location1,location2,location3;
    private ProviderController providerController;
    private BookingController bookingController;

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Put your test setup here
        
        
        //PROVIDER 1
        
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
        
        
        DefaultPricingPolicy DPP1 = new DefaultPricingPolicy();
        DPP1.setDailyRentalPrice(new BikeType(null,"mountain"), new BigDecimal("10"));
        DPP1.setDailyRentalPrice(new BikeType(null,"ebike"), new BigDecimal("5"));
        
        location1 = new Location("EH1 1LG", "Niddry Street 39");
        
        p1 = new Provider("Provider", null, location1, null, null, 
                bikes1, 
                new DefaultValuationPolicy(), 
                DPP1,new BigDecimal("0.1"), 
                null);
        
        //PROVIDER 1 END
        
        //PROVIDER 2
        
        bikes2 = new HashSet<>();
        bikes2.add(new Bike(
                7,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"mountain"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes2.add(new Bike(
                8,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"road"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes2.add(new Bike(
                9,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"ebike"), 
                new HashSet<DateRange>(), 
                "shop"));
             
        
        DefaultPricingPolicy DPP2 = new DefaultPricingPolicy();
        DPP2.setDailyRentalPrice(new BikeType(null,"mountain"), new BigDecimal("10"));
        DPP2.setDailyRentalPrice(new BikeType(null,"ebike"), new BigDecimal("5"));
        
        location2 = new Location("EH7 5QP", "Albion Road 43");
        
        p2 = new Provider("Provider", null, location2, null, null, 
                bikes1, 
                new DefaultValuationPolicy(), 
                DPP2, new BigDecimal("0.1"), 
                null);
        
        //PROVIDER 2 END
        
        //PROVIDER 3
        
        
        bikes3 = new HashSet<>();
        bikes3.add(new Bike(
                10,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"mountain"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes3.add(new Bike(
                11,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"road"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes3.add(new Bike(
                12,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"ebike"), 
                new HashSet<DateRange>(), 
                "shop"));
        bikes3.add(new Bike(
                13,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"road"), 
                new HashSet<DateRange>(), 
                "shop"));
             
        
        DefaultPricingPolicy DPP3 = new DefaultPricingPolicy();
        DPP3.setDailyRentalPrice(new BikeType(null,"mountain"), new BigDecimal("10"));
        DPP3.setDailyRentalPrice(new BikeType(null,"ebike"), new BigDecimal("5"));
        
        location3 = new Location("AB1 5KL", "George Street 12");
        
        p3 = new Provider("Provider", null, location3, null, null, 
                bikes1, 
                new DefaultValuationPolicy(), 
                DPP2,new BigDecimal("0.1"), 
                null);
        
        //PROVIDER 3 END
   
        
        //SEARCH CONTROLLER
        
        
        
        
        
        
        
    }
    
    // TODO: Write system tests covering the three main use cases

    @Test
    void testGetQuotes() {
        types1 = new HashMap<>();
        types1.put(new BikeType(null,"mountain"), 2);
        types1.put(new BikeType(null,"ebike"), 2);
        DateRange dateRange = new DateRange(LocalDate.of(2019, 1, 7),LocalDate.of(2019, 1, 10));
        Location location = new Location("EH7 5KL","Kings Street 5");
        
        
    }
}
