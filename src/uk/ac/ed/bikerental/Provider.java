package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.Objects;

public class Provider {
    
    private String name;
    private Integer ID;
    private Location address;
    private String phoneNumber;
    private Collection<Provider> partners;
    private Collection<Bike> bikes;
    
    public Provider(String name, Integer iD, Location address, String phoneNumber, 
            Collection<Provider> partners,Collection<Bike> bikes) {
        
        this.name = name;
        this.ID = iD;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.partners = partners;
        this.bikes = bikes;
    }
    
    @Override
    public boolean equals(Object other) {
        return this.ID.equals(((Provider) other).getID());
    }
   
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(this.ID);
    }
    
     
    private Object getID() {
        return this.ID;
    }


}
