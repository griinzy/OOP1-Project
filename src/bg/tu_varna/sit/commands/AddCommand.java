package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.enums.Unit;
import bg.tu_varna.sit.model.Location;
import bg.tu_varna.sit.model.Manufacturer;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;
import bg.tu_varna.sit.util.DateParser;

import java.time.LocalDate;

public class AddCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add <name> <expiry date> <date added> <manufacturer> <unit> <quantity> <section> <comment> - Add a new product to the warehouse.";
    }

    @Override
    public void execute(String[] args) {
        String name = args[0];
        LocalDate expiry = DateParser.parse(args[1]);
        LocalDate added = DateParser.parse(args[2]);
        Manufacturer manufacturer = warehouse.getManufacturer(args[3]);
        Unit unit = Unit.parse(args[4]);
        double quantity = Double.parseDouble(args[5]);
        String section = args[6];
        String comment = args.length > 7 ? args[7] : "";

        Product product = new Product(name, expiry, added, manufacturer, unit, quantity, section, comment);
        warehouse.addProduct(product);
    }
}
