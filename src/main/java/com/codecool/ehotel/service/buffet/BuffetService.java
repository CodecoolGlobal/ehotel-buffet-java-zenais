package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.util.Map;

public interface BuffetService {

    //TODO:
    public void refill(Map<MealType, Integer > portions, int cycle);
    public boolean consumeFreshest(MealType mealType);
    public int collectWaste(int buffetCycle);
    public int closeBuffet();
}
