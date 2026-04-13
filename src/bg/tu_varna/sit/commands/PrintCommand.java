package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;

import java.util.List;

public class PrintCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();

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
        List<Product> products = warehouse.getProducts();

        for(Product p : products) {
            System.out.println(p);
        }
    }
}
