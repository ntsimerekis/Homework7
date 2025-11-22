package com.tsimerekis.vending.test;

import com.tsimerekis.vending.Snack;
import com.tsimerekis.vending.chain.SnackDispenseHandler;
import com.tsimerekis.vending.chain.VendingException;

public class DispenseHandlerTest {

    public static void main(String[] args) {
        System.out.println("Running DispenseHandlerTest...");
        DispenseHandlerTest test = new DispenseHandlerTest();

        System.out.println("Test Null Handler: " + (test.testNullHandler() ? "Passed" : "Failed"));
        System.out.println("Test Null Snack Type: " + (test.testNullSnackType() ? "Passed" : "Failed"));
        System.out.println("Test Out Of Stock: " + (test.testOutOfStock() ? "Passed" : "Failed"));
    }

    public boolean testNullHandler() {

        final SnackDispenseHandler nullNextHandler = new SnackDispenseHandler(null, "test") {
        };

        try {
            nullNextHandler.handleRequest(new Snack("snack", 1.0, 1));
        } catch (VendingException e) {
            System.out.println("Caught expected VendingException: " + e.getMessage());
            return true;
        }

        return false;
    }

    public boolean testNullSnackType() {

        final SnackDispenseHandler nullSnackTypeHandler = new SnackDispenseHandler(null, null) {
        };

        try {
            nullSnackTypeHandler.handleRequest(new Snack("snack", 1.0, 1));
        } catch (VendingException e) {
            System.out.println("Caught expected VendingException: " + e.getMessage());
            return true;
        }

        return false;
    }

    public boolean testOutOfStock() {

        final SnackDispenseHandler outOfStockHandler = new SnackDispenseHandler(null, "snack") {
        };

        try {
            outOfStockHandler.handleRequest(new Snack("snack", 1.0, 0));
        } catch (IllegalStateException e) {
            System.out.println("Caught expected IllegalStateException: " + e.getMessage());
            return true;
        }

        return false;
    }
}
