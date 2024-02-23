package org.example.VendingMachine;

public enum Product {
    WATER("Water", 5),
    COCA("Coca", 7),
    TWIX("Twix", 10),
    BUENO("Bueno", 12);

    private final String name;
    private final int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
