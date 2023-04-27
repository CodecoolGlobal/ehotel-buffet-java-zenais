package com.codecool.ehotel.model;

import java.util.List;
import java.util.Map;

import static com.codecool.ehotel.model.Dinner.*;

public enum DinnerGuestType {

    BUSINESS(List.of(Avocado_burger, Veggie_Soup, Big_salat)),
    TOURIST(List.of(Simple_burger, Pommes, Veggie_burger)),
    KID(List.of(Spiderman_Spagetti, Pommes, Minestrone_Soup));

    private final List<Dinner> mealPreferences;

    DinnerGuestType(List<Dinner> mealPreferences) {
        this.mealPreferences = mealPreferences;
    }

    public List<Dinner> getMealPreferences() {
        return mealPreferences;
    }
}
