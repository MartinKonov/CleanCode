package MyFitnessPal.storage;

import MyFitnessPal.models.LoggedFood;
import MyFitnessPal.models.Food;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodLogStorage {
    private static final String FILE_NAME = "food_log.txt";

    public void saveFoodLogs(List<LoggedFood> logs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (LoggedFood log : logs) {
                writer.write(String.format("%s,%s,%s,%.2f,%d,%d,%.2f,%.2f,%.2f",
                        log.getDate().toString(),
                        log.getMeal(),
                        log.getFood().getName(),
                        log.getServings(),
                        log.getTotalGrams(),
                        log.getTotalCalories(),
                        log.getTotalCarbs(),
                        log.getTotalFat(),
                        log.getTotalProtein()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<LoggedFood> loadFoodLogs() {
        List<LoggedFood> logs = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return logs;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != 9) { // Adjusted to match the correct format
                    throw new NumberFormatException("Invalid log entry format.");
                }

                Food food = constructFood(parts);
                LoggedFood loggedFood = constructLoggedFood(parts, food);
                logs.add(loggedFood);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return logs;
    }

    private Food constructFood(String[] parts)
    {

        String foodName = parts[2].trim();
        double servings = Double.parseDouble(parts[3].trim());
        int totalGrams = Integer.parseInt(parts[4].trim());
        int totalCalories = Integer.parseInt(parts[5].trim());
        double totalCarbs = Double.parseDouble(parts[6].trim());
        double totalFat = Double.parseDouble(parts[7].trim());
        double totalProtein = Double.parseDouble(parts[8].trim());

        int servingSize = (int) Math.round(totalGrams / servings);
        int calories = (int) Math.round(totalCalories / servings);
        return new Food(foodName, "", servingSize
                , 1, calories, totalCarbs / servings
                , totalFat / servings , totalProtein / servings);
    }

    private LoggedFood constructLoggedFood(String[] parts, Food food)
    {
        LocalDate date = LocalDate.parse(parts[0].trim());
        String meal = parts[1].trim();
        double servings = Double.parseDouble(parts[3].trim());

        return new LoggedFood(date, meal, food, servings);

    }

    public void deleteDataFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
