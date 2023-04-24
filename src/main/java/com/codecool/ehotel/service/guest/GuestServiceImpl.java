package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class GuestServiceImpl implements GuestService{
    LocalDate seasonStart;
    LocalDate seasonEnd;
    public GuestServiceImpl(LocalDate seasonStart, LocalDate seasonEnd, int numberOfGuests) {
        this.seasonEnd = seasonEnd;
        this.seasonStart = seasonStart;
    }

    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        //TODO: Chis
        return null;
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        //TODO: Chris
        return null;
    }

    public Guest generateRandomGuest(){
        //TODO: Chris
        return null;
    }
}
