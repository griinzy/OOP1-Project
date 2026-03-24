package bg.tu_varna.sit;

import bg.tu_varna.sit.commands.*;
import bg.tu_varna.sit.commands.interfaces.Command;

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
        commands.put("exit", new ExitCommand(this));
    }

    public void start() {
        isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while(isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String name = parts[0];
            String[] args = Tokenizer.tokenize(parts[1]).toArray(new String[0]);
            Command cmd = commands.get(name);
            if(cmd != null) {
                cmd.execute(args);
            }
            else {
                System.out.println("Unknown command");
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
