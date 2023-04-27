package com.codecool.ehotel.ui;

import com.codecool.ehotel.model.MealServiceStatistics;
import com.codecool.ehotel.service.successMetrics.SuccessMetrics;

import java.util.List;

public class Screen {

    public static final String ROW_SEPARATOR = "-------------------------------------------------------------------------------------------------";
    public static final String END_LINE_SEPARATOR = "=================================================================================================";

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

    public void printGuestStatistics(String title,List<MealServiceStatistics> statistics, double averageSatisfaction, double vasteCostRatio) {
        System.out.println(END_LINE_SEPARATOR);
        System.out.println(title);
        System.out.println(ROW_SEPARATOR);

        for (MealServiceStatistics stats : statistics) {
            System.out.format("%-12s | Satisfaction: %2d %% | Daily visit: %2d | Waste cost: %3d coins | Total cost: %d\n",
                    stats.date().toString(),
                    stats.guestSatisfaction(),
                    stats.guestsOnDay(),
                    stats.costOfWaste(),
                    stats.costTotal());
        }
        System.out.println(ROW_SEPARATOR);
        System.out.format("%4s average satisfaction %.2f %% %-18s| Waste-cost ratio %.2f %%\n","", averageSatisfaction,"|", vasteCostRatio );
        System.out.println(END_LINE_SEPARATOR);

    }

}
