package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    private BigDecimal replacementValue;
    private String name;
    
    public BikeType(BigDecimal replacementValue, String name) {
        this.replacementValue = replacementValue;
        this.name = name;
    }
    
    public void setReplacementValue (BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }
    
    public BigDecimal getReplacementValue() {
        assert this.replacementValue != null;
        return this.replacementValue;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(name);
    }
    
    @Override
    public boolean equals(Object other) {
        return this.name.equals(((BikeType) other).getName());
    }
    
}