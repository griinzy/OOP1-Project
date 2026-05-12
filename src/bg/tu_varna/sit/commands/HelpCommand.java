package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;

import java.util.Map;

/**
 * Извежда информация за поддържаните команди.
 */
public class HelpCommand implements Command {
    private final Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Prints this information";
    }

    @Override
    public String execute(String[] args) {
        StringBuilder result = new StringBuilder("The following commands are supported:\n");
        for(Command cmd : commands.values()) {
            result.append(cmd.getName()).append("\t\t").append(cmd.getDescription()).append("\n");
        }
        return result.toString();
    }
}
