package MyFitnessPal.models;

import java.time.LocalDate;

public class LoggedFood {
    private LocalDate date;
    private String meal; // например "Breakfast", "Lunch", "Snacks", "Dinner"
    private Food food;
    private double servings;

    public LoggedFood(LocalDate date, String meal, Food food, double servings) {
        this.date = date;
        this.meal = meal;
        this.food = food;
        this.servings = servings;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMeal() {
        return meal;
    }

    public Food getFood() {
        return food;
    }

    public double getServings() {
        return servings;
    }

    public int getTotalGrams() {
        return (int) Math.round(food.getServingSize() * servings);
    }

    public int getTotalCalories() {
        return (int) Math.round(food.getCalories() * servings);
    }

    public double getTotalCarbs() {
        return food.getCarbs() * servings;
    }

    public double getTotalFat() {
        return food.getFat() * servings;
    }

    public double getTotalProtein() {
        return food.getProtein() * servings;
    }

    @Override
    public String toString() {
        return String.format("%.2f X %s (Total: %dg; %d kcal; %.2fg, %.2fg, %.2fg)",
                servings,
                food.getName(),
                getTotalGrams(),
                getTotalCalories(),
                getTotalCarbs(),
                getTotalFat(),
                getTotalProtein());
    }
}

