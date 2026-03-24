package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;

public class PrintCommand implements Command {

    @Override
    public String getName() {
        return "print";
    }

    @Override
    public String getDescription() {
        return "Outputs the information of the available products in warehouse";
    }

    @Override
    public void execute(String[] args) {

    }
}
