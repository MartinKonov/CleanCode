package MyFitnessPal.Tests.storageTests;

import MyFitnessPal.models.WaterEntry;
import MyFitnessPal.storage.WaterStorage;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WaterStorageTest {
    private WaterStorage storage;
    private static final String TEST_FILE = "water_data.txt";

    @BeforeEach
    void setUp() {
        storage = new WaterStorage();
    }

    @AfterEach
    void tearDown() {
        storage.deleteDataFile();
    }

    @Test
    void saveEntries() {
        List<WaterEntry> entries = new ArrayList<>();
        entries.add(new WaterEntry(LocalDate.of(2025, 3, 19), 500));

        storage.saveEntries(entries);

        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "File must be created");
        assertTrue(file.length() > 0, "File must not be empty");
    }

    @Test
    void loadEntries() {
        List<WaterEntry> entries = new ArrayList<>();
        entries.add(new WaterEntry(LocalDate.of(2025, 3, 19), 500));

        storage.saveEntries(entries);

        List<WaterEntry> loadedEntries = storage.loadEntries();

        assertEquals(1, loadedEntries.size(), "Must be only one entry");
        assertEquals(LocalDate.of(2025, 3, 19), loadedEntries.get(0).getDate(), "Date must be the same");
        assertEquals(500, loadedEntries.get(0).getAmount(), "quantity must be the same");
    }

    @Test
    void deleteDataFile() {
        storage.saveEntries(new ArrayList<>());
        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "File must exist before deletion");

        storage.deleteDataFile();
        assertFalse(file.exists(), "File must be deleted");
    }
}
