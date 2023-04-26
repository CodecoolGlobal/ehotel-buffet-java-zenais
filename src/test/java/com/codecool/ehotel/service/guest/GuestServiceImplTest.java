package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.service.date.DateService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceImplTest {

    DateService dateService = new DateService();
    GuestService guestService = new GuestServiceImpl(dateService, GuestType.values());
    LocalDate seasonStart = LocalDate.parse("2022-01-01");
    LocalDate seasonEnd = LocalDate.parse("2022-02-01");
    int numberOfGuestsExpected = 100;
    int maxStay = 7;

    @Test
    void generateAllGuests() {

        List<Guest> allGuests = guestService.generateAllGuests(seasonStart, seasonEnd, numberOfGuestsExpected, maxStay);
        int result = allGuests.size();
        assertEquals(numberOfGuestsExpected, result);
    }

    @Test
    void getGuestsForDay() {
        List<Guest> allGuests = guestService.generateAllGuests(seasonStart, seasonEnd, numberOfGuestsExpected, maxStay);
        LocalDate date = seasonStart;
        Set<Guest> allGuestExpected = new HashSet<>();
        while (!date.isAfter(seasonEnd)){
            Set<Guest> guestForDay = guestService.getGuestsForDay(allGuests,date);
            allGuestExpected.addAll(guestForDay);
            date = date.plusDays(1);
        }
        assertEquals(numberOfGuestsExpected, allGuestExpected.size());
    }
}