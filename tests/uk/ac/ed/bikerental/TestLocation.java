package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    
    private Location location1,location2,location3,location4;
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
        this.location1 = new Location("EH1 1LG", "Niddry Street 39");
        this.location2 = new Location("EH7 5QP", "Albion Road 43");
        this.location3 = new Location("G1 1AB", "George Street 12");
        this.location4 = new Location("EK2 1KL", "Victoria Street 23");
    }
    
    @Test
    void testIsNearToEqual1(){
        assertTrue(location1.isNearTo(location2));
    }
    
    @Test
    void testIsNearToEqual2(){
        assertTrue(location1.isNearTo(location1));
    }
    
    @Test
    void testIsNearToNotEqual1(){
        assertFalse(location1.isNearTo(location3));
    }
    
    @Test
    void testIsNearToNotEqual2(){
        assertFalse(location2.isNearTo(location4));
    }
    
    // TODO: put some tests here
    
}
