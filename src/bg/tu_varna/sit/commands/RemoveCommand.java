package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.enums.EntryType;
import bg.tu_varna.sit.model.Log;
import bg.tu_varna.sit.model.LogEntry;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class RemoveCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();
    private final Log log = Log.getInstance();

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "remove <name> <quantity> - Remove a product from the warehouse.";
    }

    @Override
    public String execute(String[] args) {
        String name = args[0];
        double quantity = Double.parseDouble(args[1]);

        List<Product> batches = warehouse.getProductBatchesByName(name);

        batches.sort(Comparator.comparing(Product::getExpiryDate));

        double totalAvailable = batches.stream().mapToDouble(Product::getQuantity).sum();

        if(quantity > totalAvailable) {
            StringBuilder result = new StringBuilder();
            result.append("Trying to remove more than is available. Available batches: \n");
            for(Product batch : batches) {
                result.append(batch.getName())
                        .append(" - ")
                        .append(batch.getQuantity())
                        .append(" ")
                        .append(batch.getUnit())
                        .append(", expires: ")
                        .append(batch.getExpiryDate())
                        .append("\n");
            }
            result.append("CONFIRM_REMOVE");
            return result.toString();
        }
        return remove(batches, quantity);
    }

    public String remove(List<Product> batches, double quantity) {
        StringBuilder result = new StringBuilder();

        double remainingQuantity = quantity;
        for(Product batch : batches) {
            if(remainingQuantity <= 0) break;
            if(batch.getQuantity() <= remainingQuantity) {
                result.append("Removed ")
                        .append(batch.getQuantity())
                        .append(" ")
                        .append(batch.getUnit())
                        .append(" of ")
                        .append(batch.getName())
                        .append(" from ")
                        .append(batch.getLocation())
                        .append("\n");
                remainingQuantity -= batch.getQuantity();
                warehouse.removeProduct(batch);
            }
            else {
                result.append("Removed ")
                        .append(remainingQuantity)
                        .append(" ")
                        .append(batch.getUnit())
                        .append(" of ")
                        .append(batch.getName())
                        .append(" from ")
                        .append(batch.getLocation())
                        .append("\n");
                log.add(new LogEntry(EntryType.REMOVED, batch.getName(), remainingQuantity, LocalDate.now()));
                batch.removeQuantity(remainingQuantity);
                remainingQuantity = 0;
            }
        }

        return result.toString();
    }
}
