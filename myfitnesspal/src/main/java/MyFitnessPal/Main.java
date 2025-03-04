package MyFitnessPal;

public class Main {

    public static void main(String[] args) {
        Storage storage = new Storage();
        WaterTracker tracker = new WaterTracker(storage);
        ConsoleUI ui = new ConsoleUI(tracker);

        ui.start();
    }

}
