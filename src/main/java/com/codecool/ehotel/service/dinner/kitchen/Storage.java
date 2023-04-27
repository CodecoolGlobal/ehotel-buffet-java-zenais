package com.codecool.ehotel.service.dinner.kitchen;

import com.codecool.ehotel.model.Ingredient;
import com.codecool.ehotel.model.StorageItem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Storage {
    List<StorageItem> storageItems;
    Map<Ingredient, Integer> ingredientCounter;

    public Storage(List<StorageItem> storageItems) {
        this.storageItems = new LinkedList<>(storageItems);
        ingredientCounter = countIngredients(storageItems);
    }

    private Map<Ingredient, Integer> countIngredients(List<StorageItem> storageItems) {
        Map<Ingredient, Integer> ingredientCounter = new HashMap<>();
        for (StorageItem storageItem : storageItems) {
            if (ingredientCounter.containsKey(storageItem.ingredient())) {
                Integer quantity = ingredientCounter.get(storageItem.ingredient());
                ingredientCounter.replace(storageItem.ingredient(), quantity, quantity + 1);
            } else {
                ingredientCounter.put(storageItem.ingredient(), 1);
            }
        }
        return ingredientCounter;
    }

    public void addItem(Ingredient ingredient, LocalDate date) {
        LocalDate expiryDate = date.plusDays(ingredient.getDaysToExpire());
        StorageItem storageItem = new StorageItem(ingredient, date, expiryDate);
        storageItems.add(storageItem);
        if (ingredientCounter.containsKey(ingredient)) {
            Integer quantity = ingredientCounter.get(ingredient);
            ingredientCounter.replace(ingredient, quantity, quantity + 1);
        } else {
            ingredientCounter.put(ingredient, 1);
        }
    }

    public void removeItem(Ingredient ingredient) {
        if (!hasIngredient(ingredient)) {
            return;
        }
        for (int storageIndex = storageItems.size() - 1; storageIndex >= 0; storageIndex--) {
            StorageItem storageItem = storageItems.get(storageIndex);
            Integer quantity = ingredientCounter.get(ingredient);

            if (storageItem.ingredient().equals(ingredient)) {
                storageItems.remove(storageItem);
                ingredientCounter.replace(ingredient, quantity, quantity - 1);
                return;
            }
        }
    }

    public boolean hasIngredient(Ingredient ingredient) {
        if (!ingredientCounter.containsKey(ingredient)) {
            return false;
        }
        if (ingredientCounter.get(ingredient) <= 0) {
            return false;
        }
        return true;
    }

    public int cleanStorage(LocalDate date) {
        int sumCostOfWaste = 0;
        List<StorageItem> freshStorageItems = new LinkedList<>();
        for (StorageItem item : this.storageItems) {
            if (item.expiryDate().isAfter(date)){
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
