package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.service.date.DateService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GuestService{

    List<Guest> generateAllGuests(LocalDate seasonStart, LocalDate seasonEnd, int numberOfGuests, int maxStay);

    Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date);

    Guest generateRandomGuest(int maxStay, LocalDate seasonStart, LocalDate seasonEnd);

    Map<GuestType, Integer> getNumberOfGuestsPerType(Set<Guest> guestsForDay);

    void removeGuestFromTypeMap(Guest guest, Map<GuestType, Integer> numberGuestsPerType);
}
