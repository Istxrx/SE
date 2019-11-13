package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
 
public class DoubleDecliningBalanceDepreciation implements ValuationPolicy  {
    
    private BigDecimal depreciationRate;
    private BigDecimal replacementValue;
    private long age;
    
    public DoubleDecliningBalanceDepreciation
    (BigDecimal replacementValue, BigDecimal depreciationRate, long age) {
        this.depreciationRate = depreciationRate;
        this.replacementValue = replacementValue;
        this.age = age;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }


    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }

    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        
        return (replacementValue (new BigDecimal("1").subtract
                (new BigDecimal(age.toString())depreciationRate))
        
    };
}
