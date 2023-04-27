package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.date.DateService;

import java.time.LocalDate;
import java.util.*;

public class GuestServiceImpl<T extends Enum<T>> implements GuestService {
    DateService dateService;
    T[] types;
    public GuestServiceImpl(DateService dateService,T[] types){
        this.dateService = new DateService();
        this.types = types;
    }


    @Override
    public List<Guest> generateAllGuests(LocalDate seasonStart, LocalDate seasonEnd, int numberOfGuests, int maxStay) {
        //TODO: Create Tests
        List<Guest> allGuests = new ArrayList<>();
        int counter = 0;
        while (counter < numberOfGuests) {
            Guest guest = generateRandomGuest(maxStay, seasonStart, seasonEnd);
            allGuests.add(guest);
            counter++;
        }
        return allGuests;
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        Set<Guest> guestsForDay = new HashSet<>();
        //TODO: Create Tests

        for (Guest guest : guests) {
            if (dateService.isBetweenInclusive(guest.checkIn(),guest.checkOut(),date)){
                guestsForDay.add(guest);
            }
        }
        return guestsForDay;
    }

    @Override
    public Guest generateRandomGuest(int maxStay, LocalDate seasonStart, LocalDate seasonEnd) {
        //TODO: create Test
        String name = UUID.randomUUID().toString().substring(0, 8).trim();
        T guestType = types[new Random().nextInt(types.length)];
        LocalDate checkIn = dateService.getRandomDateInSeason(seasonStart, seasonEnd);
        LocalDate checkOut = dateService.getCheckOutDate(checkIn, maxStay, seasonEnd);
        return new Guest<T>(name, guestType, checkIn, checkOut);
    }

    @Override
    public Map<GuestType, Integer> getNumberOfGuestsPerType(Set<Guest> guestsForDay) {
        Map<GuestType, Integer> numberPerType = new HashMap<>();
        for (Guest guest : guestsForDay) {
            if (!numberPerType.containsKey(guest.guestType())) {
                numberPerType.put((GuestType) guest.guestType(), 1);
            } else {
                numberPerType.put((GuestType) guest.guestType(), numberPerType.get(guest.guestType()) + 1);
            }
        }
        return numberPerType;
    }

    @Override
    public void removeGuestFromTypeMap(Guest guest, Map<GuestType, Integer> numberGuestsPerType) {
        if (numberGuestsPerType.get(guest.guestType()) == 1) {
            numberGuestsPerType.remove(guest.guestType());
        } else {
            numberGuestsPerType.put((GuestType) guest.guestType(), numberGuestsPerType.get(guest.guestType()) - 1);
        }
    }
}
