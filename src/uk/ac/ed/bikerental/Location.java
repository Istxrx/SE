package uk.ac.ed.bikerental;

/**
 * This class is used to encapsulate post code and address as its String attributes.
 */

public class Location {
    private String postcode;
    private String address;
    
/**
 * Constructor creates new object Location with post code and address as String attributes.
 *   
 * @param postcode stores post code of the location as String
 *        <b>Note:</b> postcode.length() must be >=6
 * @param address stores address of the location as String
 */
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
/**
 * Compares the two first two characters of the this.postcode to other.getPostcode()
 * and returns true if they are equal else returns false
 *     
 * @param other second Location to perform comparison with
 * @return returns boolean depending on equality of the first two characters of the post codes
 */
    
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
