package com.tsimerekis.vending.test;

import com.tsimerekis.vending.Snack;

public class SnackTest {

    public boolean testSnackCreation() {
        try {
            new Snack(null, -1.0, -100);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }

    public boolean addNegativePrice() {
        final Snack snack = new Snack(null, 1.0, 100);

        try {
            snack.addStock(-100);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }
}
