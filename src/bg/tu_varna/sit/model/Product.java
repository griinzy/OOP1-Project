package bg.tu_varna.sit.model;

import bg.tu_varna.sit.enums.Unit;

import java.time.LocalDate;

public class Product {
    private String name;
    private LocalDate expiryDate;
    private LocalDate dateAdded;
    private Manufacturer manufacturer;
    private Unit unit;
    private String section;
    private double quantity;
    private Location location;
    private String comment;

    public Product(String name, LocalDate expiryDate, LocalDate dateAdded, Manufacturer manufacturer, Unit unit, double quantity, String section, String comment) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.dateAdded = dateAdded;
        this.manufacturer = manufacturer;
        this.unit = unit;
        this.quantity = quantity;
        this.section = section;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    public Unit getUnit() {
        return unit;
    }
    public double getQuantity() {
        return quantity;
    }
    public String getSection() { return section; }
    public Location getLocation() {
        return location;
    }
    public String getComment() {
        return comment;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public void addQuantity(double amount) {
        this.quantity += amount;
    }

    @Override
    public String toString() {
        return name + " " + expiryDate + " " + dateAdded + " " + manufacturer + " " + unit + " " + quantity + " " + location + " " + comment;
    }
}
