package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {
    
    private BigDecimal depreciationRate;
    
    public LinearDepreciation(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BigDecimal age = new BigDecimal (new DateRange(bike.getAge(),date).toYears());
        BigDecimal replacementValue = bike.getType().getReplacementValue();
       
        return 
        replacementValue.subtract((age.multiply(depreciationRate)).multiply(replacementValue));
        
    };
}

