package bg.tu_varna.sit.model;

public class Location {
    private String section;
    private int shelf;
    private int slot;

    public Location(String section, int shelf, int slot) {
        this.section = section;
        this.shelf = shelf;
        this.slot = slot;
    }
}
