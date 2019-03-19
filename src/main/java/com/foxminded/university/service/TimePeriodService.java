package com.foxminded.university.service;

import java.time.LocalDate;

public class TimePeriodService {
    private LocalDate startOfPeriod;
    private LocalDate endOfPeriod;
    
    public TimePeriodService(LocalDate oneDay) {
        this.startOfPeriod = oneDay;
        this.endOfPeriod = oneDay;
    }
    
    public TimePeriodService(LocalDate startOfPeriod, LocalDate endOfPeriod) {
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
