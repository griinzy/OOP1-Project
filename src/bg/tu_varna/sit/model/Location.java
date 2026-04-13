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

    public String getSection() {
        return section;
    }
    public int getShelf() {
        return shelf;
    }
    public int getSlot() {
        return slot;
    }

    public static Location parse(String args) {
        String[] parts = args.split("/");
        return new Location(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Location)) {
            return false;
        }
        Location other = (Location) o;
        return this.section.equals(other.section) && this.shelf == other.shelf && this.slot == other.slot;
    }

    @Override
    public String toString() {
        return section + '/' + shelf + "/" + slot;
    }
}
