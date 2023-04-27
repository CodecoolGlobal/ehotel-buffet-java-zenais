package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Dinner;
import com.codecool.ehotel.service.breakfastGroup.BreakfastGroupProvider;
import com.codecool.ehotel.service.dinner.kitchen.DinnerService;
import com.codecool.ehotel.service.dinner.kitchen.KitchenService;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.successMetrics.SuccessMetrics;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DinnerManager {
    private List<Guest> allDinnerGuests;
    private java.awt.Menu menu;
    private final LocalDate seasonStart;
    private final LocalDate seasonEnd;
    private BreakfastGroupProvider breakfastGroupProvider;
    private GuestService guestService;
    private DinnerService dinnerService;
    private KitchenService kitchenService;
    private SuccessMetrics successMetrics;

    public DinnerManager(List<Guest> allDinnerGuests, LocalDate seasonStart, LocalDate seasonEnd, BreakfastGroupProvider breakfastGroupProvider, GuestService guestService, DinnerService dinnerService, KitchenService kitchenService,SuccessMetrics successMetrics) {
        this.allDinnerGuests = allDinnerGuests;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.breakfastGroupProvider = breakfastGroupProvider;
        this.guestService = guestService;
        this.dinnerService = dinnerService;
        this.kitchenService = kitchenService;
        this.successMetrics = successMetrics;
    }

    public void simulateSeason() {
        LocalDate date = seasonStart;
        while (isDateInSeason(date)) {
            simulateDinnerOnGivenDate(date);
            date = incrementDate(date);
        }
    }

    private boolean isDateInSeason(LocalDate date) {
        return !date.isAfter(seasonEnd);
    }

    private static LocalDate incrementDate(LocalDate date) {
        date = date.plusDays(1);
        return date;
    }

    private void simulateDinnerOnGivenDate(LocalDate date) {
        Set<Guest> guests = guestService.getGuestsForDay(allDinnerGuests, date);
        kitchenService.supplyKitchen(guests, date);
        Map<Guest, Dinner> orders = dinnerService.createOrdersFromPreferences(guests);
        int guestSatisfaction = (int)Math.round(kitchenService.prepareAvailableOrders(orders, date));
        int dailyCosts = kitchenService.calculateDailyCost(orders);
        int costOfWaste = kitchenService.getStorage().cleanStorage(date);
        successMetrics.addStatistics(date,guestSatisfaction,guests.size(),costOfWaste,dailyCosts,allDinnerGuests.size());
    }
}
