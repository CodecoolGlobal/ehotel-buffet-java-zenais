package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.util.Map;

public class BuffetServiceImpl implements BuffetService{

    Buffet buffet;
    public BuffetServiceImpl(Buffet buffet){
        this.buffet = buffet;
    }
    @Override
    public void refill(Map<MealType, Integer> portions) {
        //TODO: Martin
    }

    @Override
    public boolean consumeFreshest(MealType mealType) {
        //TODO: Martin
        return false;
    }

    @Override
    public int collectWaste(MealDurability mealDurability) {
        //TODO: Martin
        return 0;
    }
}
