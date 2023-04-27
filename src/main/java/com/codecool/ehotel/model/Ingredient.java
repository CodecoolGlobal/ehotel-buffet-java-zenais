package com.codecool.ehotel.model;

import static com.codecool.ehotel.model.MealDurability.*;

public enum Ingredient {
    ONION(5, LONG, 5 ),
    AVOCADO(5, SHORT, 2),
    SLICED_TOMATO(5, SHORT, 1),
    MAYONNAISE(8, SHORT, 4),
    MINCED_MEAT(15, MEDIUM, 3),
    LETTUCE(8, SHORT, 7),
    BURGER_BUNS(8, LONG, 30),
    SOY_STEAK(10, MEDIUM, 7),
    COOKED_VEGETABLE(8, MEDIUM, 5),
    EGG(15, LONG, 14),
    NOODLES(2, LONG,60),
    TOMATO_SOUCE(1,MEDIUM, 60 ),
    SPAGHETTI(2, LONG, 60 ),
    POTATO(1,LONG, 30),
    KETCHUP(1,LONG, 30 );
    private MealDurability mealDurability;
    private int daysToExpire;
    int cost;

    //TODO: add date of expiry
    Ingredient(int cost, MealDurability mealDurability, int daysToExpire) {
        this.cost = cost;
        this.mealDurability = mealDurability;
        this.daysToExpire = daysToExpire;
    }
    public int getDaysToExpire(){
        return this.daysToExpire;
    }

    public int getCost() {
        return cost;
    }

    public MealDurability getDurability() {
        return mealDurability;
    }
}
