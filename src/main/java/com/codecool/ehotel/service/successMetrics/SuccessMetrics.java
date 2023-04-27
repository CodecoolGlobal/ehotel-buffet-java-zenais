package com.codecool.ehotel.service.successMetrics;

public class SuccessMetrics {
    int unhappyGuests;
    int costOfWastedFood;

    int totalGuests;
    public SuccessMetrics(int unhappyGuests, int costOfWastedFood, int totalGuests){
        this.unhappyGuests = unhappyGuests;
        this.costOfWastedFood = costOfWastedFood;
        this.totalGuests = totalGuests;
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
}
