package MyFitnessPal.Tests.storageTests;

import MyFitnessPal.models.Food;
import MyFitnessPal.storage.FoodStorage;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodStorageTest {
    private FoodStorage storage;
    private static final String TEST_FILE = "food_data.txt";

    @BeforeEach
    void setUp() {
        storage = new FoodStorage();
    }

    @AfterEach
    void tearDown() {
        storage.deleteDataFile();
    }

    @Test
    void saveFoods() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Apple", "Fruit", 100, 1, 52, 14, 0.2, 0.3));

        storage.saveFoods(foods);

        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "File must be created");
        assertTrue(file.length() > 0, "File must not be empty");
    }

    @Test
    void loadFoods() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Banana", "Fruit", 120, 1, 105, 27, 0.3, 1.3));

        storage.saveFoods(foods);

        List<Food> loadedFoods = storage.loadFoods();

        assertEquals(1, loadedFoods.size(), "Must be only one food");
        assertEquals("Banana", loadedFoods.get(0).getName(), "Names must be the same");
        assertEquals(1, loadedFoods.get(0).getServingsPerContainer(), "Servings must be the same");
    }

    @Test
    void deleteDataFile() {
        storage.saveFoods(new ArrayList<>());
        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "File must exist before deletion");

        storage.deleteDataFile();
        assertFalse(file.exists(), "File must be deleted");
    }
}
