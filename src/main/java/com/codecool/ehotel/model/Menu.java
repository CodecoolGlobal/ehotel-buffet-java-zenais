package com.codecool.ehotel.model;

import java.util.Set;

import static com.codecool.ehotel.model.Ingredients.*;

public enum Menu {

    Simple_burger(Set.of(BURGER_BUNS, LETTUCE, SLICED_TOMATO, MINCED_MEAT, ONION, KETCHUP), 0.8, 7),
    Avocado_burger(Set.of(BURGER_BUNS, LETTUCE, SLICED_TOMATO, MINCED_MEAT, AVOCADO), 0.3, 9),
    Veggie_burger(Set.of(BURGER_BUNS, LETTUCE, SLICED_TOMATO, AVOCADO, SOYA_STEAK, ONION, KETCHUP), 0.3, 9),
    Veggie_Soup(Set.of(COOKED_VEGETABLE), 0.5, 5),
    Minestrone_Soup(Set.of(COOKED_VEGETABLE, SLICED_TOMATO, NOODLES), 0.5, 5),
    Spiderman_Spagetti(Set.of(SPAGHETTI, TOMATO_SOUCE, MINCED_MEAT), 0.8, 7),
    Pommes(Set.of(POTATO, KETCHUP), 0.9, 3),
    Big_salat(Set.of(LETTUCE, ONION, AVOCADO, EGG, MAYONNAISE), 0.9, 8);

    private Set<Ingredients> ingredients;
    private double popularity;
    private int cost;

    Menu(Set<Ingredients> ingredients, double popularity, int cost) {
        this.ingredients = ingredients;
        this.popularity = popularity;
        this.cost = cost;
    }

    public Set<Ingredients> getIngredients() {
        return ingredients;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getCost() {
        return cost;
    }
}
