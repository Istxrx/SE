package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
 
public class DoubleDecliningBalanceDepreciation implements ValuationPolicy  {
    
    private BigDecimal depositRate;
    private BigDecimal depreciationRate;
    
    public DoubleDecliningBalanceDepreciation(BigDecimal depositRate, BigDecimal depreciationRate) {
        this.depositRate = depositRate;
        this.depreciationRate = depreciationRate;
    }
    
    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BigDecimal age = new BigDecimal (new DateRange(bike.getAge(),date).toYears());
        BigDecimal replacementValue = bike.getType().getReplacementValue();
        return 
        replacementValue.multiply(BigDecimal.ONE.subtract
        (age.subtract(BigDecimal.ONE).multiply(depreciationRate)).pow(age.intValue())); 
    }
    
    @Override
    public BigDecimal calculateDeposit(Bike bike, LocalDate date) {
        return depositRate.multiply(calculateValue(bike, date));
    }
}
