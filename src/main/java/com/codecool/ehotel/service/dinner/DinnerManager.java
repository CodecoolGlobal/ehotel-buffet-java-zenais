package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.List;

public class DinnerManager {
    private List<Guest> allDinnerGuests;
    private java.awt.Menu menu;
    private final LocalDate seasonStart;
    private final LocalDate seasonEnd;

    public DinnerManager(List<Guest> allDinnerGuests, LocalDate seasonStart, LocalDate seasonEnd) {
        this.allDinnerGuests = allDinnerGuests;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
    }

    public void simulateSeason() {

    }
}
