package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.DinnerGuestType;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.date.DateService;
import com.codecool.ehotel.service.dinner.kitchen.DinnerService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

class DinnerServiceTest {

    DateService dateService = new DateService();
    GuestServiceImpl<DinnerGuestType> dinnerGuestService = new GuestServiceImpl<DinnerGuestType>(dateService, DinnerGuestType.values());
    List<Guest> allDinnerGuests = dinnerGuestService.generateAllGuests(LocalDate.now(), LocalDate.now(), 5, 1);
    DinnerService dinnerService = new DinnerService();
    @Test
    void getOrdersTest() {
       System.out.println( dinnerService.createOrdersFromPreferences(new HashSet<>(allDinnerGuests)));
    }

    @Test
    void consume() {
    }


}