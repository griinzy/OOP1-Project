package bg.tu_varna.sit.commands;

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
    public void execute() {

    }
}
