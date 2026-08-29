package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Goal;

public class DataStore {
    private String filePath;

    public DataStore(String filePath) {
        this.filePath = filePath;
    }

    public void saveGoal(Goal goal) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(goal.getItemName() + "," +
                    goal.getCurrentPrice() + "," +
                    goal.getTargetYear() + "," +
                    goal.getInflationRate() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error saving goal: " + e.getMessage());
        }
    }

    public List<Goal> loadGoals() {
        List<Goal> goals = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return goals;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String itemName = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int year = Integer.parseInt(parts[2]);
                    double rate = Double.parseDouble(parts[3]);
                    goals.add(new Goal(itemName, price, year, rate));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading goals: " + e.getMessage());
        }

        return goals;
    }
}