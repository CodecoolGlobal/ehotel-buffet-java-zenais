package com.codecool.ehotel.model;

import java.time.LocalDate;

public record MealServiceStatistics(LocalDate date, int guestSatisfaction, int guestsOnDay, int costOfWaste, int costTotal, int totalGuests) {
}
