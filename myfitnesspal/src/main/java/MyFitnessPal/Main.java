package MyFitnessPal;

import MyFitnessPal.models.Food;
import MyFitnessPal.storage.WaterStorage;
import MyFitnessPal.storage.FoodStorage;
public class Main {

    public static void main(String[] args) {
        WaterStorage Wstorage = new WaterStorage();
        WaterTracker Wtracker = new WaterTracker(Wstorage);
        FoodStorage Fstorage = new FoodStorage();
        FoodManager Fmanager = new FoodManager(Fstorage);
        ConsoleUI ui = new ConsoleUI(Wtracker, Fmanager);

        ui.start();
    }

}
