package com.tsimerekis.vending.chain;

public class CokeHandler extends SnackDispenseHandler {
    public CokeHandler(SnackDispenseHandler nextHandler) {
        super(nextHandler, "coke");
    }
}
