package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.DinnerGuestType;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfastGroup.BreakfastGroupProvider;
import com.codecool.ehotel.service.date.DateService;
import com.codecool.ehotel.service.dinner.kitchen.DinnerService;
import com.codecool.ehotel.service.dinner.kitchen.KitchenService;
import com.codecool.ehotel.service.dinner.kitchen.StorageService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.service.successMetrics.SuccessMetrics;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

class DinnerManagerTest {
    LocalDate seasonStart = LocalDate.parse("2023-01-01");
    LocalDate seasonEnd = LocalDate.parse("2023-02-01");
    DateService dateService = new DateService();
    int numberOfGuests = 100;
    BreakfastGroupProvider breakfastGroupProvider = new BreakfastGroupProvider();
    GuestServiceImpl<DinnerGuestType> dinnerGuestService = new GuestServiceImpl<DinnerGuestType>(dateService, DinnerGuestType.values());
    List<Guest> allDinnerGuests = dinnerGuestService.generateAllGuests(seasonStart, seasonEnd, numberOfGuests, 1);
    DinnerService dinnerService = new DinnerService();
    KitchenService kitchenService = new KitchenService(new StorageService(new LinkedList<>()));
    SuccessMetrics successMetrics = new SuccessMetrics(0,0);
    DinnerManager dinnerManager = new DinnerManager(allDinnerGuests, seasonStart, seasonEnd, breakfastGroupProvider, dinnerGuestService, dinnerService, kitchenService, successMetrics);


    @Test
    void simulateSeasonTest() {
        dinnerManager.simulateSeason();
    }
}