package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.data.MealPortion;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BuffetServiceImplTest {
    List<MealPortion> mealList = new ArrayList<>();
    List<MealPortion> mealListForExpected = new ArrayList<>();
    Buffet buffet;
    BuffetService buffetService;


    private void BuffetServiceImplTest() {
        mealList.add(new MealPortion(MealType.SCRAMBLED_EGGS, 1));
        mealList.add(new MealPortion(MealType.SUNNY_SIDE_UP, 3));
        mealList.add(new MealPortion(MealType.MASHED_POTATO, 1));
        mealList.add(new MealPortion(MealType.MUFFIN, 3));
        mealList.add(new MealPortion(MealType.MILK, 1));
        mealList.add(new MealPortion(MealType.CEREAL, 3));
        mealListForExpected.add(new MealPortion(MealType.SCRAMBLED_EGGS, 1));
        mealListForExpected.add(new MealPortion(MealType.SUNNY_SIDE_UP, 3));
        mealListForExpected.add(new MealPortion(MealType.MASHED_POTATO, 1));
        mealListForExpected.add(new MealPortion(MealType.MUFFIN, 3));
        mealListForExpected.add(new MealPortion(MealType.MILK, 1));
        mealListForExpected.add(new MealPortion(MealType.CEREAL, 3));
        Buffet buffet = new Buffet(mealList);
        this.buffet = buffet;
        this.buffetService = new BuffetServiceImpl(buffet);
    }

    @Test
    void refill() {
        BuffetServiceImplTest();
        List<MealPortion> expected = mealListForExpected;
        expected.add(new MealPortion(MealType.BUN, 4));
        expected.add(new MealPortion(MealType.BUN, 4));
        expected.add(new MealPortion(MealType.BUN, 4));
        expected.add(new MealPortion(MealType.BUN, 4));
        Map<MealType, Integer> mealMap = new HashMap<>();
        mealMap.put(MealType.BUN, 4);
        int cycle = 4;
        buffetService.refill(mealMap, cycle);
        List<MealPortion> result = buffet.mealsOnDisplay();
        System.out.println(expected);
        System.out.println(result);
        assertEquals(expected, result);
    }

    @Test
    void consumeFreshest() {
        BuffetServiceImplTest();
        assertTrue(buffetService.consumeFreshest(MealType.SCRAMBLED_EGGS));
    }

    @Test
    void collectWaste() {
        BuffetServiceImplTest();
        int cycle = 7;
        int result = buffetService.collectWaste(cycle);
        assertEquals(140, result);
    }

    @Test
    void closeBuffet() {
        BuffetServiceImplTest();
        int result = buffetService.closeBuffet();
        assertEquals(180, result);
    }
}