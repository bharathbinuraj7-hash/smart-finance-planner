package logic;

public class SavingsPlanner {
    private double monthlyIncome;
    private double savingsPercent;
    private String termType;

    public SavingsPlanner(double monthlyIncome, double savingsPercent, String termType) {
        this.monthlyIncome = monthlyIncome;
        this.savingsPercent = savingsPercent;
        this.termType = termType;
    }

    public double monthlySaving(double futureValue, int years) {
        int months = years * 12;
        if (months <= 0) {
            months = 1;
        }
        return futureValue / months;
    }

    public double yearlySaving(double futureValue, int years) {
        if (years <= 0) {
            years = 1;
        }
        return futureValue / years;
    }

    public double percentSaving() {
        return monthlyIncome * (savingsPercent / 100);
    }

    public boolean isAffordable(double requiredMonthlySaving) {
        return percentSaving() >= requiredMonthlySaving;
    }

    public String getTermType() {
        return termType;
    }
}