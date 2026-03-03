package bg.tu_varna.sit.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private List<Product> products;

    public Warehouse() {
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
