package com.codecool.ehotel.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Guest <T>(String name, T guestType, LocalDate checkIn, LocalDate checkOut) {
}
