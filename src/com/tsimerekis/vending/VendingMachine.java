package com.tsimerekis.vending;

import com.tsimerekis.vending.chain.*;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private StateOfVendingMachine state = StateOfVendingMachine.IDLE;

    private Snack currentSelection;

    private double currentBalance;

    private final SnackDispenseHandler dispenseChain = new CokeHandler(
            new PepsiHandler(
                    new CheetosHandler(
                            new DoritosHandler(
                                    new KitKatHandler(
                                            new SnickersHandler(null)
                                    )
                            )
                    )
            )
    );

    private final List<Snack> availableSnacks = new ArrayList<Snack>();

    public VendingMachine(List<Snack> snacks) {
        if (snacks == null) {
            snacks = List.of();
        }
        availableSnacks.addAll(snacks);
    }

    public void selectSnack(String snackName) {
        if (state != StateOfVendingMachine.IDLE) {
            throw new IllegalStateException("Cannot select snack in current state: " + state);
        }

        if (snackName == null || snackName.trim().isEmpty()) {
            throw new IllegalArgumentException("Snack name cannot be null or empty");
        }

        availableSnacks.stream()
                .filter(snack -> snack.getName().toLowerCase().contains(snackName.toLowerCase()))
                .findFirst()
                .ifPresentOrElse(
                        snack -> {
                            if (snack.getQuantity() > 0) {
                                currentSelection = snack;
                                state = StateOfVendingMachine.WAITING_FOR_MONEY;
                            } else {
                                throw new OutOfStockException("Snack out of stock: " + snackName);
                            }
                        },
                        () -> {
                            throw new SnackNotFoundException("Snack not found: " + snackName);
                        }
                );

    }

    public boolean insertMoney(double amount) {
        if (state != StateOfVendingMachine.WAITING_FOR_MONEY) {
            throw new IllegalStateException("Cannot insert money in current state: " + state);
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Inserted amount must be positive: " + amount);
        }

        currentBalance += amount;

        if (currentBalance >= currentSelection.getPrice()) {
            state = StateOfVendingMachine.DISPENSING_SNACK;
            currentBalance = 0;

            return true;
        }

        return false;
    }

    public void dispenseSnack() {
        if (state != StateOfVendingMachine.DISPENSING_SNACK) {
            throw new IllegalStateException("Cannot dispense snack in current state: " + state);
        }
        dispenseChain.handleRequest(currentSelection);
        state = StateOfVendingMachine.IDLE;
    }
}
