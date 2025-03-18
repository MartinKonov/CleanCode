package MyFitnessPal.Commands;

import MyFitnessPal.FoodLogManager;
import MyFitnessPal.IO;
import MyFitnessPal.models.LoggedFood;

import java.time.LocalDate;
import java.util.List;

public class ViewFoodsLoggedCommand implements Command{

    private final FoodLogManager foodLogManager;
    private final IO IO;

    public ViewFoodsLoggedCommand(IO IO, FoodLogManager foodLogManager)
    {
        this.foodLogManager = foodLogManager;
        this.IO = IO;
    }


    @Override
    public String getName() {
        return "View foods logged";
    }

    @Override
    public void execute() {

        LocalDate date = IO.inputDate("When(date) (yyyy-mm-dd):");

        List<LoggedFood> logs = foodLogManager.getFoodLogsByDate(date);

        if (logs.isEmpty()) {
            IO.outputPrompt("No foods logged for " + date);
        }
        else
        {
            printLogs(logs);
        }
    }

    private void printLogs(List<LoggedFood> logs)
    {
        for (LoggedFood log : logs) {
            IO.outputPrompt(log.toString());
        }
    }



}
