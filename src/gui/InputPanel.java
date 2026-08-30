package gui;

import java.awt.*;
import javax.swing.*;

public class InputPanel extends JPanel {
    private JTextField itemNameField;
    private JTextField priceField;
    private JTextField yearField;
    private JTextField inflationField;
    private JTextField incomeField;
    private JTextField savingsPercentField;
    private JComboBox<String> termBox;
    private JButton calculateButton;

    public InputPanel() {
        setLayout(new GridLayout(8, 2, 10, 10));

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
    }

    public String getItemName() {
        return itemNameField.getText();
    }

    public double getPrice() {
        return Double.parseDouble(priceField.getText());
    }

    public int getTargetYear() {
        return Integer.parseInt(yearField.getText());
    }

    public double getInflationRate() {
        return Double.parseDouble(inflationField.getText());
    }

    public double getIncome() {
        return Double.parseDouble(incomeField.getText());
    }

    public double getSavingsPercent() {
        return Double.parseDouble(savingsPercentField.getText());
    }

    public String getTermType() {
        return (String) termBox.getSelectedItem();
    }

    public JButton getCalculateButton() {
        return calculateButton;
    }
}