package bg.tu_varna.sit.model;

public class Manufacturer {
    private String name;

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
