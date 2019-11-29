package uk.ac.ed.bikerental;

public class Customer {

    private String name;
    private Integer ID;
    private Location address;
    private String phoneNumber;
    
    public Customer(Integer ID) {        
        this.name = null;
        this.ID = ID;
        this.address = null;
        this.phoneNumber = null;
    }
    
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
    
    public void provideDetails(String name,Location address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
