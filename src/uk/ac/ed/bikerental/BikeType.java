package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    private BigDecimal replacementValue;
    
    public BikeType(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }
    
    public void setReplacementValue (BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }
    
    public BigDecimal getReplacementValue() {
        assert this.replacementValue != null;
        return this.replacementValue;
    }
}