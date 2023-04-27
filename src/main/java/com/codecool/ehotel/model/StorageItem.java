package com.codecool.ehotel.model;

import java.time.LocalDate;

public record StorageItem(Ingredient ingredient, LocalDate purchaseDate, LocalDate expiryDate) {
}
