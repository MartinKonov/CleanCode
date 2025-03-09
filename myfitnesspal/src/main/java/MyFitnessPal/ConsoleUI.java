package MyFitnessPal;

import MyFitnessPal.models.WaterEntry;
import MyFitnessPal.models.Food;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
public class ConsoleUI {
    private WaterTracker tracker;
    private Scanner scanner;
    private FoodManager foodManager;

    public ConsoleUI(WaterTracker tracker, FoodManager foodManager) {
        this.foodManager = foodManager;
        this.tracker = tracker;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printPrompts();
            int choice = intInputWithPrompt("");
            if(choice == 5)
                break;
            executeCommand(choice);
        }
    }

    private void printPrompts()
    {
        System.out.println("1. Drink water");
        System.out.println("2. Check water");
        System.out.println("3. Create Food");
        System.out.println("4. View All Foods");
        System.out.println("5. Exit");
    }

    private void executeCommand(int choice)
    {
        switch (choice) {
            case 1:
                addWaterEntry();
                break;
            case 2:
                checkWaterEntries();
                break;
            case 3:
                createFood();
                break;
            case 4:
                viewAllFoods();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void addWaterEntry() {
        LocalDate date = getWaterEntryDate();
        int amount = intInputWithPrompt("How much? (ml)");

        tracker.addEntry(date, amount);
        System.out.println("OK");
    }

    private LocalDate getWaterEntryDate()
    {
        System.out.println("When? (yyyy-MM-dd)");
        String dateStr = scanner.nextLine();
        return LocalDate.parse(dateStr);
    }

    private void checkWaterEntries() {
        LocalDate date = getWaterEntryDate();
        List<WaterEntry> entries = tracker.getEntriesForDate(date);
        printWaterEntries(entries,date);
    }

    private void printWaterEntries(List<WaterEntry> entries, LocalDate date)
    {
        if (entries.isEmpty()) {
            System.out.println("No entries for this date.");
        } else {
            System.out.println(date + ":");
            for (WaterEntry entry : entries) {
                System.out.println("  " + entry.getAmount() + "ml");
            }
        }
    }

    private void createFood()
    {
        String name = stringInputWithPrompt("Name:");
        String description = stringInputWithPrompt("Description(optional):");
        int servingSize = intInputWithPrompt("Serving Size (g):");
        int servingsPerContainer = intInputWithPrompt("Servings per container:");
        int calories =  intInputWithPrompt("Calories (kcal):");
        double carbs = doubleInputWithPrompt("Carbs (g):");
        double fat = doubleInputWithPrompt("Fat (g):");
        double protein = doubleInputWithPrompt("Protein (g):");

        Food food = new Food(name, description, servingSize, servingsPerContainer, calories, carbs, fat, protein);
        foodManager.addAndSaveFood(food);
        System.out.println("Food created successfully!");
    }

    private String stringInputWithPrompt(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private int intInputWithPrompt(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    private double doubleInputWithPrompt(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextDouble();
    }

    private void viewAllFoods() {
        List<Food> foods = foodManager.getFoods();
        if (foods.isEmpty()) {
            System.out.println("No foods available.");
        } else {
            int index = 1;
            for (Food food : foods) {
                System.out.println(index + ". " + food);
                index++;
            }
        }
    }

}
