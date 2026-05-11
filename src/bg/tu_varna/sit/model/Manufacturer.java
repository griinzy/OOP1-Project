package bg.tu_varna.sit.model;

/**
 * Производител на продукт
 */
public class Manufacturer {
    private String name;

    /**
     * @param name името на производителя
     */
    public Manufacturer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
