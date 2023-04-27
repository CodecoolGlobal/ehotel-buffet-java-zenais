package com.codecool.ehotel.service.successMetrics;

import com.codecool.ehotel.model.MealServiceStatistics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuccessMetrics {
    int unhappyGuests;
    int costOfWastedFood;
    private List<MealServiceStatistics> statistics;

    int totalGuests;
    public SuccessMetrics(int unhappyGuests, int costOfWastedFood, int totalGuests){
        this.unhappyGuests = unhappyGuests;
        this.costOfWastedFood = costOfWastedFood;
        this.totalGuests = totalGuests;
        statistics = new ArrayList<>();
    }

    public SuccessMetrics() {
        statistics = new ArrayList<>();
    }

    public void addStatistics(LocalDate date, int guestSatisfaction, int guestsOnDay, int costOfWaste, int costTotal, int totalGuests){
        MealServiceStatistics dailyStatistics = new MealServiceStatistics(date, guestSatisfaction, guestsOnDay, costOfWaste, costTotal,totalGuests);
        statistics.add(dailyStatistics);
    }

    public List<MealServiceStatistics> getStatistics() {
        return statistics;
    }

    public int getNumberOfUnhappyGuests(){
        return unhappyGuests;
    }
    public void addUnhappyGuests(int unhappyGuests){
        this.unhappyGuests += unhappyGuests;
    }

    public void addCostOfWastedFood(int costOfWastedFood){
        this.costOfWastedFood += costOfWastedFood;
    }

    public int getCostOfWastedFood(){
        return costOfWastedFood;
    }

    public int getTotalGuests() { return totalGuests;}

    public double getAverageSatisfaction() {
        double satisfationTotal = statistics.stream().map(MealServiceStatistics::guestSatisfaction).reduce(0, Integer::sum);
        return satisfationTotal / statistics.size();
    }

    public double getCostWasteRatio() {
        double WasteTotal = statistics.stream().map(MealServiceStatistics::costOfWaste).reduce(0, Integer::sum);
        double CostTotalAll = statistics.stream().map(MealServiceStatistics::costTotal).reduce(0, Integer::sum);
        return (WasteTotal/CostTotalAll) * 100;
    }
}
