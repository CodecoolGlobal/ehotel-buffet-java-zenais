package com.codecool.ehotel.service.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.random;

public class DateService {
    public LocalDate getRandomDateInSeason(LocalDate seasonStart, LocalDate seasonEnd) {
        int dateDifference = (int) ChronoUnit.DAYS.between(seasonStart, seasonEnd);
        LocalDate checkIn;
        do {
            checkIn = seasonStart.plusDays((long) (random() * dateDifference));
        } while (checkIn.isAfter(seasonEnd));
        return checkIn;
    }

    public LocalDate getCheckOutDate(LocalDate checkIn, int maxStay, LocalDate seasonEnd) {
        LocalDate checkOut;
        do {
            checkOut = checkIn.plusDays((long) (random() * maxStay));
        } while (checkOut.isAfter(seasonEnd));
        return checkOut;
    }

    public boolean isBetweenInclusive(LocalDate firstDate, LocalDate secondDate, LocalDate date){
        return !date.isBefore(firstDate) && !date.isAfter(secondDate);
    }
}
