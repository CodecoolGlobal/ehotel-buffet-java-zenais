package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.data.MealPortion;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class BuffetServiceImpl implements BuffetService {

    Buffet buffet;
    List<MealPortion> mealsOnDisplay;

    public BuffetServiceImpl(Buffet buffet) {
        this.buffet = buffet;
        this.mealsOnDisplay = buffet.mealsOnDisplay();
    }

    @Override
    public void refill(Map<MealType, Integer> portions, int cycle) {
        if (portions.isEmpty()) return;
        for (Map.Entry<MealType, Integer> portion : portions.entrySet()) {
            for (int i = 1; i <= portion.getValue(); i++) {
                mealsOnDisplay.add(new MealPortion(portion.getKey(), cycle));
            }
        }
        //TODO: Martin/done?
    }

    @Override
    public boolean consumeFreshest(MealType consumedMeal) {
        for (int i = mealsOnDisplay.size() - 1; i >= 0; i--) {
            if (mealsOnDisplay.get(i).meal() == consumedMeal) {
                mealsOnDisplay.remove(i);
                return true;
            }
        }
        return false;
        //TODO: Martin/done?
    }

    @Override
    public int collectWaste(int buffetCycle) {
        int costOfThrownAwayMeals = 0;

            for (int i = 0; i < mealsOnDisplay.size(); i++) {
                if (mealsOnDisplay.get(i).meal().getDurability() == MealDurability.SHORT && buffetCycle - mealsOnDisplay.get(i).servedAtCycle() > 2) {
                    costOfThrownAwayMeals += mealsOnDisplay.get(i).meal().getCost();
                    mealsOnDisplay.remove(i);
                    i--;
                }
            }
        //TODO: Martin
        return costOfThrownAwayMeals;
    }
    public int closeBuffet() {
    int costOfThrownAwayMeals = 0;
        for (int i = 0; i < mealsOnDisplay.size(); i++) {
            if (mealsOnDisplay.get(i).meal().getDurability() != MealDurability.LONG) {
                costOfThrownAwayMeals += mealsOnDisplay.get(i).meal().getCost();
                mealsOnDisplay.remove(i);
                i--;
            }
        }
        return costOfThrownAwayMeals;
    }
}
