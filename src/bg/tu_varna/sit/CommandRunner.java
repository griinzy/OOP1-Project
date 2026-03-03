package bg.tu_varna.sit;

import bg.tu_varna.sit.commands.AddCommand;
import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.commands.HelpCommand;
import bg.tu_varna.sit.commands.PrintCommand;

import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class CommandRunner {
    private Map<String, Command> commands = new HashMap<>();
    private boolean isRunning;

    public CommandRunner() {
        commands.put("add", new AddCommand());
        commands.put("print", new PrintCommand());
        commands.put("help", new HelpCommand(commands));
    }

    public void start() {
        isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while(isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String name = parts[0];
            Command cmd = commands.get(name);
            cmd.execute();
        }
    }
}
