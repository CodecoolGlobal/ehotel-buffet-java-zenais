package com.codecool.ehotel.model;

import java.time.LocalDate;

public interface Constants {
    int ALL_BREAKFAST_GUESTS = 150;
    int ALL_DINNER_GUESTS = 100;
    LocalDate SEASON_START = LocalDate.parse("2022-01-01");
    LocalDate SEASON_END = LocalDate.parse("2022-02-01");
    int MAX_STAY_DAYS = 7;
    int[] QUOTIENTS = {5, 3, 2};
    int SUM_OF_QUOTIENTS = 10;

}
