package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.service.date.DateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

//import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
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
    @Test
    void getGuestsOneForDayTest() {
        List<Guest> allGuests = guestService.generateAllGuests(seasonStart, seasonStart, 10, 1);
        LocalDate date = seasonStart;
        Set<Guest> allGuestResult = guestService.getGuestsForDay(allGuests,date);
        assertEquals(allGuests.size(), allGuestResult.size());
    }

    @Test
    void getNumberOfGuestsPerType() {
        Set<Guest> testSet = new HashSet<>();
        LocalDate date = LocalDate.now();
        testSet.add(new Guest<>("John", GuestType.BUSINESS, date, date));
        testSet.add(new Guest<>("Sepp", GuestType.BUSINESS, date, date));
        testSet.add(new Guest<>("Hans", GuestType.BUSINESS, date, date));
        testSet.add(new Guest<>("Susi", GuestType.KID, date, date));
        testSet.add(new Guest<>("Mitzi", GuestType.KID, date, date));
        testSet.add(new Guest<>("blabla", GuestType.TOURIST, date, date));
        testSet.add(new Guest<>("test", GuestType.TOURIST, date, date));
        testSet.add(new Guest<>("Sam", GuestType.TOURIST, date, date));
        testSet.add(new Guest<>("Joey", GuestType.TOURIST, date, date));
        Map<GuestType, Integer> result = guestService.getNumberOfGuestsPerType(testSet);
        Map<GuestType, Integer> expected = new HashMap<>();
        expected.put(GuestType.BUSINESS, 3);
        expected.put(GuestType.KID, 2);
        expected.put(GuestType.TOURIST, 4);
        assertTrue(result.equals(expected));
    }
}