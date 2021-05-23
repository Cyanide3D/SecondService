package com.example.SecondService.model;

public class Report {

    private int cranesQuantity;
    private int amountOfShips;
    private int avgAwaitTime;
    private int maxUnloadTime;
    private int avgUnloadTime;
    private int fine;
    private int minNeedfulCraneAmount;


    public int getCranesQuantity() {
        return cranesQuantity;
    }

    public void setCranesQuantity(int cranesQuantity) {
        this.cranesQuantity = cranesQuantity;
    }

    public int getAmountOfShips() {
        return amountOfShips;
    }

    public void setAmountOfShips(int amountOfShips) {
        this.amountOfShips = amountOfShips;
    }

    public int getAvgAwaitTime() {
        return avgAwaitTime;
    }

    public void setAvgAwaitTime(int avgAwaitTime) {
        this.avgAwaitTime = avgAwaitTime;
    }

    public int getMaxUnloadTime() {
        return maxUnloadTime;
    }

    public void setMaxUnloadTime(int maxUnloadTime) {
        this.maxUnloadTime = maxUnloadTime;
    }

    public int getAvgUnloadTime() {
        return avgUnloadTime;
    }

    public void setAvgUnloadTime(int avgUnloadTime) {
        this.avgUnloadTime = avgUnloadTime;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getMinNeedfulCraneAmount() {
        return minNeedfulCraneAmount;
    }

    public void setMinNeedfulCraneAmount(int minNeedfulCraneAmount) {
        this.minNeedfulCraneAmount = minNeedfulCraneAmount;
    }
}
