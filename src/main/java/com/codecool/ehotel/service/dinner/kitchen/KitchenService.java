package com.codecool.ehotel.service.dinner.kitchen;

import com.codecool.ehotel.model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.IntStream;

import static com.codecool.ehotel.model.Constants.*;

public class KitchenService {
    private final StorageService  storageService;

    public KitchenService(StorageService storageService) {
        this.storageService = storageService;
    }

    public double assessOrderQuality(Map<Guest, Dinner> orders, LocalDate date) {
        //TODO: test
        double orderQuality = 0;
        for (Map.Entry<Guest, Dinner> order : orders.entrySet()) {
            Dinner dinner = order.getValue();
            orderQuality = getOrder(orders, date, orderQuality, order, dinner);
        }
        return calculateAverage(orders, orderQuality);
    }

    private static double calculateAverage(Map<Guest, Dinner> orders, double orderQuality) {
        return (orderQuality / orders.size()) * 100;
    }

    private double getOrder(Map<Guest, Dinner> orders, LocalDate date, double orderQuality, Map.Entry<Guest, Dinner> order, Dinner dinner) {
        if (isMealAvailable(dinner)) {
            List<StorageItem> dinnerIngredients = removeDinnerIngredients(dinner);
            orderQuality += getDinnerQuality(dinnerIngredients, date);
        } else {
            assignNoOrder(orders, order);
        }
        return orderQuality;
    }

    private static void assignNoOrder(Map<Guest, Dinner> orders, Map.Entry<Guest, Dinner> order) {
        orders.replace(order.getKey(), order.getValue(), Dinner.NO_DINNER);
    }

    private double getDinnerQuality(List<StorageItem> dinnerIngredients, LocalDate date) {
        double freshnessSum = 0.0;
        freshnessSum = getFreshnessSum(dinnerIngredients, date, freshnessSum);
        return getAverage(dinnerIngredients, freshnessSum);
    }

    private static double getFreshnessSum(List<StorageItem> dinnerIngredients, LocalDate date, double freshnessSum) {
        for (StorageItem storageItem : dinnerIngredients) {
            double daysBetween = getDaysBetween(date, storageItem);
            double ingredientFreshness = calculateIngredientFreshness(storageItem, daysBetween);
            freshnessSum += ingredientFreshness;
        }
        return freshnessSum;
    }

    private static double calculateIngredientFreshness(StorageItem storageItem, double daysBetween) {
        return 1.0 - (daysBetween / storageItem.ingredient().getDaysToExpire());
    }

    private static double getDaysBetween(LocalDate date, StorageItem storageItem) {
        return Period.between(storageItem.purchaseDate(), date).getDays();
    }

    private static double getAverage(List<StorageItem> dinnerIngredients, double freshnessSum) {
        return freshnessSum / dinnerIngredients.size();
    }


    private List<StorageItem> removeDinnerIngredients(Dinner dinner) {
        List<StorageItem> removedIngredients = new LinkedList<>();
        for (Ingredient ingredient : dinner.getIngredients()) {
            removedIngredients.add(storageService.removeItem(ingredient));
        }
        return removedIngredients;
    }

    private boolean isMealAvailable(Dinner dinner) {
        for (Ingredient ingredient : dinner.getIngredients()) {
            if (!storageService.hasIngredient(ingredient)) {
                return false;
            }
        }
        return true;
    }

    public void restockKitchen(Set<Guest> guests, LocalDate date) {
        Map<Ingredient, Integer> guestIngredients = getOptimalQuantityOfIngredients(guests);
        fillStorageWithIngredients(date, guestIngredients);
    }

    private void fillStorageWithIngredients(LocalDate date, Map<Ingredient, Integer> guestIngredients) {
        for (Map.Entry<Ingredient, Integer> ingredient : guestIngredients.entrySet()) {
            IntStream.range(0, ingredient.getValue()).forEachOrdered(i -> storageService.addItem(ingredient.getKey(), date));
        }
    }

    public StorageService getStorage() {
        return storageService;
    }

    private Map<Ingredient, Integer> getOptimalQuantityOfIngredients(Set<Guest> guestSet) {
        Map<Ingredient, Integer> ingredientsEstimate = new HashMap<>();
        getWeightedIngredientsEstimate(guestSet, ingredientsEstimate);
        normalizeIngredientsEstimate(ingredientsEstimate);
        return ingredientsEstimate;
    }

    private static void getWeightedIngredientsEstimate(Set<Guest> guestSet,
                                                       Map<Ingredient, Integer> ingredientsEstimate) {
        for (Guest guest : guestSet) {
            DinnerGuestType guestType = DinnerGuestType.valueOf(guest.guestType().toString());
            for (int index = 0; index < 3; index++) {
                updateIngredientsEstimate(ingredientsEstimate, guestType, index);
            }
        }
    }

    private static void normalizeIngredientsEstimate(Map<Ingredient, Integer> ingredientsEstimate) {
        for (Map.Entry<Ingredient, Integer> ingredient : ingredientsEstimate.entrySet()             ) {
            ingredientsEstimate.replace(ingredient.getKey(), ingredient.getValue(), getAverage(ingredient));
        }
    }

    private static int getAverage(Map.Entry<Ingredient, Integer> ingredient) {
        return ingredient.getValue() / SUM_OF_QUOTIENTS;
    }

    private static void updateIngredientsEstimate(Map<Ingredient, Integer> ingredientsEstimate, DinnerGuestType guestType, int preferenceIndex) {
       //TODO: test
        for (Ingredient ingredient : guestType.getMealPreferences().get(preferenceIndex).getIngredients()) {
            if (ingredientsEstimate.containsKey(ingredient)) {
                int quantity = ingredientsEstimate.get(ingredient);
                ingredientsEstimate.replace(ingredient, quantity, quantity + QUOTIENTS[preferenceIndex]);
            } else {
                ingredientsEstimate.put(ingredient, QUOTIENTS[preferenceIndex]);
            }
        }
    }

    public int calculateDailyCost(Map<Guest, Dinner> orders) {
        int sumCost = 0;
        for (Dinner dinner: orders.values()) {
            sumCost += dinner.getCost();
        }
        return sumCost;
    }
}
