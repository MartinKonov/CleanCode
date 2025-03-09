package MyFitnessPal.models;

public class Food {
    private String name;
    private String description;
    private int servingSize;
    private int servingsPerContainer;
    private int calories;
    private double carbs;
    private double fat;
    private double protein;

    public Food(String name, String description, int servingSize, int servingsPerContainer,
                int calories, double carbs, double fat, double protein) {
        this.name = name;
        this.description = description;
        this.servingSize = servingSize;
        this.servingsPerContainer = servingsPerContainer;
        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getServingSize() {
        return servingSize;
    }

    public int getServingsPerContainer() {
        return servingsPerContainer;
    }

    public int getCalories() {
        return calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }

    public double getProtein() {
        return protein;
    }

    @Override
    public String toString() {
        return String.format("%s (%dg; %d kcal; %.1fg, %.1fg, %.2fg)",
                name, servingSize, calories, carbs, fat, protein);
    }
}
