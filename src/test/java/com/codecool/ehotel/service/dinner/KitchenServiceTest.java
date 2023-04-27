package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.DinnerGuestType;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.date.DateService;
import com.codecool.ehotel.service.dinner.kitchen.KitchenService;
import com.codecool.ehotel.service.dinner.kitchen.StorageService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

class KitchenServiceTest {
    DateService dateService = new DateService();
    GuestServiceImpl<DinnerGuestType> dinnerGuestService = new GuestServiceImpl<DinnerGuestType>(dateService, DinnerGuestType.values());
    List<Guest> allDinnerGuests = dinnerGuestService.generateAllGuests(LocalDate.now(), LocalDate.now(), 1, 1);
    KitchenService kitchenService = new KitchenService(new StorageService(new LinkedList<>()));
    @Test
    void serveOrders() {
    }

    @Test
    void supplyKitchenTest() {
        //kitchenService.supplyKitchen(new HashSet<>(allDinnerGuests));
    }

    @Test
    void cleanKitchen() {
    }
}