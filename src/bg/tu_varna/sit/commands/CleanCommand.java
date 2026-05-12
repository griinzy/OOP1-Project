package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Премахват се всички стоки с изтекъл или чиито срок на годност скоро ще изтече.
 */
public class CleanCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public String getName() {
        return "clean";
    }

    @Override
    public String getDescription() {
        return "Removes all expired or soon to expire products.";
    }

    @Override
    public String execute(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate soon = today.plusDays(30);

        List<Product> products = warehouse.getProducts().stream().filter(p -> !p.getExpiryDate().isAfter(soon)).collect(Collectors.toList());

        if(products.isEmpty()) {
            return "No products to remove.";
        }
        StringBuilder result = new StringBuilder("Removed products: \n");
        for(Product p : products) {
            result.append(p.getName())
                    .append(", expires: ")
                    .append(p.getExpiryDate())
                    .append(", location: ")
                    .append(p.getLocation())
                    .append("\n");
            warehouse.removeProduct(p);
        }
        return result.toString();
    }
}
