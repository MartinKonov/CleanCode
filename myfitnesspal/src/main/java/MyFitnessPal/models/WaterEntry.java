package MyFitnessPal.models;

import java.time.LocalDate;

public class WaterEntry {
    private LocalDate date;
    private int amount;

    public WaterEntry(LocalDate date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return date + ": " + amount + "ml";
    }

}
