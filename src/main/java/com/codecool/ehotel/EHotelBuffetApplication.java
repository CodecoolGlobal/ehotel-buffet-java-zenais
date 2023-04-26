package com.codecool.ehotel;

import com.codecool.ehotel.data.MealPortion;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EHotelBuffetApplication {

    public static void main(String[] args) {

        // Initialize services
        Buffet buffet = new Buffet(new ArrayList<>());
        BuffetService buffetService = new BuffetServiceImpl(buffet);


        // Generate guests for the season


        // Run breakfast buffet

    }
}
