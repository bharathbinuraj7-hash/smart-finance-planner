package gui;

import java.awt.*;
import javax.swing.*;
import logic.InflationCalculator;
import logic.SavingsPlanner;
import model.Goal;

public class InputPanel extends JPanel {
    private MainFrame mainFrame;

    private JTextField itemNameField;
    private JTextField priceField;
    private JTextField yearField;
    private JTextField inflationField;
    private JTextField incomeField;
    private JTextField savingsPercentField;
    private JComboBox<String> termBox;
    private JButton calculateButton;

    public InputPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(8, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        add(itemNameField);

        add(new JLabel("Current Price (Rs.):"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Target Year:"));
        yearField = new JTextField();
        add(yearField);

        add(new JLabel("Inflation Rate (%):"));
        inflationField = new JTextField();
        add(inflationField);

        add(new JLabel("Monthly Income (Rs.):"));
        incomeField = new JTextField();
        add(incomeField);

        add(new JLabel("Savings % of Income:"));
        savingsPercentField = new JTextField();
        add(savingsPercentField);

        add(new JLabel("Goal Term:"));
        termBox = new JComboBox<>(new String[]{"Short-term", "Long-term"});
        add(termBox);

        calculateButton = new JButton("Calculate");
        add(calculateButton);

        calculateButton.addActionListener(e -> calculate());
    }

    private void calculate() {
        try {
            Goal goal = new Goal(
                    itemNameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(yearField.getText()),
                    Double.parseDouble(inflationField.getText())
            );

            if (goal.getYearsLeft() <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Target Year must be a future calendar year (e.g. 2029).",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            InflationCalculator calc = new InflationCalculator(goal.getInflationRate());
            double futureCost = calc.futureValue(goal.getCurrentPrice(), goal.getInflationRate(), goal.getYearsLeft());

            SavingsPlanner planner = new SavingsPlanner(
                    Double.parseDouble(incomeField.getText()),
                    Double.parseDouble(savingsPercentField.getText()),
                    (String) termBox.getSelectedItem()
            );

            double monthlySaving = planner.monthlySaving(futureCost, goal.getYearsLeft());
            double possibleMonthly = planner.percentSaving();
            boolean affordable = planner.isAffordable(monthlySaving);

            mainFrame.showResultScreen(goal, futureCost, monthlySaving, possibleMonthly, affordable);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}