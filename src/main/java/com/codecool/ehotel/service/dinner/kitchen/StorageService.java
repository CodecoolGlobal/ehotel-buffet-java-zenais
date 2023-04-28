package com.codecool.ehotel.service.dinner.kitchen;

import com.codecool.ehotel.model.Ingredient;
import com.codecool.ehotel.model.StorageItem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StorageService {
    List<StorageItem> storageItems;
    Map<Ingredient, Integer> ingredientCounter;

    public StorageService(List<StorageItem> storageItems) {
        this.storageItems = new LinkedList<>(storageItems);
        ingredientCounter = countIngredients(storageItems);
    }

    private Map<Ingredient, Integer> countIngredients(List<StorageItem> storageItems) {
        Map<Ingredient, Integer> ingredientCounter = new HashMap<>();
        for (StorageItem storageItem : storageItems) {
            incrementIngredient(ingredientCounter, storageItem.ingredient());
        }
        return ingredientCounter;
    }

    private void incrementIngredient(Map<Ingredient, Integer> ingredientCounter, Ingredient storageItem) {
        if (ingredientCounter.containsKey(storageItem)) {
            Integer quantity = ingredientCounter.get(storageItem);
            ingredientCounter.replace(storageItem, quantity, quantity + 1);
        } else {
            ingredientCounter.put(storageItem, 1);
        }
    }

    public void addItem(Ingredient ingredient, LocalDate date) {
        LocalDate expiryDate = date.plusDays(ingredient.getDaysToExpire());
        StorageItem storageItem = new StorageItem(ingredient, date, expiryDate);
        storageItems.add(storageItem);
        incrementIngredient(ingredientCounter, ingredient);
    }

    public StorageItem removeItem(Ingredient ingredient) {
        if (hasIngredient(ingredient)) {
            for (int storageIndex = storageItems.size() - 1; storageIndex >= 0; storageIndex--) {
                StorageItem storageItem = storageItems.get(storageIndex);
                Integer quantity = ingredientCounter.get(ingredient);
                if (storageItem.ingredient().equals(ingredient)) {
                    ingredientCounter.replace(ingredient, quantity, quantity - 1);
                    storageItems.remove(storageItem);
                    return storageItem;
                }
            }
        }
        return null;
    }

    public boolean hasIngredient(Ingredient ingredient) {
        return ingredientCounter.containsKey(ingredient) &&
                ingredientCounter.get(ingredient) > 0;
    }

    public int cleanStorage(LocalDate date) {
        int sumCostOfWaste = 0;
        List<StorageItem> freshStorageItems = new LinkedList<>();
        for (StorageItem item : this.storageItems) {
            if (item.expiryDate().isAfter(date)) {
                freshStorageItems.add(item);
            } else {
                sumCostOfWaste += item.ingredient().getCost();
            }
        }
        this.storageItems = freshStorageItems;
        this.ingredientCounter = countIngredients(freshStorageItems);
        return sumCostOfWaste;
    }

    public List<StorageItem> getStorageItems() {
        return this.storageItems;
    }


    public Map<Ingredient, Integer> getIngredientCounter() {
        return this.ingredientCounter;
    }

    ;

}
