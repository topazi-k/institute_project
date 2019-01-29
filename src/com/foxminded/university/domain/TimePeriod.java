package com.foxminded.university.domain;

import java.time.LocalDate;

public class TimePeriod {
    private LocalDate startOfPeriod;
    private LocalDate endOfPeriod;
    
    public TimePeriod(LocalDate oneDay) {
        this.startOfPeriod = oneDay;
        this.endOfPeriod = oneDay;
    }
    
    public TimePeriod(LocalDate startOfPeriod, LocalDate endOfPeriod) {
        this.startOfPeriod = startOfPeriod;
        this.endOfPeriod = endOfPeriod;
    }
    
    public LocalDate getStartOfPeriod() {
        return startOfPeriod;
    }
    
    public LocalDate getEndOfPeriod() {
        return endOfPeriod;
    }
}
