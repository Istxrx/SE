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
    
    public Customer() {}
    
    public Location getAddress() {
        return this.address;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Integer getID() {
        return this.ID;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void provideDetails(String name, Integer iD, Location address, String phoneNumber) {
        this.name = name;
        this.ID = iD;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
