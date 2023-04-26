package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.service.date.DateService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static java.lang.Math.random;

public class GuestServiceImpl implements GuestService {


    @Override
    public List<Guest> generateAllGuests(LocalDate seasonStart, LocalDate seasonEnd, int numberOfGuests) {

        return null;
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        //TODO: Chris
        return null;
    }

    @Override
    public Guest generateRandomGuest(int maxStay, LocalDate seasonStart, LocalDate seasonEnd) {
        //TODO: create Test
        GuestType[] types = GuestType.values();
        String name = UUID.randomUUID().toString().substring(0, 8).trim();
        GuestType guestType = types[new Random().nextInt(types.length)];
        LocalDate checkIn = DateService.getRandomDateInSeason(seasonStart, seasonEnd);
        LocalDate checkOut = DateService.getCheckOutDate(checkIn, maxStay, seasonEnd);
        return new Guest(name, guestType, checkIn, checkOut);
    }
}
