package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;


public class SystemTests {
    // You can add attributes here
    
    private Provider p1,p2,p3;
    private Collection<Bike> bikes1, bikes2, bikes3;
    private HashMap<BikeType, Integer> types1,types2,types3;
    private Location location1,location2,location3;
    private ProviderController providerController;
    private BookingController bookingController;
    private Collection<Provider> allProviders;
    private Customer customer;

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Put your test setup here
        allProviders = new ArrayList<Provider>();
        
        providerController = new ProviderController(allProviders);
        
        Collection<Booking> allBookings = new ArrayList<>();
        bookingController = new BookingController(allBookings,providerController);
        
        Customer customer = new Customer(1);
        

        
        
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
        
        p1 = new Provider("Provider", 1, location1, null, null, 
                bikes1, 
                new DefaultValuationPolicy(), 
                DPP1,new BigDecimal("0.1"), 
                null, bookingController);
        
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
        DPP2.setDailyRentalPrice(new BikeType(null,"road"), new BigDecimal("15"));
        
        location2 = new Location("EH7 5QP", "Albion Road 43");
        
        p2 = new Provider("Provider", 2, location2, null, null, 
                bikes2, 
                new DefaultValuationPolicy(), 
                DPP2, new BigDecimal("0.1"), 
                null,bookingController);
        
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
        DPP2.setDailyRentalPrice(new BikeType(null,"ebike"), new BigDecimal("15"));
        
        location3 = new Location("AB1 5KL", "George Street 12");
        
        p3 = new Provider("Provider", 3, location3, null, null, 
                bikes3, 
                new DefaultValuationPolicy(), 
                DPP2,new BigDecimal("0.1"), 
                null,bookingController);
        
        //PROVIDER 3 END
   
        
        providerController.registerNewProvider(p1);
        providerController.registerNewProvider(p2);
        providerController.registerNewProvider(p3);
        
        
        
        
        
        
        
    }
    
    // TODO: Write system tests covering the three main use cases

    @Test
    void testGetQuotes() {
        types1 = new HashMap<>();
        types1.put(new BikeType(null,"road"), 1);
        DateRange dateRange = new DateRange(LocalDate.of(2019, 1, 7),LocalDate.of(2019, 1, 10));
        Location location = new Location("EH7 5KL","Kings Street 5");
        Bike testBike = new Bike(
                8,
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"road"), 
                new HashSet<DateRange>(), 
                "shop");
        
       
        Collection<Bike> testBikeSet = new ArrayList<>();
        testBikeSet.add(testBike);
        assertEquals(bookingController.getQuotes(types1, dateRange, location).size(),1);
       
        Quote testQuote =
                new Quote(p2, testBikeSet,
                        new BigDecimal(45),null,dateRange);
        
        //assertTrue(bookingController.getQuotes(types1, dateRange, location).contains(testQuote));
        
        bookingController.bookQuote(testQuote, customer, false);
        
        assertEquals(bookingController.getQuotes(types1, dateRange, location).size(),0);
        
    }
}
