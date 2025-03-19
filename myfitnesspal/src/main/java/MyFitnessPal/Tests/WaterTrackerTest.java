package MyFitnessPal.Tests;

import MyFitnessPal.WaterTracker;
import MyFitnessPal.models.WaterEntry;
import MyFitnessPal.storage.WaterStorage;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WaterTrackerTest {

    @org.junit.jupiter.api.Test
    void addEntry() {
        WaterStorage storage = new WaterStorage();
        WaterTracker tracker = new WaterTracker(storage);
        tracker.addEntry(LocalDate.parse("2025-03-05"), 500);
        List<WaterEntry> entriesForTestDate = tracker.getEntriesForDate(LocalDate.parse("2025-03-05"));

        storage.deleteDataFile();

        assertNotNull(entriesForTestDate, "Entries should not be null");
        assertEquals(1,entriesForTestDate.size(), "There should be one entry");
    }

    @org.junit.jupiter.api.Test
    void getEntriesForDate() {
        WaterStorage storage = new WaterStorage();
        WaterTracker tracker = new WaterTracker(storage);

        tracker.addEntry(LocalDate.parse("2024-03-04"), 250);
        tracker.addEntry(LocalDate.parse("2024-03-04"), 500);
        tracker.addEntry(LocalDate.parse("2024-03-06"), 300);

        List<WaterEntry> entriesForTestDate = tracker.getEntriesForDate(LocalDate.parse("2024-03-04"));

        storage.deleteDataFile();

        assertEquals(2, entriesForTestDate.size());

    }
}