package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;

import java.util.Comparator;
import java.util.List;

public class RemoveCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "remove <name> <quantity> - Remove a product from the warehouse.";
    }

    @Override
    public void execute(String[] args) {
        String name = args[0];
        double quantity = Double.parseDouble(args[1]);

        List<Product> batches = warehouse.getProductBatchesByName(name);

        batches.sort(Comparator.comparing(Product::getExpiryDate));

        double remainingQuantity = quantity;
        for(Product batch : batches) {
            if(remainingQuantity <= 0) break;
            if(batch.getQuantity() <= remainingQuantity) {
                remainingQuantity -= batch.getQuantity();
                warehouse.removeProduct(batch);
            }
            else {
                batch.removeQuantity(remainingQuantity);
                remainingQuantity = 0;
            }
        }
    }
}
