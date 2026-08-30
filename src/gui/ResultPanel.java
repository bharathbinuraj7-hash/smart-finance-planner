package gui;

import model.Goal;
import data.DataStore;
import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private MainFrame mainFrame;
    private DataStore dataStore;
    private Goal currentGoal;

    private JLabel futureCostLabel;
    private JLabel monthlySavingLabel;
    private JLabel possibleSavingLabel;
    private JLabel affordableLabel;
    private JLabel summaryLabel;

    public ResultPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.dataStore = new DataStore("goals.txt");

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        futureCostLabel = new JLabel("FUTURE COST");
        futureCostLabel.setFont(new Font("Arial", Font.BOLD, 28));
        futureCostLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        monthlySavingLabel = new JLabel("MONTHLY SAVING NEEDED");
        monthlySavingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        monthlySavingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        possibleSavingLabel = new JLabel();
        possibleSavingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        possibleSavingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        affordableLabel = new JLabel();
        affordableLabel.setFont(new Font("Arial", Font.BOLD, 14));
        affordableLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        summaryLabel = new JLabel();
        summaryLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        summaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(futureCostLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(monthlySavingLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(possibleSavingLabel);
        centerPanel.add(affordableLabel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(summaryLabel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save This Goal");
        JButton backButton = new JButton("Back to Input");

        saveButton.addActionListener(e -> saveGoal());
        backButton.addActionListener(e -> mainFrame.showInputScreen());

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setResult(Goal goal, double futureCost, double monthlySaving, double possibleMonthly, boolean affordable) {
        this.currentGoal = goal;

        futureCostLabel.setText(String.format("FUTURE COST: Rs. %.2f", futureCost));
        monthlySavingLabel.setText(String.format("MONTHLY SAVING NEEDED: Rs. %.2f", monthlySaving));
        possibleSavingLabel.setText(String.format("You can save: Rs. %.2f / month", possibleMonthly));
        affordableLabel.setText(affordable ? "Affordable: Yes" : "Affordable: No");
        affordableLabel.setForeground(affordable ? new Color(0, 130, 0) : Color.RED);
        summaryLabel.setText(String.format("Based on %.1f%% inflation, over %d years",
                goal.getInflationRate(), goal.getYearsLeft()));
    }

    private void saveGoal() {
        dataStore.saveGoal(currentGoal);
        JOptionPane.showMessageDialog(this, "Goal saved!");
        mainFrame.showDashboardScreen();
    }
}