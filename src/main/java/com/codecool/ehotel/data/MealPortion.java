package com.codecool.ehotel.data;

import com.codecool.ehotel.model.MealType;

import java.sql.Timestamp;

public record MealPortion(MealType meal, Timestamp timestamp) {
}
