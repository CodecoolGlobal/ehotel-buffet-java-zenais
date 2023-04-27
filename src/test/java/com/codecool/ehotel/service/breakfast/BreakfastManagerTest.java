package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.data.MealPortion;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BreakfastManagerTest {
    Buffet buffet;
    BreakfastManager breakfastManager;
    Map<GuestType, Integer > numberOfGuestsPerType;
    int cycle;
    int assumedCostOfUnhappyGuest;

    /*private void BreakfastManagerTest (Buffet buffet, Map<GuestType, Integer > numberOfGuestsPerType, int cycle, int assumedCostOfUnhappyGuest) {
        this.breakfastManager = new BreakfastManager(buffet, GuestService guestService, BuffetService buffetService, )

    }*/
    @Test
    void getOptimalPortions() {
        //Buffet buffet, Map<GuestType, Integer > numberOfGuestsPerType, int cycle, int assumedCostOfUnhappyGuest
        List<MealPortion> mealList = new ArrayList<>();
        mealList.add(new MealPortion(MealType.SCRAMBLED_EGGS, 1));
        Buffet buffet = new Buffet(mealList);
        Map<GuestType, Integer> guestsPerType = new HashMap<>();
        guestsPerType.put(GuestType.BUSINESS, 3);
        guestsPerType.put(GuestType.KID, 2);
        guestsPerType.put(GuestType.TOURIST, 4);
        int cycle = 1;
        int assumedCostOfUnhappyGuest = 1;


    }


}