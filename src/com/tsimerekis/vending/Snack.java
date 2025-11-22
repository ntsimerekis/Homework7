package com.tsimerekis.vending;

public class Snack {

    private final String name;

    private final double price;

    private int quantity;

    public Snack(String name, double price, int quantity) {
        if ( name == null || price < 0 || quantity < 0) {
            throw new IllegalArgumentException("Invalid snack parameters");
        }

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Stock amount cannot be negative");
        }

        this.quantity += amount;
    }

    public boolean dispense() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Qty: " + quantity + ")";
    }
}
