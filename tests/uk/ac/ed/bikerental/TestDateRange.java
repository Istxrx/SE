package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    private DateRange dateRange1, dateRange2, dateRange3, dateRange4;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
        this.dateRange4 = new DateRange(LocalDate.of(2016, 1, 7),
                LocalDate.of(2017, 1, 10));
        
    }

    // Sample JUnit tests checking toYears works
    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }

    @Test
    void testOverlapsTrue() {
        assertTrue(this.dateRange1.overlaps(this.dateRange2));
    }
    
    @Test
    void testOverlapsTrue2() {
        assertTrue(this.dateRange1.overlaps(this.dateRange2));
    }
    
    @Test
    void testOverlapsTrue3() {
        assertTrue(this.dateRange3.overlaps(this.dateRange4));
    }
    
    @Test
    void testOverlapsTrue4() {
        assertTrue(this.dateRange4.overlaps(this.dateRange3));
    }

    @Test
    void testOverlapsFalse() {
        assertFalse(this.dateRange1.overlaps(this.dateRange3));
    }

    @Test
    void testOverlapsFalse2() {
        assertFalse(this.dateRange3.overlaps(this.dateRange1));
    }
    
    @Test
    void testOverlapsFalse3() {
        assertFalse(this.dateRange4.overlaps(this.dateRange1));
    }
    
}
