package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

import java.util.Map;

public interface BuffetService {

    //TODO:
    void refill(Map<MealType, Integer> portions, int cycle);

    boolean consumeFreshest(MealType mealType);

    int collectWaste(int buffetCycle);

    int closeBuffet();

    Map<MealType, Integer> getMapOfBuffet(Buffet buffet);
}
