package gui;

import model.Goal;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private InputPanel inputPanel;
    private ResultPanel resultPanel;
    private DashboardPanel dashboardPanel;

    public MainFrame() {
        setTitle("Smart Finance Planner");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        inputPanel = new InputPanel(this);
        resultPanel = new ResultPanel(this);
        dashboardPanel = new DashboardPanel(this);

        cardPanel.add(inputPanel, "input");
        cardPanel.add(resultPanel, "result");
        cardPanel.add(dashboardPanel, "dashboard");

        add(cardPanel);
        cardLayout.show(cardPanel, "input");
    }

    public void showInputScreen() {
        cardLayout.show(cardPanel, "input");
    }

    public void showResultScreen(Goal goal, double futureCost, double monthlySaving, double possibleMonthly, boolean affordable) {
        resultPanel.setResult(goal, futureCost, monthlySaving, possibleMonthly, affordable);
        cardLayout.show(cardPanel, "result");
    }

    public void showDashboardScreen() {
        dashboardPanel.refreshGoals();
        cardLayout.show(cardPanel, "dashboard");
    }
}