package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.files.FileService;

import java.io.IOException;

public class SaveAsCommand implements Command {
    private final FileService fileService = FileService.getInstance();

    @Override
    public String getName() {
        return "save as";
    }

    @Override
    public String getDescription() {
        return "save as <path> - Save to another file.";
    }

    @Override
    public String execute(String[] args) {
        try {
            String path = args[0];
            fileService.saveAs(path);
            return "Successfully saved to " + path;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
