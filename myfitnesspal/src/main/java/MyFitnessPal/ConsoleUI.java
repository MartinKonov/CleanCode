package MyFitnessPal;

import MyFitnessPal.models.WaterEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
public class ConsoleUI {
    private WaterTracker tracker;
    private Scanner scanner;

    public ConsoleUI(WaterTracker tracker) {
        this.tracker = tracker;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1. Drink water");
            System.out.println("2. Check water");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addWaterEntry();
            } else if (choice == 2) {
                checkWaterEntries();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private void addWaterEntry() {
        System.out.println("When? (yyyy-MM-dd)");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        System.out.println("How much? (ml)");
        int amount = scanner.nextInt();
        scanner.nextLine();

        tracker.addEntry(date, amount);
        System.out.println("OK");
    }

    private void checkWaterEntries() {
        System.out.println("When? (yyyy-MM-dd)");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        List<WaterEntry> entries = tracker.getEntriesForDate(date);
        if (entries.isEmpty()) {
            System.out.println("No entries for this date.");
        } else {
            System.out.println(date + ":");
            for (WaterEntry entry : entries) {
                System.out.println("  " + entry.getAmount() + "ml");
            }
        }
    }
}
