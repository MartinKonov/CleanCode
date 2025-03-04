package MyFitnessPal;

import MyFitnessPal.models.WaterEntry;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILE_NAME = "water_data.txt";

    public void saveEntries(List<WaterEntry> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (WaterEntry entry : entries) {
                writer.write(entry.getDate() + "," + entry.getAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<WaterEntry> loadEntries() {
        List<WaterEntry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                LocalDate date = LocalDate.parse(parts[0]);
                int amount = Integer.parseInt(parts[1]);
                entries.add(new WaterEntry(date, amount));
            }
        } catch (IOException e) {

        }
        return entries;
    }

    public void deleteDataFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
