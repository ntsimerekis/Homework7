package com.tsimerekis.vending.chain;

public class KitKatHandler extends SnackDispenseHandler {
    public KitKatHandler(SnackDispenseHandler nextHandler) {
        super(nextHandler, "kitkat");
    }
}
