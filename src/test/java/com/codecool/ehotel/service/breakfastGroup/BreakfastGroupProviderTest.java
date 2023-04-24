package com.codecool.ehotel.service.breakfastGroup;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.*;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class BreakfastGroupProviderTest {

    BreakfastGroupProvider breakfastGroupProvider = new BreakfastGroupProvider();
    int numberOfGuests = 100;
    int numberOfGroups = 15;
    Set<Guest> expectedAllGuests = generateGuests(numberOfGuests);
    List<Set<Guest>> guestGroups = breakfastGroupProvider.getBreakfastGroups(expectedAllGuests, numberOfGroups);
    @org.junit.jupiter.api.Test
    void testIfAllGuestsIncluded() {
        int sum = guestGroups.stream().mapToInt(guestSet->guestSet.size()).reduce(0,(acc,el)->acc+el);
        assertEquals(numberOfGuests,sum);
    }
    @org.junit.jupiter.api.Test
    void testIfNoGuestRepeated() {
        Set<Guest> resultAllGuests = new HashSet<>();
        guestGroups.forEach(set->resultAllGuests.addAll(set));
        assertTrue(resultAllGuests.equals(expectedAllGuests));
    }
    private Set<Guest> generateGuests(int num) {
        Set<Guest> allGuests = new HashSet<>();
        GuestType[] types = GuestType.values();
        for (int i = 0; i < num; i++) {
            String name = UUID.randomUUID().toString().substring(0, 8).trim();
            GuestType guestType = types[new Random().nextInt(types.length)];
            LocalDate date = LocalDate.now().plusDays((long) (Math.random() * 6));
            allGuests.add(new Guest(name, guestType, LocalDate.now(), date));
//            System.out.format("%s, %s, %s\n",name,guestType, date.toString());
        }
        return allGuests;
    }
    private static void printGuestGroups( List<Set<Guest>> guestGroups) {
        int i = 0;
        for (Set<Guest> guestSet : guestGroups) {
            System.out.println("Group: " + i++);
            guestSet.iterator().forEachRemaining(System.out::println);
        }
    }
}