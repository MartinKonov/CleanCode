package MyFitnessPal.Tests.modelTests;

import MyFitnessPal.models.WaterEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WaterEntryTest {

    private WaterEntry entry;

    @BeforeEach
    void setUp() {
        entry = new WaterEntry(LocalDate.of(2025, 3, 19), 500);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDate() {
        // Проверка дали getDate() връща правилната дата
        assertEquals(LocalDate.of(2025, 3, 19), entry.getDate(), "Date of entry is not correct");
    }

    @Test
    void getAmount() {
        // Проверка дали getAmount() връща правилното количество
        assertEquals(500, entry.getAmount(), "Quantity of entry is not correct");
    }

    @Test
    void testToString() {
        // Проверка на метода toString(), който трябва да връща датата и количеството в правилен формат
        assertEquals("2025-03-19: 500ml", entry.toString(), "ToString() method is not correct");
    }
}
