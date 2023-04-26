package com.codecool.ehotel.model;

import static com.codecool.ehotel.model.MealDurability.*;

public enum Ingredients {
    ONION(5, LONG),
    AVOCADO(5, SHORT),
    SLICED_TOMATO(5, SHORT),
    MAYONNAISE(8, SHORT),
    MINCED_MEAT(15, MEDIUM),
    LETTUCE(8, SHORT),
    BURGER_BUNS(8, MEDIUM),
    SOYA_STEAK(10, MEDIUM),
    COOKED_VEGETABLE(8, MEDIUM),
    EGG(15, LONG),
    NOODLES(2, LONG),
    TOMATO_SOUCE(1,MEDIUM ),
    SPAGHETTI(2, LONG ), POTATO(1,LONG), KETCHUP(1,LONG );
    private MealDurability mealDurability;
    int cost;

    Ingredients(int cost, MealDurability mealDurability) {
        this.cost = cost;
        this.mealDurability = mealDurability;
    }

    public int getCost() {
        return cost;
    }

    public MealDurability getDurability() {
        return mealDurability;
    }
}
