package MyFitnessPal.storage;

import MyFitnessPal.models.Food;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FoodStorage {
    private static final String FILE_NAME = "food_data.txt";

    public void saveFoods(List<Food> foods)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Food food : foods) {
                writer.write(String.format("%s,%s,%d,%d,%d,%.1f,%.1f,%.2f",
                        food.getName(),
                        food.getName().contains(",") ? "\"" + food.getName() + "\"" : food.getName(),
                        food.getServingSize(),
                        food.getServingsPerContainer(),
                        food.getCalories(),
                        food.getCarbs(),
                        food.getFat(),
                        food.getProtein()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Food> loadFoods() {
        List<Food> foods = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return foods;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 8) continue;
                String name = parts[0];
                String description = parts[1];
                int servingSize = Integer.parseInt(parts[2]);
                int servingsPerContainer = Integer.parseInt(parts[3]);
                int calories = Integer.parseInt(parts[4]);
                double carbs = Double.parseDouble(parts[5]);
                double fat = Double.parseDouble(parts[6]);
                double protein = Double.parseDouble(parts[7]);
                foods.add(new Food(name, description, servingSize, servingsPerContainer, calories, carbs, fat, protein));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public void deleteDataFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

}
