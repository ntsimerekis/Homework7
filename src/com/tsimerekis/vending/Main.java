package com.tsimerekis.vending;

import com.tsimerekis.vending.chain.OutOfStockException;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final List<Snack> initialSnacks = List.of(
                new Snack("Coke", 1.5, 10),
                new Snack("Pepsi", 1.5, 8),
                new Snack("Cheetos", 2.0, 5),
                new Snack("Doritos", 2.0, 7),
                new Snack("KitKat", 1.0, 15),
                new Snack("Snickers", 1.0, 2)
        );

        final VendingMachine vendingMachine = new VendingMachine(initialSnacks);

        System.out.println("Created vending machine with initial snacks:");
        initialSnacks.forEach(System.out::println);

        System.out.println("\nSelecting Coke and inserting money...");
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();

        System.out.println();

        System.out.println("Trying to dispense snack twice (should throw illgal state)...");
        try {
            vendingMachine.dispenseSnack();
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println();

        System.out.println("Buying all the Snickers");
        vendingMachine.selectSnack("snickers");
        vendingMachine.insertMoney(1.0);
        vendingMachine.dispenseSnack();

        vendingMachine.selectSnack("snickers");
        vendingMachine.insertMoney(1.0);
        vendingMachine.dispenseSnack();

        System.out.println("Trying to buy another Snickers (should throw out of stock exception)...");
        try {
            vendingMachine.selectSnack("snickers");
        } catch (OutOfStockException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

    }
}