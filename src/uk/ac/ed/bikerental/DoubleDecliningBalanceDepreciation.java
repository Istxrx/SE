package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
 
public class DoubleDecliningBalanceDepreciation implements ValuationPolicy  {
    
    private BigDecimal depreciationRate;
    private BigDecimal replacementValue;
    private long age;
    
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        
        
    };
}
