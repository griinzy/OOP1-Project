package bg.tu_varna.sit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        products.add(product);
    }

    public Manufacturer getManufacturer(String name) {
        if(!manufacturers.containsKey(name)) {
             manufacturers.put(name, new Manufacturer(name));
        }
        return manufacturers.get(name);
    }
}
