package com.tsimerekis.vending.chain;

public class CheetosHandler extends SnackDispenseHandler {
    public CheetosHandler(SnackDispenseHandler nextHandler) {
        super(nextHandler, "cheetos");
    }
}
