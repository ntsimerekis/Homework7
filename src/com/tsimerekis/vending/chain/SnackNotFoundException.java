package com.tsimerekis.vending.chain;

public class SnackNotFoundException extends RuntimeException {
    public SnackNotFoundException(String message) {
        super(message);
    }
}
