package MyFitnessPal;

import MyFitnessPal.models.WaterEntry;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
public class IO {

    Scanner scanner;

    public IO()
    {
        this.scanner = new Scanner(System.in);
    }

    public int intInputWithPrompt(String prompt)
    {
        outputPrompt(prompt);
        return scanner.nextInt();
    }

    public int intInput()
    {
        return scanner.nextInt();
    }

    public String stringInputWithPrompt(String prompt)
    {
        outputPrompt(prompt);
        return scanner.nextLine();
    }

    public String stringInput()
    {
        return scanner.nextLine();
    }

    public double doubleInputWithPrompt(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextDouble();
    }

    public LocalDate inputDate(String prompt)
    {
        outputPrompt(prompt);
        String dateStr = scanner.nextLine();
        return LocalDate.parse(dateStr);
    }

    public void outputPrompt(String prompt)
    {
        System.out.println(prompt);
    }

    public void printWaterEntries(List<WaterEntry> entries, LocalDate date)
    {
        if (entries.isEmpty()) {
            outputPrompt("No entries for this date.");
        } else {
            outputPrompt(date + ":");
            for (WaterEntry entry : entries) {
                outputPrompt("  " + entry.getAmount() + "ml");
            }
        }
    }

}
