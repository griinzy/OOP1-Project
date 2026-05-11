package bg.tu_varna.sit;

import bg.tu_varna.sit.commands.*;
import bg.tu_varna.sit.commands.fileCommands.CloseCommand;
import bg.tu_varna.sit.commands.fileCommands.OpenCommand;
import bg.tu_varna.sit.commands.fileCommands.SaveAsCommand;
import bg.tu_varna.sit.commands.fileCommands.SaveCommand;
import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.enums.CommandType;
import bg.tu_varna.sit.files.FileService;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;
import bg.tu_varna.sit.util.Tokenizer;

import java.util.*;
/**
 * Клас, контролиращ програмата. Чете и изпълнява командите
 */
public class CommandRunner {
    /**
     * Колекция с регистрираните команди
     */
    private final Map<String, Command> commands = new HashMap<>();
    private boolean isRunning;

    private final Warehouse warehouse = Warehouse.getInstance();
    private final FileService fileService = FileService.getInstance();

    /**
     * Командите, които могат да се използват без отворен файл
     */
    private static final Set<String> allowedCommands = Set.of(
                                                        CommandType.OPEN.toString(),
                                                        CommandType.HELP.toString(),
                                                        CommandType.EXIT.toString());

    /**
     * Конструктор, който регистрира всички команди
     */
    public CommandRunner() {
        registerCommand(CommandType.ADD, new AddCommand());
        registerCommand(CommandType.REMOVE, new RemoveCommand());
        registerCommand(CommandType.CLEAN, new CleanCommand());
        registerCommand(CommandType.LOG, new LogCommand());
        registerCommand(CommandType.PRINT, new PrintCommand());
        registerCommand(CommandType.HELP, new HelpCommand(commands));
        registerCommand(CommandType.EXIT, new ExitCommand(this));
        registerCommand(CommandType.OPEN, new OpenCommand());
        registerCommand(CommandType.CLOSE, new CloseCommand());
        registerCommand(CommandType.SAVE, new SaveCommand());
        registerCommand(CommandType.SAVE_AS, new SaveAsCommand());
    }

    /**
     * Регистрира командата в колекцията
     * @param type Типа на командата
     * @param command Инстанцията на командата
     */
    private void registerCommand(CommandType type, Command command) {
        commands.put(type.toString(), command);
    }

    /**
     * Главният цикъл на програмата
     */
    public void start() {
        isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while(isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String name;
            String[] args;

            if(input.startsWith("save as")) {
                name = "save as";
                String remainingInput = input.substring("save as".length());
                args = remainingInput.isEmpty() ? new String[0] : Tokenizer.tokenize(remainingInput).toArray(new String[0]);
            }
            else {
                String[] parts = input.split(" ", 2);
                name = parts[0];
                args = parts.length > 1 ? Tokenizer.tokenize(parts[1]).toArray(new String[0]) : new String[0];
            }
            Command cmd = commands.get(name);
            if(cmd != null) {
                if(!allowedCommands.contains(name) && !fileService.isFileOpen()) {
                    System.out.println("No file is currently open.");
                }
                else {
                    String result = cmd.execute(args);
                    if (result.endsWith("CONFIRM_REMOVE")) {
                        System.out.println(result.replace("CONFIRM_REMOVE", "").trim());
                        System.out.print("Remove what's available? (yes/no): ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                            List<Product> batches = new ArrayList<>(warehouse.getProductBatchesByName(args[0]));
                            batches.sort(Comparator.comparing(Product::getExpiryDate));
                            double total = batches.stream().mapToDouble(Product::getQuantity).sum();
                            System.out.println(((RemoveCommand) cmd).remove(batches, total));
                        }
                    } else {
                        System.out.println(result);
                    }
                }
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
