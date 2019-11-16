package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * This class is used to encapsulate start and end date as its LocalDate attributes.
 * 
 */

public class DateRange {
    private LocalDate start, end;

/**
 * Constructor creates new DateRange object with start and end attributes
 *     
 * @param start start date 
 * @param end end date
 */
    
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

/**
 * Getter for start
 *    
 * @return this.start
 */
    
    public LocalDate getStart() {
        return this.start;
    }
    
/**
 * Getter for end    
 * @return this.end
 */
    
    public LocalDate getEnd() {
        return this.end;
    }
    
/**
 * Calculates number of years between the start and end date    
 * @return number of years
 */

    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

/**
 * Calculates number of days between the start and end date
 *      
 * @return number of days
 */

    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

/**
 * Finds out if the two DateRanges are overlapping
 *     
 * @param other
 * @return boolean, true if they overlap else false
 */
    
    public Boolean overlaps(DateRange other) {
        if((other.getEnd().isBefore(this.start)) || (other.getStart().isAfter(this.end))){
             return false;   
            }
        return true;
    }

    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
    // You can add your own methods here
}
