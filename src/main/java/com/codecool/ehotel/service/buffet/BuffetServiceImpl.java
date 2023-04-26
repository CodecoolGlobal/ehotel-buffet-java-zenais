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
    public void refill(Map<MealType, Integer> portions) {
        if (portions.isEmpty()) return;
        for (Map.Entry<MealType, Integer> portion : portions.entrySet()) {
            for (int i = 1; i <= portion.getValue(); i++) {
                mealsOnDisplay.add(new MealPortion(portion.getKey(), 1));
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
    public int collectWaste(MealDurability mealDurability, int buffetCycle) {
        int costOfThrownAwayMeals = 0;
        if (buffetCycle == 6) { //we don't know if there are 6 cycles
            for (MealPortion portion : mealsOnDisplay) {//the same loop repeats twice, once is more than enough
                if (portion.meal().getDurability() == MealDurability.SHORT || portion.meal().getDurability() == MealDurability.MEDIUM) { //too complicated Logic, please refactor
                    costOfThrownAwayMeals += portion.meal().getCost();
                    mealsOnDisplay.remove(portion);
                }
            }
        } else if (buffetCycle >= 4 && buffetCycle < 6) {//refactoring needed, irrelevant if the the buffetCycle is last
            for (MealPortion portion : mealsOnDisplay) {
                if (portion.meal().getDurability() == MealDurability.SHORT && buffetCycle - portion.servedAtCycle() == 3) {//please SLAP this
                    costOfThrownAwayMeals += portion.meal().getCost(); //method repeates twice, rethink order
                    mealsOnDisplay.remove(portion);
                }
            }
        }
        //TODO: Martin please write Tests
        return costOfThrownAwayMeals;
    }
}
