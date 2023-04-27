package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.DinnerGuestType;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.breakfastGroup.BreakfastGroupProvider;
import com.codecool.ehotel.service.date.DateService;
import com.codecool.ehotel.service.dinner.DinnerManager;
import com.codecool.ehotel.service.dinner.DinnerService;
import com.codecool.ehotel.service.dinner.kitchen.KitchenService;
import com.codecool.ehotel.service.dinner.kitchen.Storage;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.service.successMetrics.SuccessMetrics;

import java.time.LocalDate;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        int numberOfGuests = 10;
        int maxStay = 7;
        LocalDate seasonStart = LocalDate.parse("2023-01-01");
        LocalDate seasonEnd = LocalDate.parse("2023-02-01");

        // Initialize services
        Buffet buffet = new Buffet(new ArrayList<>());
        DateService dateService = new DateService();
        GuestServiceImpl<GuestType> guestService = new GuestServiceImpl<>(dateService, GuestType.values());
        BuffetService buffetService = new BuffetServiceImpl(buffet);
        BreakfastGroupProvider breakfastGroupProvider = new BreakfastGroupProvider();

        // Generate guests for the season
        List<Guest> allGuests = guestService.generateAllGuests(seasonStart, seasonEnd, numberOfGuests, maxStay);
        BreakfastManager breakfastManager = new BreakfastManager(guestService, buffetService, breakfastGroupProvider, allGuests, seasonStart, seasonEnd);
        // Run breakfast buffet
        breakfastManager.simulateSeason();
        // Run dinner service
        GuestServiceImpl<DinnerGuestType> dinnerGuestService = new GuestServiceImpl<DinnerGuestType>(dateService, DinnerGuestType.values());
        List<Guest> allDinnerGuests = dinnerGuestService.generateAllGuests(seasonStart, seasonEnd, numberOfGuests, 1);
        Storage storage = new Storage(new LinkedList<>());
        KitchenService kitchenService = new KitchenService(storage);
        SuccessMetrics successMetrics = new SuccessMetrics(0,0);
        DinnerService dinnerService = new DinnerService();
        DinnerManager dinnerManager = new DinnerManager(allDinnerGuests, seasonStart, seasonEnd, breakfastGroupProvider, dinnerGuestService, dinnerService, kitchenService, successMetrics);
        dinnerManager.simulateSeason();

    }
}
