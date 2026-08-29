package logic;

public class InflationCalculator {
    private double rate;

    public InflationCalculator(double rate) {
        this.rate = rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public double futureValue(double price, double ratePercent, int years) {
        return price * Math.pow(1 + (ratePercent / 100), years);
    }
}