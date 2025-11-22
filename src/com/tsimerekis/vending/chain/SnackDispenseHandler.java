package com.tsimerekis.vending.chain;

import com.tsimerekis.vending.Snack;

public abstract class SnackDispenseHandler {
    private final SnackDispenseHandler nextHandler;

    private final String snackType;

    public SnackDispenseHandler(SnackDispenseHandler nextHandler, String snackType) {
        this.nextHandler = nextHandler;

        if (snackType != null) {
            this.snackType = snackType;
        } else {

            //This string must never match any snack name
            //We use weird unicode characters to do this
            this.snackType = "\uE000\uE001\uE002";
        }
    }

    public void handleRequest(Snack snack) {
        if (canHandle(snack)) {
            dispenseSnack(snack);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snack);
        } else {
            throw new VendingException("No handler found for request type: " + snack);
        }
    }

    private boolean canHandle(Snack snack) {
        return snack.getName().toLowerCase().contains(snackType.toLowerCase());
    }

    private void dispenseSnack(Snack snack) {
        if (!snack.dispense()) {
            //Note: We do not throw an OutOfStockException here because at this point the snack should be in stock
            throw new IllegalStateException("Snack out of stock: " + snack);
        }

        System.out.println("Dispensing snack: " + snack);
    }
}
