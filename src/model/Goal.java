package model;

public class Goal {
    private String itemName;
    private double currentPrice;
    private int targetYear;
    private double inflationRate;

    public Goal(String itemName, double currentPrice, int targetYear, double inflationRate) {
        this.itemName = itemName;
        this.currentPrice = currentPrice;
        this.targetYear = targetYear;
        this.inflationRate = inflationRate;
    }

    public String getItemName() {
        return itemName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public double getInflationRate() {
        return inflationRate;
    }

    public int getYearsLeft() {
        int currentYear = 2026;
        return targetYear - currentYear;
    }

    @Override
    public String toString() {
        return itemName + " - Target Year: " + targetYear + ", Current Price: " + currentPrice;
    }
}