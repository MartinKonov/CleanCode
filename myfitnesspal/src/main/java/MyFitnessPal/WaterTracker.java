package MyFitnessPal;

import MyFitnessPal.models.WaterEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class WaterTracker {
    private List<WaterEntry> entries;
    private Storage storage;

    public WaterTracker(Storage storage) {
        this.storage = storage;
        this.entries = storage.loadEntries();
    }

    public void addEntry(LocalDate date, int amount) {
        WaterEntry entry = new WaterEntry(date, amount);
        entries.add(entry);
        storage.saveEntries(entries);
    }

    public List<WaterEntry> getEntriesForDate(LocalDate date) {
        List<WaterEntry> result = new ArrayList<>();
        for (WaterEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                result.add(entry);
            }
        }
        return result;
    }
}
