package MyFitnessPal;

import MyFitnessPal.storage.WaterStorage;
import MyFitnessPal.storage.FoodStorage;
import MyFitnessPal.Commands.CommandRegistry;
import MyFitnessPal.Commands.CreateFoodCommand;
import MyFitnessPal.Commands.AddWaterEntryCommand;
import MyFitnessPal.Commands.CheckWaterEntriesCommand;
import MyFitnessPal.Commands.ViewAllFoodsCommand;


public class Main {

    public static void main(String[] args) {

        WaterStorage Wstorage = new WaterStorage();
        WaterTracker Wtracker = new WaterTracker(Wstorage);

        FoodStorage Fstorage = new FoodStorage();
        FoodManager Fmanager = new FoodManager(Fstorage);

        IO IO = new IO();

        CommandRegistry registry = new CommandRegistry();
        registry.register(new AddWaterEntryCommand(IO, Wtracker));
        registry.register(new CheckWaterEntriesCommand(IO, Wtracker));
        registry.register(new CreateFoodCommand(IO, Fmanager));
        registry.register(new ViewAllFoodsCommand(IO, Fmanager));

        ConsoleUI ui = new ConsoleUI(IO, registry);

        ui.start();
    }

}
