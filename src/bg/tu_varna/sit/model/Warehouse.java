package bg.tu_varna.sit.model;

import bg.tu_varna.sit.enums.EntryType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Клас, управляващ продуктите в склада
 */
public class Warehouse {
    private static Warehouse instance;

    /**
     * Списък с всички продукти в склада
     */
    private final List<Product> products = new ArrayList<>();
    /**
     * Колекция с всички производители
     */
    private final Map<String, Manufacturer> manufacturers = new HashMap<>();

    private final Log log = Log.getInstance();

    private Warehouse() { }

    /**
     * Връща инстанцията на класа
     * @return инстанцията на склада
     */
    public static Warehouse getInstance() {
        if(instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    /**
     * @return списъка с продукти
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Добавя продукт в склада
     * @param product продуктът за добавяне
     */
    public void addProduct(Product product) {
        // еднакви продукти с един и същи срок на годност се поствавят на едно и също място
        for(Product prod : products) {
            if(prod.getName().equals(product.getName()) && prod.getExpiryDate().equals(product.getExpiryDate())) {
                log.add(new LogEntry(EntryType.ADDED, product.getName(), product.getQuantity(), product.getDateAdded()));
                prod.addQuantity(product.getQuantity());
                return;
            }
        }

        Location location = findFreeLocation(product.getSection());
        product.setLocation(location);
        products.add(product);
    }

    /**
     * Премахва продук от склада
     * @param product продуктът за премахване
     */
    public void removeProduct(Product product) {
        log.add(new LogEntry(EntryType.REMOVED, product.getName(), product.getQuantity(), LocalDate.now()));
        products.remove(product);
    }

    /**
     * Проверява дали дадено място е свободно
     * @param location мястото за проверка
     */
    private boolean isLocationFree(Location location) {
        return products.stream().noneMatch(p -> p.getLocation().equals(location));
    }

    /**
     * Намира първото свободно място в зададена секция
     * @param section цекцията за търсене
     * @return първото свободно място
     */
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

    /**
     * Връща или създава производител по име
     * @param name името на производителя
     * @return намерения производител
     */
    public Manufacturer getManufacturer(String name) {
        if(!manufacturers.containsKey(name)) {
             manufacturers.put(name, new Manufacturer(name));
        }
        return manufacturers.get(name);
    }

    /**
     * Връща всички партиди с дадено име
     * @param name името на продукта
     * @return списък с намерените продукти
     */
    public List<Product> getProductBatchesByName(String name) {
        return products.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList());
    }
}
