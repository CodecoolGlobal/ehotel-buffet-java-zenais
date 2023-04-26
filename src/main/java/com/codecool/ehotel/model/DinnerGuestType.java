package com.codecool.ehotel.model;

import java.util.List;

import static com.codecool.ehotel.model.Menu.*;

public enum DinnerGuestType {

    BUSINESS(List.of(Avocado_burger, Veggie_Soup, Big_salat)),
    TOURIST(List.of(Simple_burger, Pommes, Veggie_burger)),
    KID(List.of(Minestrone_Soup, Spiderman_Spagetti, Pommes));

    private List<Menu> mealPreferences;

    DinnerGuestType(List<Menu> mealPreferences) {
        this.mealPreferences = mealPreferences;
    }

    public List<Menu> getMealPreferences() {
        return mealPreferences;
    }
}
