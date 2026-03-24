package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;

import java.util.Map;


public class HelpCommand implements Command {
    private Map<String, Command> commands;

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
    public void execute(String[] args) {
        System.out.println("The following commands are supported:");
        for(Command cmd : commands.values()) {
            System.out.println(cmd.getName() + "\t\t" + cmd.getDescription());
        }
    }
}
