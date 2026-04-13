package bg.tu_varna.sit.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private static Warehouse instance;

    private final List<Product> products = new ArrayList<>();

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
}
