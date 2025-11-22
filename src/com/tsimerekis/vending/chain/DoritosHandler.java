package com.tsimerekis.vending.chain;

public class DoritosHandler extends SnackDispenseHandler{

    public DoritosHandler(SnackDispenseHandler nextHandler) {
        super(nextHandler, "doritos");
    }
}
