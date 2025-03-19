package MyFitnessPal.Tests.modelTests;

import MyFitnessPal.models.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    private Food food;

    @BeforeEach
    void setUp() {
        // Създаване на инстанция на Food
        food = new Food("Apple", "Red apple", 100, 1, 52, 14.0, 0.2, 0.3);
    }

    @AfterEach
    void tearDown() {
        // Няма нужда от изчистване в случая
    }

    @Test
    void getName() {
        assertEquals("Apple", food.getName(), "Name of food is not correct");
    }

    @Test
    void getDescription() {
        assertEquals("Red apple", food.getDescription(), "Description of food is not correct");
    }

    @Test
    void getServingSize() {
        assertEquals(100, food.getServingSize(), "Size of serving is not correct");
    }

    @Test
    void getServingsPerContainer() {
        assertEquals(1, food.getServingsPerContainer(), "Portions per container is not correct");
    }

    @Test
    void getCalories() {
        assertEquals(52, food.getCalories(), "Calories are not correct");
    }

    @Test
    void getCarbs() {
        assertEquals(14.0, food.getCarbs(), "Carbs are not correct");
    }

    @Test
    void getFat() {
        assertEquals(0.2, food.getFat(), "Fats are not correct");
    }

    @Test
    void getProtein() {
        assertEquals(0.3, food.getProtein(), "Proteins are not correct");
    }

    @Test
    void testToString() {
        // Проверява формата на string представянето на храната
        String expected = "Apple (100g; 52 kcal; 14,0g, 0,2g, 0,30g)";
        assertEquals(expected, food.toString(), "ToString() method is not correct");
    }
}
