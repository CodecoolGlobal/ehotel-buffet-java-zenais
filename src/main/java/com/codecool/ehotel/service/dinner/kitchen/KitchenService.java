package com.codecool.ehotel.service.dinner.kitchen;

import com.codecool.ehotel.model.DinnerGuestType;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Dinner;
import com.codecool.ehotel.model.Ingredient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KitchenService {
    private Storage storage;

    public KitchenService(Storage storage) {
        this.storage = storage;
    }

    public double serveOrders(Map<Guest, Dinner> orders) {
        //TODO: test
        double orderQuality = 0;
        for (Map.Entry<Guest, Dinner> order : orders.entrySet()) {
            Dinner dinner = order.getValue();
            if (isMealAvailable(dinner)) {
                orderQuality += 1;
                removeDinnerIngredients(dinner);
            }
        }
        return orderQuality / orders.size();
    }


    private void removeDinnerIngredients(Dinner dinner) {
        //TODO: test
        for (Ingredient ingredient : dinner.getIngredients()) {
            storage.removeItem(ingredient);
        }
    }

    private boolean isMealAvailable(Dinner dinner) {
        //TODO: test
        for (Ingredient ingredient : dinner.getIngredients()) {
            if (!storage.hasIngredient(ingredient)) {
                return false;
            }
        }
        return true;
    }

    public void supplyKitchen(Set<Guest> guests, LocalDate date) {
        //TODO: test
        List<Ingredient> guestIngredients = getOptimalIngredients(guests);
        for (Ingredient ingredient : guestIngredients) {
            storage.addItem(ingredient, date);
        }
    }

    public Storage getStorage() {
        return storage;
    }

    private List<Ingredient> getOptimalIngredients(Set<Guest> guestSet) {
        double firstPercentage = 0.5;
        double secondPercentage = 0.3;
        double thirdPercentage = 0.2;

        Map<DinnerGuestType, Integer> guestTypeQuantity = new HashMap<>();
        for (Guest guest : guestSet) {
            DinnerGuestType guestType = DinnerGuestType.valueOf(guest.guestType().toString());
            if (guestTypeQuantity.containsKey(guestType)) {
                Integer quantity = guestTypeQuantity.get(guestType);
                guestTypeQuantity.replace(guestType, quantity, quantity + 1);
            } else {
                guestTypeQuantity.put(guestType, 1);
            }
        }

        //TODO:
        return null;
    }
}
