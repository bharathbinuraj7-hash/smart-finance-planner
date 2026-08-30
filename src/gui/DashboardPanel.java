package gui;

import model.Goal;
import data.DataStore;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JPanel {
    private MainFrame mainFrame;
    private DataStore dataStore;
    private JPanel goalsListPanel;

    public DashboardPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.dataStore = new DataStore("goals.txt");

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("My Goals", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        goalsListPanel = new JPanel();
        goalsListPanel.setLayout(new BoxLayout(goalsListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(goalsListPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("+ Add New Goal");
        addButton.addActionListener(e -> mainFrame.showInputScreen());
        add(addButton, BorderLayout.SOUTH);
    }

    public void refreshGoals() {
        goalsListPanel.removeAll();
        List<Goal> goals = dataStore.loadGoals();

        if (goals.isEmpty()) {
            goalsListPanel.add(new JLabel("No saved goals yet."));
        } else {
            for (Goal g : goals) {
                JLabel goalLabel = new JLabel(String.format("%s - %d - Rs. %.2f",
                        g.getItemName(), g.getTargetYear(), g.getCurrentPrice()));
                goalLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                goalsListPanel.add(goalLabel);
            }
        }

        goalsListPanel.revalidate();
        goalsListPanel.repaint();
    }
}