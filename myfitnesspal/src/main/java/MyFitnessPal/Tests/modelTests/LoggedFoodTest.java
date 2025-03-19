package MyFitnessPal.Tests.modelTests;

import MyFitnessPal.models.Food;
import MyFitnessPal.models.LoggedFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoggedFoodTest {

    private LoggedFood loggedFood;
    private Food food;

    @BeforeEach
    void setUp() {
        food = new Food("Apple", "Red apple", 100, 1, 52, 14.0, 0.2, 0.3);

        loggedFood = new LoggedFood(LocalDate.of(2025, 3, 19), "Breakfast", food, 2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2025, 3, 19), loggedFood.getDate(), "Date is not correct");
    }

    @Test
    void getMeal() {
        assertEquals("Breakfast", loggedFood.getMeal(), "meal is not correct");
    }

    @Test
    void getFood() {
        assertEquals(food, loggedFood.getFood(), "Food is not correct");
    }

    @Test
    void getServings() {
        assertEquals(2, loggedFood.getServings(), "Servings are not correct");
    }

    @Test
    void getTotalGrams() {
        assertEquals(200, loggedFood.getTotalGrams(), "Quantity of grams is not correct");
    }

    @Test
    void getTotalCalories() {
        assertEquals(104, loggedFood.getTotalCalories(), "Calories are not correct");
    }

    @Test
    void getTotalCarbs() {
        assertEquals(28.0, loggedFood.getTotalCarbs(), "Carbs are not correct");
    }

    @Test
    void getTotalFat() {
        assertEquals(0.4, loggedFood.getTotalFat(), "Fats are not correct");
    }

    @Test
    void getTotalProtein() {
        assertEquals(0.6, loggedFood.getTotalProtein(), "proteins are not correct");
    }

    @Test
    void testToString() {
        String expected = "2,00 X Apple (Total: 200g; 104 kcal; 28,00g, 0,40g, 0,60g)";
        assertEquals(expected, loggedFood.toString(), "ToString() method is not correct");
    }
}
