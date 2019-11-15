package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.*;


public class PricingPolicyTests {
    // You can add attributes here
    DefaultPricingPolicy DPP;
    Bike b1,b2;
    DateRange d1,d2;
    HashSet<Bike> bikes1,bikes2;

    @BeforeEach
    void setUp() throws Exception {
        // Put setup here
        b1 = new Bike(
                LocalDate.of(2016, 1, 7), 
                new BikeType(new BigDecimal("900"),"ebike"), 
                null, 
                "shop");
        b2 = new Bike(
                LocalDate.of(2018, 1, 7), 
                new BikeType(new BigDecimal("100"),"mountain"), 
                null, 
                "shop");
        
        d1 = new DateRange(LocalDate.of(2019, 1, 7),LocalDate.of(2019, 1, 10));
        d2 = new DateRange(LocalDate.of(2019, 11, 15),LocalDate.of(2019, 11, 20));
        
        DPP = new DefaultPricingPolicy();
        DPP.setDailyRentalPrice(new BikeType(null,"mountain"), new BigDecimal("10"));
        DPP.setDailyRentalPrice(new BikeType(null,"ebike"), new BigDecimal("5"));
        
        bikes1 = new HashSet<>();
        bikes1.add(b1);
        bikes1.add(b2);
        
        bikes2 = new HashSet<>();
        bikes2.add(b1);
    }
    
    
    @Test
    void testDefault() {
        BigDecimal A = new BigDecimal("45");
        BigDecimal B = DPP.calculatePrice(bikes1, d1);
        assertEquals(A.stripTrailingZeros(), B.stripTrailingZeros());
    }
    
    @Test
    void testDefault2() {
        BigDecimal A = new BigDecimal("15");
        BigDecimal B = DPP.calculatePrice(bikes2, d1);
        assertEquals(A.stripTrailingZeros(), B.stripTrailingZeros());
    }
    
    @Test
    void testDefault3() {
        BigDecimal A = new BigDecimal("75");
        BigDecimal B = DPP.calculatePrice(bikes1, d2);
        assertEquals(A.stripTrailingZeros(), B.stripTrailingZeros());
    }


}