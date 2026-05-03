package bg.tu_varna.sit.model;

import bg.tu_varna.sit.enums.Unit;

import java.time.LocalDate;

public class Product {
    private String name;
    private LocalDate expiryDate;
    private LocalDate dateAdded;
    private Manufacturer manufacturer;
    private Unit unit;
    private double quantity;
    private Location location;
    private String comment;

    public Product(String name, LocalDate expiryDate, LocalDate dateAdded, Manufacturer manufacturer, Unit unit, double quantity, Location location, String comment) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.dateAdded = dateAdded;
        this.manufacturer = manufacturer;
        this.unit = unit;
        this.quantity = quantity;
        this.location = location;
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
    public Location getLocation() {
        return location;
    }
    public String getComment() {
        return comment;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " " + expiryDate + " " + dateAdded + " " + manufacturer + " " + unit + " " + quantity + " " + location + " " + comment;
    }
}
