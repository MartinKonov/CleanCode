package MyFitnessPal.Commands;

import MyFitnessPal.models.Food;
import MyFitnessPal.FoodManager;
import MyFitnessPal.IO;

public class CreateFoodCommand implements Command{

    private IO IO;
    private FoodManager foodManager;

    public CreateFoodCommand(IO io, FoodManager foodManager)
    {
        this.IO = io;
        this.foodManager = foodManager;
    }
    @Override
    public String getName() {
        return "Create Food";
    }

    @Override
    public void execute() {
        String name = IO.stringInputWithPrompt("Name:");
        String description = IO.stringInputWithPrompt("Description(optional):");
        int servingSize = IO.intInputWithPrompt("Serving Size (g):");
        int servingsPerContainer = IO.intInputWithPrompt("Servings per container:");
        int calories =  IO.intInputWithPrompt("Calories (kcal):");
        double carbs = IO.doubleInputWithPrompt("Carbs (g):");
        double fat = IO.doubleInputWithPrompt("Fat (g):");
        double protein = IO.doubleInputWithPrompt("Protein (g):");

        Food food = new Food(name, description, servingSize, servingsPerContainer, calories, carbs, fat, protein);
        foodManager.addAndSaveFood(food);
        IO.outputPrompt("Food created successfully!");
    }
}
