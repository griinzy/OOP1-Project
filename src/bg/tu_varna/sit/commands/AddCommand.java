package bg.tu_varna.sit.commands;

import java.time.LocalDate;

public class AddCommand implements Command {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add <name> <expiry date> <date added> <manufacturer> <unit> <quantity> <location> <comment> - Add a new product to the warehouse.";
    }

    @Override
    public void execute() {

    }
}
