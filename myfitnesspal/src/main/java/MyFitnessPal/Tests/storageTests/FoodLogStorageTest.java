package MyFitnessPal.Tests.storageTests;

import MyFitnessPal.models.LoggedFood;
import MyFitnessPal.models.Food;
import MyFitnessPal.storage.FoodLogStorage;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodLogStorageTest {
    private FoodLogStorage storage;

    @BeforeEach
    void setUp() {
        storage = new FoodLogStorage();
    }

    @AfterEach
    void tearDown() {
        storage.deleteDataFile();
    }

    @Test
    void saveFoodLogs() {
        List<LoggedFood> logs = new ArrayList<>();
        logs.add(new LoggedFood(LocalDate.of(2025, 3, 18), "Lunch",
                new Food("Apple", "Fruit", 100, 1, 52, 14, 0.2, 0.3), 1));

        storage.saveFoodLogs(logs);

        File file = new File("food_log.txt");
        assertTrue(file.exists(), "File must be created");
        assertTrue(file.length() > 0, "File must not be empty");
    }

    @Test
    void loadFoodLogs() {
        List<LoggedFood> logs = new ArrayList<>();
        logs.add(new LoggedFood(LocalDate.of(2025, 3, 18), "Dinner",
                new Food("Banana", "Fruit", 120, 1, 105, 27, 0.3, 1.3), 1.5));

        storage.saveFoodLogs(logs);
        List<LoggedFood> loadedLogs = storage.loadFoodLogs();

        assertEquals(1, loadedLogs.size(), "Only one log should be loaded");
        assertEquals("Banana", loadedLogs.get(0).getFood().getName(), "Name of loaded food should be the same");
        assertEquals(1, loadedLogs.get(0).getServings(), "Servings should be the same");
    }

    @Test
    void deleteDataFile() {
        storage.saveFoodLogs(new ArrayList<>());
        File file = new File("food_log.txt");
        assertTrue(file.exists(), "File must exist before deletion");

        storage.deleteDataFile();
        assertFalse(file.exists(), "File must be deleted");
    }
}
