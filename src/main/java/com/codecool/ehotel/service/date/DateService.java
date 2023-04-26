package com.codecool.ehotel.service.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.random;

public class DateService {
    public static LocalDate getRandomDateInSeason(LocalDate seasonStart, LocalDate seasonEnd) {
        //TODO: create test
        int dateDifference = (int) ChronoUnit.DAYS.between(seasonStart, seasonEnd);
        LocalDate checkIn;
        do {
            checkIn = seasonStart.plusDays((long) (random() * dateDifference));
        } while (checkIn.isAfter(seasonEnd));
        return checkIn;
    }

    public static LocalDate getCheckOutDate(LocalDate checkIn, int maxStay, LocalDate seasonEnd) {
        //TODO: create test
        LocalDate checkOut;
        do {
            checkOut = checkIn.plusDays((long) (random() * maxStay));
        } while (checkOut.isAfter(seasonEnd));
        return checkOut;
    }
}
