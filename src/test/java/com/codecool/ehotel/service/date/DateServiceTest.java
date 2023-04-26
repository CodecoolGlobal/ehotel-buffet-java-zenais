package com.codecool.ehotel.service.date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class DateServiceTest {

    DateService dateService = new DateService();

    @ParameterizedTest
    @MethodSource("seasons")
    void getRandomDateInSeasonTest() {
        //TODO:
        LocalDate seasonStart = LocalDate.parse("2023-01-01");
        LocalDate seasonEnd = LocalDate.parse("2023-02-01");
        LocalDate result = dateService.getRandomDateInSeason(seasonStart, seasonEnd);
    }

    public static Stream<Arguments> dates() {
        return Stream.of(
                of( LocalDate.parse("2022-01-01"), 30, LocalDate.parse("2022-01-10")),
                of( LocalDate.parse("2022-01-01"), 1, LocalDate.parse("2022-01-10")),
                of( LocalDate.parse("2022-01-01"), 0, LocalDate.parse("2022-01-10"))
        );
    }
    @ParameterizedTest
    @MethodSource("dates")
    void getCheckOutDateTest( LocalDate checkIn, int maxStay, LocalDate seasonEnd ) {
        LocalDate resultDate = dateService.getCheckOutDate(checkIn,maxStay,seasonEnd);
        assertTrue(dateService.isBetweenInclusive(checkIn,seasonEnd,resultDate));
    }

    public static Stream<Arguments> parameters() {
        return Stream.of(
                of(true, LocalDate.parse("2022-01-01"), LocalDate.parse("2022-02-01"), LocalDate.parse("2022-01-01")),
                of(true, LocalDate.parse("2022-01-01"), LocalDate.parse("2022-02-01"), LocalDate.parse("2022-02-01")),
                of(true, LocalDate.parse("2022-01-01"), LocalDate.parse("2022-02-01"), LocalDate.parse("2022-01-05")),
                of(false, LocalDate.parse("2022-01-02"), LocalDate.parse("2022-02-01"), LocalDate.parse("2022-01-01")),
                of(false, LocalDate.parse("2022-01-02"), LocalDate.parse("2022-02-01"), LocalDate.parse("2022-02-02")),
                of(false, LocalDate.parse("2022-01-02"), LocalDate.parse("2022-02-01"), LocalDate.parse("2021-02-02"))
        );
    }
    @ParameterizedTest
    @MethodSource("parameters")
    void isBetweenInclusive(boolean expected, LocalDate firstDate, LocalDate secondDate, LocalDate date) {
        Boolean result = dateService.isBetweenInclusive(firstDate, secondDate, date);
        assertEquals(expected, result);
    }
}