package com.codecool.ehotel.service.successMetrics;

public class SuccessMetrics {
    int unhappyGuests;
    int costOfWastedFood;
    public SuccessMetrics(int unhappyGuests, int costOfWastedFood){
        this.unhappyGuests = unhappyGuests;
        this.costOfWastedFood = costOfWastedFood;
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
}
