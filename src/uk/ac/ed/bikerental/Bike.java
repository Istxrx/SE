package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.HashSet;

public class Bike {

    private LocalDate age;
    private BikeType type;
    private HashSet<DateRange> availability;
    private String status;
    
    public Bike(LocalDate age, BikeType type, HashSet<DateRange> availability, String status) {
        super();
        this.age = age;
        this.type = type;
        this.availability = availability;
        this.status = status;
    }
    
    public BikeType getType() {
        assert this.type != null;
        return this.type;
    }
}
