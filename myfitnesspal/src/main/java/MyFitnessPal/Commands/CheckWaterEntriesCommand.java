package MyFitnessPal.Commands;


import java.time.LocalDate;
import java.util.List;
import MyFitnessPal.IO;
import MyFitnessPal.WaterTracker;
import MyFitnessPal.models.WaterEntry;

public class CheckWaterEntriesCommand implements Command{

    private WaterTracker tracker;
    private IO IO;

    public CheckWaterEntriesCommand(IO io, WaterTracker tracker)
    {
        this.tracker = tracker;
        this.IO = io;
    }

    @Override
    public String getName() {
        return "Check water";
    }

    @Override
    public void execute() {
        LocalDate date = IO.inputDate("When? (yyyy-MM-dd)");
        List<WaterEntry> entries = tracker.getEntriesForDate(date);
        IO.printWaterEntries(entries, date);
    }
}
