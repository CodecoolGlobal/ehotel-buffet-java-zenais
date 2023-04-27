package com.codecool.ehotel.service.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.random;

public class DateService {
    public LocalDate getRandomDateInSeason(LocalDate seasonStart, LocalDate seasonEnd) {
        int dateDifference = getTimeBetweenInDays(seasonStart, seasonEnd);
        LocalDate checkIn;
        do {
            checkIn = getRadomDateInPeriod(seasonStart, dateDifference);
        } while (isAfterSeasonEnd(seasonEnd, checkIn));
        return checkIn;
    }

    private static int getTimeBetweenInDays(LocalDate seasonStart, LocalDate seasonEnd) {
        int dateDifference = (int) ChronoUnit.DAYS.between(seasonStart, seasonEnd);
        return dateDifference;
    }

    private static LocalDate getRadomDateInPeriod(LocalDate seasonStart, int dateDifference) {
        return seasonStart.plusDays((long) (random() * dateDifference));
    }

    public LocalDate getCheckOutDate(LocalDate checkIn, int maxStay, LocalDate seasonEnd) {
        LocalDate checkOut;
        do {
            checkOut = getRadomDateInPeriod(checkIn, maxStay);
        } while (isAfterSeasonEnd(seasonEnd, checkOut));
        return checkOut;
    }

    private static boolean isAfterSeasonEnd(LocalDate seasonEnd, LocalDate checkOut) {
        return checkOut.isAfter(seasonEnd);
    }

    public boolean isBetweenInclusive(LocalDate firstDate, LocalDate secondDate, LocalDate date){
        return !date.isBefore(firstDate) && !isAfterSeasonEnd(secondDate, date);
    }
}
