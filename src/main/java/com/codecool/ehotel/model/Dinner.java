package com.codecool.ehotel.model;

import java.util.Set;

import static com.codecool.ehotel.model.Ingredient.*;

public enum Dinner {

    Simple_burger(Set.of(BURGER_BUNS, LETTUCE, SLICED_TOMATO, MINCED_MEAT, ONION, KETCHUP), 0.17, 42),
    Avocado_burger(Set.of(BURGER_BUNS, LETTUCE, SLICED_TOMATO, MINCED_MEAT, AVOCADO), 0.17, 41),
    Veggie_burger(Set.of(BURGER_BUNS, LETTUCE, SLICED_TOMATO, AVOCADO, SOY_STEAK, ONION, KETCHUP), 0.06, 42),
    Veggie_Soup(Set.of(COOKED_VEGETABLE), 0.1, 8),
    Minestrone_Soup(Set.of(COOKED_VEGETABLE, SLICED_TOMATO, NOODLES), 0.06, 15),
    Spiderman_Spagetti(Set.of(SPAGHETTI, TOMATO_SOUCE, MINCED_MEAT), 0.13, 18),
    Pommes(Set.of(POTATO, KETCHUP), 0.13, 2),
    Big_salat(Set.of(LETTUCE, ONION, AVOCADO, EGG, MAYONNAISE), 0.06, 41),
    NO_DINNER(Set.of(), 0, 0);

    private Set<Ingredient> ingredients;
    private double popularity;
    private int cost;

    Dinner(Set<Ingredient> ingredients, double popularity, int cost) {
        this.ingredients = ingredients;
        this.popularity = popularity;
        this.cost = cost;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getCost() {
        return cost;
    }
}
