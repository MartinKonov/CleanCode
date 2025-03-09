package MyFitnessPal;

import MyFitnessPal.models.Food;
import MyFitnessPal.storage.FoodStorage;
import java.util.List;

public class FoodManager {

    private List<Food> foods;
    private FoodStorage storage;

    public FoodManager(FoodStorage storage)
    {
        this.storage = storage;
        this.foods = storage.loadFoods();
    }

    public void addAndSaveFood(Food food)
    {
        foods.add(food);
        storage.saveFoods(foods);
    }

    public List<Food> getFoods()
    {
        return foods;
    }

}
