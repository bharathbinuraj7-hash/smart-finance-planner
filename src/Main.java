import data.DataStore;
import java.util.List;
import model.Goal;

public class Main {
    public static void main(String[] args) {
        DataStore store = new DataStore("goals.txt");

        Goal myGoal = new Goal("Laptop", 60000, 2028, 6.0);
        store.saveGoal(myGoal);

        System.out.println("Saved! Now loading back from file:");
        List<Goal> allGoals = store.loadGoals();
        for (Goal g : allGoals) {
            System.out.println(" - " + g);
        }
    }
}