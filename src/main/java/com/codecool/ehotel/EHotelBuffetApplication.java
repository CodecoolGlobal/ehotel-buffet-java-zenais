package com.codecool.ehotel;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.breakfastGroup.BreakfastGroupProvider;
import com.codecool.ehotel.service.date.DateService;
import com.codecool.ehotel.service.dinner.DinnerManager;
import com.codecool.ehotel.service.dinner.kitchen.DinnerService;
import com.codecool.ehotel.service.dinner.kitchen.KitchenService;
import com.codecool.ehotel.service.dinner.kitchen.StorageService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.service.successMetrics.SuccessMetrics;
import com.codecool.ehotel.ui.Screen;

import java.time.LocalDate;
import java.util.Random;

import static com.codecool.ehotel.model.Constants.*;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        int numberOfGuests = 150;
        int maxStay = 7;
        LocalDate seasonStart = LocalDate.parse("2023-01-01");
        LocalDate seasonEnd = LocalDate.parse("2023-02-01");
        Screen screen = new Screen();

        // Initialize services
        Buffet buffet = new Buffet(new ArrayList<>());
        DateService dateService = new DateService();
        GuestServiceImpl<GuestType> guestService = new GuestServiceImpl<>(dateService, GuestType.values());
        BuffetService buffetService = new BuffetServiceImpl(buffet);
        BreakfastGroupProvider breakfastGroupProvider = new BreakfastGroupProvider();

        // Generate guests for the season
        List<Guest> allGuests = guestService.generateAllGuests(seasonStart, seasonEnd, numberOfGuests, maxStay);
        Random random = new Random();
        BreakfastManager breakfastManager = new BreakfastManager(buffet, guestService, buffetService, breakfastGroupProvider, allGuests, seasonStart, seasonEnd, random, screen);
        // Run breakfast buffet
        breakfastManager.simulateSeason();
        // Run dinner service
        GuestServiceImpl<DinnerGuestType> dinnerGuestService = new GuestServiceImpl<DinnerGuestType>(dateService, DinnerGuestType.values());
        List<Guest> allDinnerGuests = dinnerGuestService.generateAllGuests(SEASON_START, SEASON_END, ALL_DINNER_GUESTS,MAX_STAY_DAYS );
        StorageService storageService = new StorageService(new LinkedList<>());
        KitchenService kitchenService = new KitchenService(storageService);
        SuccessMetrics successMetrics = new SuccessMetrics();
        DinnerService dinnerService = new DinnerService();
        DinnerManager dinnerManager = new DinnerManager(allDinnerGuests, SEASON_START, SEASON_END, breakfastGroupProvider, dinnerGuestService, dinnerService, kitchenService, successMetrics);
        dinnerManager.simulateSeason();
        screen.printGuestStatistics("DINNER STATISTICS: ",successMetrics.getStatistics(), successMetrics.getAverageSatisfaction(), successMetrics.getCostWasteRatio() );
    }
}
