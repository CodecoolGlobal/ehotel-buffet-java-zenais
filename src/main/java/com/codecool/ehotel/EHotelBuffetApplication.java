package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;

import java.util.ArrayList;
import java.util.List;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.breakfastGroup.BreakfastGroupProvider;
import com.codecool.ehotel.service.date.DateService;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.time.LocalDate;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        int numberOfGuests = 10;
        int maxStay = 7;
        LocalDate seasonStart = LocalDate.parse("2023-01-01");
        LocalDate seasonEnd = LocalDate.parse("2023-02-01");

        // Initialize services
        Buffet buffet = new Buffet(new ArrayList<>());
        BuffetServiceImpl buffetServiceImpl = new BuffetServiceImpl(buffet);
        DateService dateService = new DateService();
        GuestService guestService = new GuestServiceImpl(dateService);
        BuffetService buffetService = new BuffetServiceImpl(buffet);
        BreakfastGroupProvider breakfastGroupProvider = new BreakfastGroupProvider();

        // Generate guests for the season
        List<Guest> allGuests = guestService.generateAllGuests(seasonStart, seasonEnd, numberOfGuests, maxStay);
        BreakfastManager breakfastManager = new BreakfastManager(guestService, buffetService,  breakfastGroupProvider, allGuests,seasonStart,seasonEnd);
        // Run breakfast buffet
        breakfastManager.simulateSeason();

    }
}
