package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Bike {

    private LocalDate age;
    private BikeType type;
    private HashSet<DateRange> availability;
    private String status;
    
    public Bike(LocalDate age, BikeType type, HashSet<DateRange> availability, String status) {
        this.age = age;
        this.type = type;
        this.availability = availability;
        this.status = status;
    }
     
    public BikeType getType() {
        return this.type;
    }
    
    public LocalDate getAge() {
        return this.age;
    }
    
    public boolean isAvailableOn(DateRange dateRange) {
        for (DateRange dateRangeUnavailable : availability) {
            if (dateRange.overlaps(dateRangeUnavailable)) {
                return false;
            }
        }
        return true;
    }
}
