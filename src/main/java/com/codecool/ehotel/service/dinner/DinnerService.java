package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.DinnerGuestType;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Dinner;

import java.util.*;

public class DinnerService {
    public Map<Guest, Dinner> getOrders(Set<Guest> guests) {
        Map<Guest, Dinner> orders =  new HashMap<>();
        for (Guest guest : guests) {
            pickOrderFromPreferences(orders, guest);
        }
        return orders;
    }

    public static void pickOrderFromPreferences(Map<Guest, Dinner> orders, Guest guest) {
        List<Dinner> dinnerPreferences = DinnerGuestType.valueOf(guest.guestType().toString()).getMealPreferences();
        double randomMeal = Math.random();
        if (randomMeal < 0.5){
            orders.put(guest, dinnerPreferences.get(0));
        } else if (randomMeal < 0.85) {
            orders.put(guest, dinnerPreferences.get(1));
        } else {
            orders.put(guest, dinnerPreferences.get(2));
        }
    }

    public int consume(Map<Guest, Dinner> orders) {
        //TODO
        return -1;
    }

}
