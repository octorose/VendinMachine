package org.example.VendingMachine;

import java.util.EnumMap;
import java.util.Map;

public class VendinMachine {
    private final Map<Coin, Integer> coinInventory;
    private final Map<Product, Integer> productInventory;
    private int userBalance;

    public VendinMachine() {
        coinInventory = new EnumMap<>(Coin.class);
        productInventory = new EnumMap<>(Product.class);
        initializeInventories();
        userBalance = 0;
    }

    private void initializeInventories() {
        for (Coin coin : Coin.values()) {
            coinInventory.put(coin, 10);
        }

        for (Product product : Product.values()) {
            productInventory.put(product, 10);
        }
    }

    public void insertCoin(Coin coin) {
        if (coinInventory.containsKey(coin)) {
            userBalance += coin.getValue();
            coinInventory.put(coin, coinInventory.get(coin) + 1);
        } else {
            System.out.println("Invalid coin inserted.");
        }
    }

    public void selectProduct(Product product) {
        if (productInventory.containsKey(product)) {
            if (userBalance >= product.getPrice()) {
                dispenseProduct(product);
            } else {
                System.out.println("Insufficient balance. Please insert more coins.");
            }
        } else {
            System.out.println("Invalid product selection.");
        }
    }

    private void dispenseProduct(Product product) {
        int change = userBalance - product.getPrice();
        System.out.println("Product dispensed: " + product.getName());
        if (change > 0) {
            System.out.println("Change returned: " + change + " dirham");
            userBalance = 0;
            returnChange(change);
        }
        userBalance = 0;
        productInventory.put(product, productInventory.get(product) - 1);
    }

    private void returnChange(int change) {
        for (Coin coin : Coin.values()) {
            int numCoins = change / coin.getValue();
            if (numCoins > 0 && coinInventory.get(coin) > 0) {
                int availableCoins = coinInventory.get(coin);
                int coinsToReturn = Math.min(numCoins, availableCoins);
                System.out.println("Returned " + coinsToReturn + " coins of " + coin.getValue() + " dirham");
                coinInventory.put(coin, availableCoins - coinsToReturn);
                change -= coinsToReturn * coin.getValue();
            }
        }
    }

    public void cancelRequest() {
        System.out.println("Request canceled. Refunding " + userBalance + " dirham");
        returnChange(userBalance);
        userBalance = 0;
    }

    public void reset() {
        coinInventory.clear();
        productInventory.clear();
        initializeInventories();
        userBalance = 0;
        System.out.println("Vending machine reset successfully.");
    }

    public int getUserBalance() {
        return userBalance;
    }
}

