package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface GuestService {

    List<Guest> generateAllGuests(LocalDate seasonStart, LocalDate seasonEnd, int numberOfGuests);

    Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date);

    Guest generateRandomGuest(int maxStay, LocalDate seasonStart, LocalDate seasonEnd);
}
