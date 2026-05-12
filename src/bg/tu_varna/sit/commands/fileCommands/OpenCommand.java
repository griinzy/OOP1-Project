package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.files.FileService;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Зарежда продуктите от зададен файл.
 * Ако файл не съществува, той се създава.
 * Всички останали команди се изпълняват само ако има успешно зареден файл.
 */
public class OpenCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();
    private final FileService fileService = FileService.getInstance();

    @Override
    public String getName() {
        return "open";
    }

    @Override
    public String getDescription() {
        return "open <path> - Opens a file.";
    }

    @Override
    public String execute(String[] args) {
        String path = args[0];
        fileService.setFilePath(path);
        File file = new File(path);

        if(!file.exists()) {
            try {
                file.createNewFile();
                return "File not found, created a new warehouse: " + file.getName();
            }
            catch (IOException e) {
                return e.getMessage();
            }
        }

        try {
            List<Product> products = fileService.load();
            warehouse.getProducts().clear();
            warehouse.getProducts().addAll(products);
            return "Opened: " + file.getName();
        }
        catch (Exception e) {
            fileService.setFilePath(null);
            return e.getMessage();
        }
    }
}
