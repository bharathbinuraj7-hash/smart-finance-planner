package gui;

import javax.swing.*;
import logic.InflationCalculator;
import logic.SavingsPlanner;
import model.Goal;

public class MainFrame extends JFrame {
    private InputPanel inputPanel;

    public MainFrame() {
        setTitle("Smart Finance Planner");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputPanel = new InputPanel();
        add(inputPanel);

        inputPanel.getCalculateButton().addActionListener(e -> calculateAndShowResult());
    }

private void calculateAndShowResult() {
    try {
        Goal goal = new Goal(
                inputPanel.getItemName(),
                inputPanel.getPrice(),
                inputPanel.getTargetYear(),
                inputPanel.getInflationRate()
        );

        if (goal.getYearsLeft() <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Target Year must be a future calendar year (e.g. 2029), not a number of years.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        InflationCalculator calc = new InflationCalculator(goal.getInflationRate());
        double futureCost = calc.futureValue(goal.getCurrentPrice(), goal.getInflationRate(), goal.getYearsLeft());

        SavingsPlanner planner = new SavingsPlanner(inputPanel.getIncome(), inputPanel.getSavingsPercent(), inputPanel.getTermType());
        double monthlySaving = planner.monthlySaving(futureCost, goal.getYearsLeft());
        double possibleMonthly = planner.percentSaving();

        String message = String.format(
                "Future Cost: Rs. %.2f%nMonthly Saving Needed: Rs. %.2f%nYou Can Save: Rs. %.2f%nAffordable: %s",
                futureCost, monthlySaving, possibleMonthly, planner.isAffordable(monthlySaving) ? "Yes" : "No"
        );

        JOptionPane.showMessageDialog(this, message, "Result", JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid numbers in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
}