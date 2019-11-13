package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {
    private BigDecimal depreciationRate;
    private BigDecimal replacementValue;
    
    public LinearDepreciation
    (BigDecimal replacementValue, BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
        this.replacementValue = replacementValue;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }


    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }

    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BigDecimal age = new BigDecimal (new DateRange(bike.getAge(),date).toYears());
        
        return 
        replacementValue.subtract((age.multiply(depreciationRate)).multiply(replacementValue));
        
    };
}

