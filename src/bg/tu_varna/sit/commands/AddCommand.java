package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.enums.Unit;
import bg.tu_varna.sit.model.Manufacturer;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;
import bg.tu_varna.sit.util.DateParser;

import java.time.LocalDate;

/**
 * Добавя нов продукт в склада.
 * Ако съществува продукт с еднакво име и срок на годност, количеството му се добавя към това на другия продукт.
 * Ако съществува продукт с еднакви име и различен срок на годност, той се добавя като отделен продукт.
 */
public class AddCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public String getName() {
        return "add ";
    }

    @Override
    public String getDescription() {
        return "add <name> <expiry date> <date added> <manufacturer> <unit> <quantity> <section> <comment> - Add a new product to the warehouse.";
    }

    @Override
    public String execute(String[] args) {
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
        return "Product " + name + " added successfully.";
    }
}
