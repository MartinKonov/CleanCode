package MyFitnessPal;

import MyFitnessPal.models.LoggedFood;
import MyFitnessPal.storage.FoodLogStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodLogManager {
    private List<LoggedFood> loggedFoods;
    private FoodLogStorage storage;

    public FoodLogManager(FoodLogStorage storage) {
        this.storage = storage;
        this.loggedFoods = storage.loadFoodLogs();
    }

    public void logFood(LoggedFood log) {
        loggedFoods.add(log);
        storage.saveFoodLogs(loggedFoods);
    }

    public List<LoggedFood> getFoodLogsByDate(LocalDate date) {
        return loggedFoods.stream()
                .filter(log -> log.getDate().equals(date))
                .collect(Collectors.toList());
    }
}