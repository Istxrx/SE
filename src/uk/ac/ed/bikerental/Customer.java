package uk.ac.ed.bikerental;

public class Customer {

    private String name;
    private Integer ID;
    private Location address;
    private String phoneNumber;
    
    public Customer(String name, Integer iD, Location address, String phoneNumber) {        
        this.name = name;
        this.ID = iD;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public Location getAddress() {
        return this.address;
    }
    
}
