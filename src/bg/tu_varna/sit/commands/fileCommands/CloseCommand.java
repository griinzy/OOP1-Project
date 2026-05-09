package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.files.FileService;
import bg.tu_varna.sit.model.Warehouse;

public class CloseCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();
    private final FileService fileService = FileService.getInstance();

    @Override
    public String getName() {
        return "close";
    }

    @Override
    public String getDescription() {
        return "Closes a file.";
    }

    @Override
    public String execute(String[] args) {
        if(!fileService.isFileOpen()) {
            return "No file is currently open.";
        }
        warehouse.getProducts().clear();
        fileService.setFilePath(null);
        return "File closed.";
    }
}
