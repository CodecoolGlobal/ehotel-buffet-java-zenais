package com.codecool.ehotel.service.dinner.kitchen;

import com.codecool.ehotel.model.Ingredient;
import com.codecool.ehotel.model.StorageItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codecool.ehotel.model.Ingredient.*;
import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    Storage emptyStorage = new Storage(new ArrayList<>());
    Storage fullStorage = new Storage(List.of(
            new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(5)),
            new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(1)),
            new StorageItem(ONION,LocalDate.now(),LocalDate.now().plusDays(5))
    ));

    @Test
    void addOneItemTest() {
        emptyStorage.addItem(KETCHUP, LocalDate.now());
        List<StorageItem> storageItemsResult = emptyStorage.getStorageItems();
        List<StorageItem> expectedList = List.of(new StorageItem(KETCHUP,LocalDate.now(), LocalDate.now().plusDays(KETCHUP.getDaysToExpire())));
        Map<Ingredient, Integer> expectedCounter = Map.of(KETCHUP, 1);
        Map<Ingredient, Integer> resultCounter = emptyStorage.getIngredientCounter();
        assertEquals(expectedList, storageItemsResult);
        assertEquals(expectedCounter, resultCounter);
    }

    @Test
    void addMoreItemsTest() {
        emptyStorage.addItem(KETCHUP, LocalDate.now());
        emptyStorage.addItem(KETCHUP, LocalDate.now());
        emptyStorage.addItem(ONION, LocalDate.now());
        List<StorageItem> storageItemsResult = emptyStorage.getStorageItems();
        List<StorageItem> expectedList = List.of(
                new StorageItem(KETCHUP,LocalDate.now(), LocalDate.now().plusDays(KETCHUP.getDaysToExpire())),
                new StorageItem(KETCHUP,LocalDate.now(), LocalDate.now().plusDays(KETCHUP.getDaysToExpire())),
                new StorageItem(ONION,LocalDate.now(), LocalDate.now().plusDays(ONION.getDaysToExpire()))
                );
        Map<Ingredient, Integer> expectedCounter = Map.of(KETCHUP, 2, ONION, 1);
        Map<Ingredient, Integer> resultCounter = emptyStorage.getIngredientCounter();
        assertEquals(expectedList, storageItemsResult);
        assertEquals(expectedCounter, resultCounter);
    }

    @Test
    void removeOneItem() {
        fullStorage.removeItem(KETCHUP);
        List<StorageItem> expectedList = List.of(
                new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(5)),
                new StorageItem(ONION,LocalDate.now(),LocalDate.now().plusDays(5))
        );
        Map<Ingredient, Integer> expectedCounter = Map.of(KETCHUP, 1, ONION, 1);
        Map<Ingredient, Integer> resultCounter = fullStorage.getIngredientCounter();
        List<StorageItem> resultList = fullStorage.getStorageItems();
        assertEquals(expectedList,resultList);
        assertEquals(expectedCounter, resultCounter);
    }

    @Test
    void cleanStorageEmptyTest(){
        fullStorage.cleanStorage(LocalDate.now().plusDays(100));
        assertTrue(fullStorage.getStorageItems().isEmpty());
    }
    @Test
    void cleanStorageTest(){
        Storage expectedStorage = new Storage(List.of(
                new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(5)),
                new StorageItem(ONION,LocalDate.now(),LocalDate.now().plusDays(5))
        ));
        fullStorage.cleanStorage(LocalDate.now().plusDays(2));
        assertTrue(expectedStorage.getStorageItems().equals(fullStorage.getStorageItems()));
    }

}