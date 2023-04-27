package com.codecool.ehotel.service.dinner.kitchen;

import com.codecool.ehotel.model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class KitchenService {
    private StorageService storageService;

    public KitchenService(StorageService storageService) {
        this.storageService = storageService;
    }

    public double prepareAvailableOrders(Map<Guest, Dinner> orders, LocalDate date) {
        //TODO: test
        double orderQuality = 0;
        for (Map.Entry<Guest, Dinner> order : orders.entrySet()) {
            Dinner dinner = order.getValue();
            if (isMealAvailable(dinner)) {
                List<StorageItem> dinnerIngredients = removeDinnerIngredients(dinner);
                orderQuality += getDinnerQuality(dinnerIngredients, date);
            } else {
                orders.replace(order.getKey(), order.getValue(), Dinner.NO_DINNER);
            }
        }
        return orderQuality / orders.size();
    }

    private double getDinnerQuality(List<StorageItem> dinnerIngredients, LocalDate date) {
        //TODO: Test
        double freshnessSum = 0.0;
        for (StorageItem storageItem : dinnerIngredients) {
            double daysBetween = Period.between(storageItem.purchaseDate(), date).getDays();
            double ingredientFreshness = 1.0 - (daysBetween / storageItem.ingredient().getDaysToExpire());
            freshnessSum += ingredientFreshness;
        }
        return freshnessSum / dinnerIngredients.size();
    }


    private List<StorageItem> removeDinnerIngredients(Dinner dinner) {
        //TODO: test
        List<StorageItem> removedIngredients = new LinkedList<>();
        for (Ingredient ingredient : dinner.getIngredients()) {
            removedIngredients.add(storageService.removeItem(ingredient));
        }
        return removedIngredients;
    }

    private boolean isMealAvailable(Dinner dinner) {
        //TODO: test
        for (Ingredient ingredient : dinner.getIngredients()) {
            if (!storageService.hasIngredient(ingredient)) {
                return false;
            }
        }
        return true;
    }

    public void supplyKitchen(Set<Guest> guests, LocalDate date) {
        //TODO: test
        Map<Ingredient, Integer> guestIngredients = getOptimalQuantityOfIngredients(guests);
        for (Map.Entry<Ingredient, Integer> ingredient : guestIngredients.entrySet()) {
            for (int i = 0; i < ingredient.getValue(); i++) {
                storageService.addItem(ingredient.getKey(), date);
            }
        }
    }

    public StorageService getStorage() {
        return storageService;
    }

    private Map<Ingredient, Integer> getOptimalQuantityOfIngredients(Set<Guest> guestSet) {
        //TODO: refactor and test
        int[] quotients = {5, 3, 2};
        int sum = 10;

        Map<Ingredient, Integer> ingredientsEstimate = new HashMap<>();
        for (Guest guest : guestSet) {
            DinnerGuestType guestType = DinnerGuestType.valueOf(guest.guestType().toString());
            for (int index = 0; index < 3; index++) {
                updateIngredientsEstimate(ingredientsEstimate, guestType, index, quotients);
            }
        }

        for (Map.Entry<Ingredient, Integer> ingredient : ingredientsEstimate.entrySet()             ) {
            ingredientsEstimate.replace(ingredient.getKey(), ingredient.getValue(), ingredient.getValue() / sum);
        }

        return ingredientsEstimate;
    }

    private static void updateIngredientsEstimate(Map<Ingredient, Integer> ingredientsEstimate, DinnerGuestType guestType, int preferenceIndex, int[] quotients) {
       //TODO: test
        for (Ingredient ingredient : guestType.getMealPreferences().get(preferenceIndex).getIngredients()) {
            if (ingredientsEstimate.containsKey(ingredient)) {
                int quantity = ingredientsEstimate.get(ingredient);
                ingredientsEstimate.replace(ingredient, quantity, quantity + quotients[preferenceIndex]);
            } else {
                ingredientsEstimate.put(ingredient, quotients[preferenceIndex]);
            }
        }
    }
}
