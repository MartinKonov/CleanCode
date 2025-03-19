package MyFitnessPal.Commands;

import MyFitnessPal.FoodManager;
import MyFitnessPal.IO;
import MyFitnessPal.models.Food;
import MyFitnessPal.FoodManager;
import java.util.List;
import java.util.Scanner;

public class ViewAllFoodsCommand implements Command{

    private IO IO;
    private FoodManager foodManager;

    public ViewAllFoodsCommand(IO io, FoodManager foodManager)
    {
        this.IO = io;
        this.foodManager = foodManager;
    }

    @Override
    public String getName()
    {
        return "View All Foods";
    }

    @Override
    public void execute() {
        List<Food> foods = foodManager.getFoods();
        if (foods.isEmpty())
        {
            IO.outputPrompt("No foods available.");
        }
        else
        {
            int index = 1;
            for (Food food : foods) {
                IO.outputPrompt(index + ". " + food);
                index++;
            }
        }
    }
}
