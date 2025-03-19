package MyFitnessPal.Tests;

import MyFitnessPal.IO;
import MyFitnessPal.models.WaterEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {

    private IO io;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        io = new IO();

        System.setOut(new PrintStream(outContent));

    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        io = new IO();
    }

    @Test
    void intInputWithPrompt() {
        provideInput("42\n");
        assertEquals(42, io.intInputWithPrompt("Enter a number: "));
        assertTrue(outContent.toString().contains("Enter a number:"));
    }

    @Test
    void intInput() {
        provideInput("55\n");
        assertEquals(55, io.intInput());
    }

    @Test
    void stringInputWithPrompt() {
        provideInput("Hello World\n");
        assertEquals("Hello World", io.stringInputWithPrompt("Say something: "));
        assertTrue(outContent.toString().contains("Say something:"));
    }

    @Test
    void stringInput() {
        provideInput("Testing input\n");
        assertEquals("Testing input", io.stringInput());
    }


    @Test
    void inputDate() {
        provideInput("2023-05-12\n");
        assertEquals(LocalDate.of(2023, 5, 12), io.inputDate("Enter date:"));
        assertTrue(outContent.toString().contains("Enter date:"));
    }

}
