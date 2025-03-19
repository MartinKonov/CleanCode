package MyFitnessPal.Commands;

import MyFitnessPal.FoodLogManager;
import MyFitnessPal.FoodManager;
import MyFitnessPal.models.Food;
import MyFitnessPal.models.LoggedFood;
import MyFitnessPal.IO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
public class LogFoodCommand implements Command{

    private final FoodLogManager foodLogManager;
    private List<Food> foods;

    private final IO IO;

    public LogFoodCommand(IO io,FoodLogManager foodLogManager, FoodManager foodManager) {
        this.foodLogManager = foodLogManager;
        this.IO = io;
        this.foods = foodManager.getFoods();
    }

    @Override
    public String getName() {
        return "Log Food";
    }

    @Override
    public void execute() {
        LocalDate date = IO.inputDate("When(date) (yyyy-MM-dd):");
        String meal;
        Food selectedFood;
        double servings;
        try{
            meal = selectMeal();
            selectedFood = selectFood();
            servings = getServings();
        }
        catch(IllegalArgumentException e)
        {
            IO.outputPrompt(e.getMessage());
            return;
        }

        LoggedFood log = new LoggedFood(date, meal, selectedFood, servings);

        IO.outputPrompt(log.toString());

        foodLogManager.logFood(log);
    }

    private String selectMeal()
    {
        String meal = IO.stringInputWithPrompt("When (meal):");

        if(meal.equals("breakfast") || meal.equals("lunch") || meal.equals("dinner") || meal.equals("snacks"))
        {
            return meal;
        }

        throw new IllegalArgumentException("Try again. Use breakfast, lunch, dinner or snacks");
    }

    private Food selectFood()
    {
        int foodId = getFoodID();
        return foods.get(foodId - 1);
    }
    private int getFoodID()
    {
        int foodId = IO.intInputWithPrompt("Which food (food id):");
        IO.stringInput();

        if(!isValidID(foodId))
            throw new IllegalArgumentException("Illegal food id. Try again!");

        return foodId;
    }

    private boolean isValidID(int ID)
    {
        return ID >= 0 && ID <= foods.size() - 1;
    }
    private double getServings()
    {
        double servings = IO.doubleInputWithPrompt("Number of serving(s):");
        IO.stringInput();

        if(servings < 0)
            throw new IllegalArgumentException("You can't eat negative servings. Try again!");

        return servings;
    }
}
