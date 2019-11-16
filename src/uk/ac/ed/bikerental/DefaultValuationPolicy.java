package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DefaultValuationPolicy implements ValuationPolicy {

    private BigDecimal depositRate;
    
    public DefaultValuationPolicy(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }
    
    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        return bike.getType().getReplacementValue();
    }

    @Override
    public BigDecimal calculateDeposit(Bike bike, LocalDate date) {
        return depositRate.multiply(calculateValue(bike, date));
    }

}
