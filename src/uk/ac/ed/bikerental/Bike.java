package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Bike implements Deliverable{
 
    private Integer ID;
    private LocalDate age;
    private Provider owner;
    private BikeType type;
    private Collection<DateRange> availability;
    private String status;
    
    public Bike(Integer ID, LocalDate age, Provider owner, BikeType type, Collection<DateRange> availability, 
            String status) {
        this.ID = ID;
        this.age = age;
        this.owner = owner;
        this.type = type;
        this.availability = availability;
        this.status = status;
    }
    
    @Override
    public boolean equals(Object other) {
        return this.ID.equals(((Bike) other).getID());
    }

    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(this.ID);
    }
    
    public void setOwner(Provider provider) {
        this.owner = provider;
    }
     
    public BikeType getType() {
        return this.type;
    }
    
    public Integer getID() {
        return this.ID;
    }
    
    public LocalDate getAge() {
        return this.age;
    }
    
    public Provider getOwner() {
        return this.owner;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public boolean isAvailableOn(DateRange dateRange) {
        for (DateRange dateRangeUnavailable : availability) {
            if (dateRange.overlaps(dateRangeUnavailable)) {
                return false;
            }
        }
        return true;
    }
    
    public void updateStatus() {
        switch (this.status) {
        case "shop":
            this.status = "being delivered to customer";
            break;
        case "being delivered to customer":
            this.status = "with customer";
            break;    
        case "with customer":
            this.status = "shop";
            break;
        case "with partner":
            this.status = "being delivered to owner";
            break;
        case "being delivered to owner":
            this.status = "shop";
            break;
        }
    }
    
    public void makeUnavailable(DateRange dateRange) {
        this.availability.add(dateRange);
    }
    
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public void onPickup() {
        this.updateStatus();
    }

    @Override
    public void onDropoff() {
        this.updateStatus();
        
    }
}
