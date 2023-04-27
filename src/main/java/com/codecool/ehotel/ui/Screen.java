package com.codecool.ehotel.ui;

import com.codecool.ehotel.service.successMetrics.SuccessMetrics;

import java.util.List;

public class Screen {
    public void displayBreakfastSeason(SuccessMetrics seasonMetrics, List<SuccessMetrics> dailyMetricsList) {
        int day = 1;
        int sumDailyGuests = 0;
        System.out.println("--------------------------");
        for (SuccessMetrics metrics : dailyMetricsList) {
            System.out.println(day + ": " + metrics.getCostOfWastedFood() + " | " + metrics.getNumberOfUnhappyGuests() + "/" + metrics.getTotalGuests());
            day += 1;
            sumDailyGuests += metrics.getTotalGuests();
        }
        System.out.println("--------------------------");
        System.out.println("Total: " + seasonMetrics.getCostOfWastedFood() + " | " + seasonMetrics.getNumberOfUnhappyGuests() + "/" + sumDailyGuests);
        System.out.println("==========================");
    }
}
