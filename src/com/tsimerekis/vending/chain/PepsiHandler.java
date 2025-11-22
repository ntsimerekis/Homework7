package com.tsimerekis.vending.chain;

public class PepsiHandler extends SnackDispenseHandler{
    public PepsiHandler(SnackDispenseHandler nextHandler) {
        super(nextHandler, "pepsi");
    }
}
