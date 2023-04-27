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

class StorageServiceTest {
    StorageService emptyStorageService = new StorageService(new ArrayList<>());
    StorageService fullStorageService = new StorageService(List.of(
            new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(5)),
            new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(1)),
            new StorageItem(ONION,LocalDate.now(),LocalDate.now().plusDays(5))
    ));

    @Test
    void addOneItemTest() {
        emptyStorageService.addItem(KETCHUP, LocalDate.now());
        List<StorageItem> storageItemsResult = emptyStorageService.getStorageItems();
        List<StorageItem> expectedList = List.of(new StorageItem(KETCHUP,LocalDate.now(), LocalDate.now().plusDays(KETCHUP.getDaysToExpire())));
        Map<Ingredient, Integer> expectedCounter = Map.of(KETCHUP, 1);
        Map<Ingredient, Integer> resultCounter = emptyStorageService.getIngredientCounter();
        assertEquals(expectedList, storageItemsResult);
        assertEquals(expectedCounter, resultCounter);
    }

    @Test
    void addMoreItemsTest() {
        emptyStorageService.addItem(KETCHUP, LocalDate.now());
        emptyStorageService.addItem(KETCHUP, LocalDate.now());
        emptyStorageService.addItem(ONION, LocalDate.now());
        List<StorageItem> storageItemsResult = emptyStorageService.getStorageItems();
        List<StorageItem> expectedList = List.of(
                new StorageItem(KETCHUP,LocalDate.now(), LocalDate.now().plusDays(KETCHUP.getDaysToExpire())),
                new StorageItem(KETCHUP,LocalDate.now(), LocalDate.now().plusDays(KETCHUP.getDaysToExpire())),
                new StorageItem(ONION,LocalDate.now(), LocalDate.now().plusDays(ONION.getDaysToExpire()))
                );
        Map<Ingredient, Integer> expectedCounter = Map.of(KETCHUP, 2, ONION, 1);
        Map<Ingredient, Integer> resultCounter = emptyStorageService.getIngredientCounter();
        assertEquals(expectedList, storageItemsResult);
        assertEquals(expectedCounter, resultCounter);
    }

    @Test
    void removeOneItem() {
        fullStorageService.removeItem(KETCHUP);
        List<StorageItem> expectedList = List.of(
                new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(5)),
                new StorageItem(ONION,LocalDate.now(),LocalDate.now().plusDays(5))
        );
        Map<Ingredient, Integer> expectedCounter = Map.of(KETCHUP, 1, ONION, 1);
        Map<Ingredient, Integer> resultCounter = fullStorageService.getIngredientCounter();
        List<StorageItem> resultList = fullStorageService.getStorageItems();
        assertEquals(expectedList,resultList);
        assertEquals(expectedCounter, resultCounter);
    }

    @Test
    void cleanStorageEmptyTest(){
        fullStorageService.cleanStorage(LocalDate.now().plusDays(100));
        assertTrue(fullStorageService.getStorageItems().isEmpty());
    }
    @Test
    void cleanStorageTest(){
        StorageService expectedStorageService = new StorageService(List.of(
                new StorageItem(KETCHUP,LocalDate.now(),LocalDate.now().plusDays(5)),
                new StorageItem(ONION,LocalDate.now(),LocalDate.now().plusDays(5))
        ));
        fullStorageService.cleanStorage(LocalDate.now().plusDays(2));
        assertTrue(expectedStorageService.getStorageItems().equals(fullStorageService.getStorageItems()));
    }

}