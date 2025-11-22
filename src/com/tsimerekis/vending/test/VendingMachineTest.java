package com.tsimerekis.vending.test;

import com.tsimerekis.vending.Snack;
import com.tsimerekis.vending.VendingMachine;
import com.tsimerekis.vending.chain.OutOfStockException;
import com.tsimerekis.vending.chain.SnackNotFoundException;

import java.util.List;

public class VendingMachineTest {

    private final List<Snack> initialSnacks = List.of(
            new Snack("Coke", 1.5, 10),
            new Snack("Pepsi", 1.5, 8),
            new Snack("Cheetos", 2.0, 5),
            new Snack("Doritos", 2.0, 7),
            new Snack("KitKat", 1.0, 15),
            new Snack("Snickers", 1.0, 2)
    );

    public boolean testNullSnackList() {
        try {
            new VendingMachine(null);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean testEarlyDispense() {
        final VendingMachine machine = new VendingMachine(initialSnacks);

        try {
            machine.selectSnack("Coke");
            machine.dispenseSnack();
        } catch (IllegalStateException e) {
            return true;
        }

        return false;
    }

    public boolean testNegativePayment() {
        final VendingMachine machine = new VendingMachine(initialSnacks);

        try {
            machine.selectSnack("Pepsi");
            machine.insertMoney(-5.00);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }

    public boolean testCorrectAmount() {
        final VendingMachine machine = new VendingMachine(initialSnacks);

        try {
            machine.selectSnack("Coke");
            machine.insertMoney(.50);
            machine.dispenseSnack();
        } catch (IllegalStateException e) {
            return true;
        }

        return false;
    }

    public boolean testOutOfStock() {
        final VendingMachine machine = new VendingMachine(initialSnacks);

        try {
            machine.selectSnack("Snickers");
            machine.insertMoney(1.00);
            machine.dispenseSnack();

            machine.selectSnack("Snickers");
            machine.insertMoney(1.00);
            machine.dispenseSnack();

            machine.selectSnack("Snickers");
        } catch (OutOfStockException e) {
            return true;
        }

        return false;
    }

    public boolean testSnackNotFound() {
        final VendingMachine machine = new VendingMachine(initialSnacks);

        try {
            machine.selectSnack("Fritos");
        } catch (SnackNotFoundException e) {
            return true;
        }

        return false;
    }
}
