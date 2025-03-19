package MyFitnessPal.Tests;

import MyFitnessPal.FoodManager;
import MyFitnessPal.models.Food;

import java.io.File;
import java.util.List;

import MyFitnessPal.storage.FoodStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodManagerTest {

    Food food_1, food_2, food_3;
    FoodManager foodManager;
    private static final String FILE_NAME = "food_data.txt";
    FoodStorage storage;
    @BeforeEach
    void setUp() {
        food_1 = new Food("Carrot", "yellow", 1, 10, 10, 4, 2, 4);
        food_2 = new Food("Shkembe", "luto", 1, 1, 600, 25.2, 100, 50.55);
        food_3 = new Food("musaka", "mmmmmm", 1, 1, 500, 4.2, 24.12, 4.11);
        storage = new FoodStorage();
        foodManager = new FoodManager(storage);
    }

    @AfterEach
    void tearDown() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void addAndSaveFood() {
        foodManager.addAndSaveFood(food_1);
        foodManager.addAndSaveFood(food_2);

        List<Food> foods = foodManager.getFoods();

        assertEquals(2, foods.size());
        assertEquals("Carrot", foods.get(0).getName());
        assertEquals("Shkembe", foods.get(1).getName());
    }

    @Test
    void getFoods() {
        foodManager.addAndSaveFood(food_1);
        foodManager.addAndSaveFood(food_2);
        foodManager.addAndSaveFood(food_3);

        List<Food> foods = foodManager.getFoods();

        assertEquals(3, foods.size());
        assertEquals("Carrot", foods.get(0).getName());
        assertEquals("Shkembe", foods.get(1).getName());
        assertEquals("musaka", foods.get(2).getName());
    }
}