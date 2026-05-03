package bg.tu_varna.sit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse {
    private static Warehouse instance;

    private final List<Product> products = new ArrayList<>();
    private final Map<String, Manufacturer> manufacturers = new HashMap<>();

    private Warehouse() { }

    public static Warehouse getInstance() {
        if(instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        // еднакви продукти с един и същи срок на годност се поствавят на едно и също място
        for(Product prod : products) {
            if(prod.getName().equals(product.getName()) && prod.getExpiryDate().equals(product.getExpiryDate())) {
                prod.addQuantity(product.getQuantity());
                return;
            }
        }

        Location location = findFreeLocation(product.getSection());
        product.setLocation(location);
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    private boolean isLocationFree(Location location) {
        return products.stream().noneMatch(p -> p.getLocation().equals(location));
    }

    private Location findFreeLocation(String section) {
        for(int shelf = 1; ; shelf++) {
            for(int slot = 1; slot <= 20; slot++) {
                Location loc = new Location(section, shelf, slot);
                if(isLocationFree(loc)) {
                    return loc;
                }
            }
        }
    }

    public Manufacturer getManufacturer(String name) {
        if(!manufacturers.containsKey(name)) {
             manufacturers.put(name, new Manufacturer(name));
        }
        return manufacturers.get(name);
    }

    public List<Product> getProductBatchesByName(String name) {
        return products.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList());
    }
}
