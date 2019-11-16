package uk.ac.ed.bikerental;



public class Location {
    private String postcode;
    private String address;
    
/**
 * Creates new object Location with postcode and address attributes.
 *   
 * @param stores postcode of the location, its length must be >=6
 * @param address stores address of the location
 */
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    public boolean isNearTo(Location other) {
       
       if (postcode.charAt(0)!=other.getPostcode().charAt(0)) {
           return false;
       }
       
       if (postcode.charAt(1)!=other.getPostcode().charAt(1)) {
           return false;
       }
        
        return true;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
