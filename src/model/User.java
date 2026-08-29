package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String userId;
    private List<Goal> goals;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.goals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public void viewGoals() {
        if (goals.isEmpty()) {
            System.out.println(name + " has no goals yet.");
            return;
        }
        System.out.println(name + "'s goals:");
        for (Goal g : goals) {
            System.out.println(" - " + g);
        }
    }
}