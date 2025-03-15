package MyFitnessPal;

import MyFitnessPal.Commands.Command;
import MyFitnessPal.Commands.CommandRegistry;
import java.util.List;


public class ConsoleUI {

    private IO IO;
    private CommandRegistry registry;
    private List<Command> commands;

    public ConsoleUI(IO io, CommandRegistry registry) {
        this.IO = io;
        this.registry = registry;
        this.commands = registry.getCommands();
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            showCommands();

            int choice = getChoice();

            if (choice == commands.size() + 1) {
                exit = true;
            }
            else {
                try {
                    executeCommand(choice);
                }
                catch (IllegalArgumentException e)
                {
                    IO.outputPrompt(e.getMessage());
                }
            }
        }
    }

    private void showCommands()
    {
        IO.outputPrompt("Select an option:");
        for (int i = 0; i < commands.size(); i++) {
            IO.outputPrompt((i + 1) + ". " + commands.get(i).getName());
        }
        IO.outputPrompt((commands.size() + 1) + ". Exit");
    }

    private int getChoice()
    {
        int choice = IO.intInput();
        IO.stringInput();
        return choice;
    }

    private void executeCommand( int choice)    {
        if (isValid(choice))
        {
            Command selectedCommand = commands.get(choice - 1);
            selectedCommand.execute();
        }
        else {
            throw new IllegalArgumentException("Invalid choice. Try again.");
        }
    }

    private boolean isValid(int choice)
    {
        return choice > 0 && choice <= commands.size();
    }
}
