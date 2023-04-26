package com.codecool.ehotel.service.breakfastGroup;

import com.codecool.ehotel.model.Guest;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreakfastGroupProvider {

    public List<Set<Guest>> getBreakfastGroups(Set<Guest> allGuests, int numberOfGroups){
        //TODO: Zinaida FINISHED
        List<Set<Guest>> guestGroups = initalizeEmptyList(numberOfGroups);
        allGuests.iterator().forEachRemaining(guest -> {
            assignGuestToRandomGroup(numberOfGroups, guestGroups, guest);
        });
        return guestGroups;
    }

    private void assignGuestToRandomGroup(int numberOfGroups, List<Set<Guest>> guestGroups, Guest guest) {
        int randomIndex = getRandomIndex(numberOfGroups);
        Set<Guest> guestSet = getGuestSet(guestGroups, randomIndex);
        guestSet.add(guest);
    }

    private List<Set<Guest>> initalizeEmptyList(int numberOfGroups) {
        List<Set<Guest>> guestGroups = new ArrayList<>();
        for (long count = numberOfGroups; count > 0; count--) {
            HashSet<Guest> objects = new HashSet<>();
            guestGroups.add(objects);
        }
        return guestGroups;
    }

    private Set<Guest> getGuestSet(List<Set<Guest>> guestGroups, int randomIndex) {
        return guestGroups.get(randomIndex);
    }

    private static int getRandomIndex(int numberOfGroups) {
        return new Random().nextInt(numberOfGroups);
    }
}
