package MyFitnessPal.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    private final List<Command> commands = new ArrayList<>();

    public void register(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }
}
