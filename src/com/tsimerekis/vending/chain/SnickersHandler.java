package com.tsimerekis.vending.chain;

public class SnickersHandler extends SnackDispenseHandler {
    public SnickersHandler(SnackDispenseHandler nextHandler) {
        super(nextHandler, "snickers");
    }
}
