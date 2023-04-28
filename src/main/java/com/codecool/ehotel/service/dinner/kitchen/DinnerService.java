package com.codecool.ehotel.service.dinner.kitchen;

import com.codecool.ehotel.model.Dinner;
import com.codecool.ehotel.model.DinnerGuestType;
import com.codecool.ehotel.model.Guest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DinnerService {
    private  Map<Double, Integer> preferences;
    
    public Map<Guest, Dinner> createOrdersFromPreferences(Set<Guest> guests) {
        Map<Guest, Dinner> orders = new HashMap<>();
        for (Guest guest : guests) {
            pickOrderFromPreferences(orders, guest);
        }
        return orders;
    }

    private void pickOrderFromPreferences(Map<Guest, Dinner> orders, Guest guest) {
        List<Dinner> dinnerPreferences = DinnerGuestType.valueOf(guest.guestType().toString()).getMealPreferences();
        double randomMeal = Math.random();
        Integer index = preferences.entrySet().stream()
                .filter(e -> e.getKey() < randomMeal)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow();
        orders.put(guest, dinnerPreferences.get(index));
    }


}
