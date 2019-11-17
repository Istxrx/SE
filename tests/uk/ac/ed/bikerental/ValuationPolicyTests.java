package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    Bike b1,b2;
    ValuationPolicy DVP,LD,DDBD;
    

    @BeforeEach
    void setUp() throws Exception {
        // Put setup here
        b1 = new Bike(
                LocalDate.of(2016, 1, 7), 
                null,
                new BikeType(new BigDecimal("900"),"ebike"), 
                null, 
                "shop");
        b2 = new Bike(
                LocalDate.of(2018, 1, 7), 
                null,
                new BikeType(new BigDecimal("100"),"mountain"), 
                null, 
                "shop");
        DVP = new DefaultValuationPolicy(new BigDecimal("0.1"));
        LD = new LinearDepreciation(null,new BigDecimal("0.1"));
        DDBD = new DoubleDecliningBalanceDepreciation(null,new BigDecimal("0.1"));
    }
    
    // TODO: Write tests for valuation policies
    @Test
    void testDefault() {
        BigDecimal A = new BigDecimal("100");
        BigDecimal B = DVP.calculateValue(b2, LocalDate.now());
        assertEquals(A.stripTrailingZeros(), B.stripTrailingZeros());
    }
    
    @Test
    void testLinear() {
        BigDecimal A = new BigDecimal("630");
        BigDecimal B = LD.calculateValue(b1, LocalDate.of(2019, 1, 7));
        assertEquals(A.stripTrailingZeros(), B.stripTrailingZeros());
    }
    
    @Test
    void testDobleDeclining() {
        BigDecimal A = new BigDecimal("460.8");
        BigDecimal B = DDBD.calculateValue(b1, LocalDate.of(2019, 1, 7));
        assertEquals(A.stripTrailingZeros(), B.stripTrailingZeros());
    }
    
    
}
