package MyFitnessPal.Commands;
import  MyFitnessPal.WaterTracker;
import MyFitnessPal.IO;
import java.time.LocalDate;

public class AddWaterEntryCommand implements Command{

    private WaterTracker tracker;
    private IO IO;
    public AddWaterEntryCommand(IO io, WaterTracker tracker)
    {
        this.tracker = tracker;
        this.IO = io;
    }
    @Override
    public String getName()
    {
        return "Drink water";
    }

    @Override
    public void execute()
    {
        LocalDate date = IO.inputDate("When? (yyyy-MM-dd)");
        int amount = IO.intInputWithPrompt("How much? (ml)");

        tracker.addEntry(date, amount);
        IO.outputPrompt("OK");
    }
}
