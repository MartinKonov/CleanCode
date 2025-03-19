package MyFitnessPal.Tests;

import MyFitnessPal.FoodLogManager;
import MyFitnessPal.models.Food;
import MyFitnessPal.models.LoggedFood;
import MyFitnessPal.storage.FoodLogStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodLogManagerTest {

    private FoodLogManager foodLogManager;
    private FoodLogStorage storage;
    private static final String FILE_NAME = "food_log.txt"; // Ensure this matches FoodLogStorage
    private LoggedFood log1, log2, log3;
    private LocalDate today;

    @BeforeEach
    void setUp() {
        storage = new FoodLogStorage();
        foodLogManager = new FoodLogManager(storage);

        today = LocalDate.now();
        Food food1 = new Food("Apple", "Fresh apple", 100, 1, 52, 14, 0.2, 0.3);
        Food food2 = new Food("Banana", "Yellow banana", 120, 1, 89, 23, 0.3, 1.1);

        log1 = new LoggedFood(today, "Breakfast", food1, 1);
        log2 = new LoggedFood(today, "Lunch", food2, 1.5);
        log3 = new LoggedFood(today.minusDays(1), "Dinner", food1, 2);
    }

    @Test
    void logFood() {
        foodLogManager.logFood(log1);
        foodLogManager.logFood(log2);

        List<LoggedFood> logs = foodLogManager.getFoodLogsByDate(today);
        assertEquals(2, logs.size());
        assertEquals("Apple", logs.get(0).getFood().getName());
        assertEquals("Banana", logs.get(1).getFood().getName());
    }

    @Test
    void getFoodLogsByDate() {
        foodLogManager.logFood(log1);
        foodLogManager.logFood(log2);
        foodLogManager.logFood(log3);

        List<LoggedFood> todayLogs = foodLogManager.getFoodLogsByDate(today);
        List<LoggedFood> yesterdayLogs = foodLogManager.getFoodLogsByDate(today.minusDays(1));

        assertEquals(2, todayLogs.size());
        assertEquals(1, yesterdayLogs.size());
        assertEquals("Apple", yesterdayLogs.get(0).getFood().getName());
    }

    @AfterEach
    void tearDown() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
