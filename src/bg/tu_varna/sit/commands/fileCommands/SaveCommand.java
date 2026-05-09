package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.files.FileService;

public class SaveCommand implements Command {
    private final FileService fileService = FileService.getInstance();

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save - save changes to file.";
    }

    @Override
    public String execute(String[] args) {
        try {
            fileService.save();
            return "Successfully saved to " + fileService.getFilePath();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
