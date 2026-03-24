package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.CommandRunner;
import bg.tu_varna.sit.commands.interfaces.Command;

public class ExitCommand implements Command {
    private CommandRunner runner;

    public ExitCommand(CommandRunner runner) {
        this.runner = runner;
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Exits the program";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Exiting the program...");
        runner.stop();
    }
}
