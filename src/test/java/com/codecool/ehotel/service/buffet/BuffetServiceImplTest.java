package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.data.MealPortion;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuffetServiceImplTest {
    BuffetService buffetService;

    private void BuffetServiceImplTest() {
        List<MealPortion> mealList = new ArrayList<>();
        mealList.add(new MealPortion(MealType.SCRAMBLED_EGGS, 1));
        mealList.add(new MealPortion(MealType.SUNNY_SIDE_UP, 3));
        mealList.add(new MealPortion(MealType.MASHED_POTATO, 1));
        mealList.add(new MealPortion(MealType.MUFFIN, 3));
        mealList.add(new MealPortion(MealType.MILK, 1));
        mealList.add(new MealPortion(MealType.CEREAL, 3));
        Buffet buffet = new Buffet(mealList);
        buffetService = new BuffetServiceImpl(buffet);
    }

   /* @Test
    void refill() {
        Map<MealType, Integer> mealMap = new HashMap<>();
        mealMap.put(MealType.BUN, 4);
        int cycle = 4;
        buffetService.refill(mealMap, cycle);

        //buffetService.refill();

    }*/

    @Test
    void consumeFreshest() {
        assertTrue(buffetService.consumeFreshest(MealType.SCRAMBLED_EGGS));
    }

    @Test
    void collectWaste() {
    }

    @Test
    void closeBuffet() {
    }
}