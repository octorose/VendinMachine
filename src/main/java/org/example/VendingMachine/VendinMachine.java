package org.example.VendingMachine;

import org.example.VendingMachine.exceptions.ProductOutOfStockException;
import org.example.VendingMachine.exceptions.UnsuficentMoneyException;

import java.util.*;

public class VendinMachine {

    private final List<Coin> ACCEPTED_COINS = Arrays.asList(Coin.DIRHAM_1, Coin.DIRHAM_2, Coin.DIRHAM_5, Coin.DIRHAM_10);
    private final Map<Coin, Integer> coinInventory;
    private final Map<Product, Integer> productInventory;
    private final Map<Coin, Integer> onHoldCoins;

    public VendinMachine() {
        coinInventory = new EnumMap<>(Coin.class);
        productInventory = new EnumMap<>(Product.class);
        initializeInventories();
       onHoldCoins = new EnumMap<>(Coin.class);
    }

    public void initializeInventories() {
//        cancelRequest();
        for (Coin coin : Coin.values()) {
            coinInventory.put(coin, 10);
        }

        for (Product product : Product.values()) {
            productInventory.put(product, 10);
        }
    }


    public void insertCoin(Coin coin) {
        if (!ACCEPTED_COINS.contains(coin)) {
        throw  new IllegalArgumentException("Invalid coin value. Please insert 1, 2, 5, or 10 dirham coins.");
        }
        onHoldCoins.put(coin, onHoldCoins.getOrDefault(coin, 0) + 1);
        System.out.println(coin.getValue() + " dirham coin inserted.");
        System.out.println("Current balance: " + sumCoins(onHoldCoins) + " dirham");
    }

    public void selectProduct(Product product) throws ProductOutOfStockException, UnsuficentMoneyException {
        int availableProducts = productInventory.get(product);
        if (availableProducts==0) {
            throw new ProductOutOfStockException("Product is out of stock. Please select another product.");
        }
        if (sumCoins(onHoldCoins) < product.getPrice()) {
            throw new UnsuficentMoneyException("Insufficient money to buy the product");
        }
        // Caculate the ammount of money to return to the costumer
        int change = sumCoins(onHoldCoins) - product.getPrice();
        returnChange(change);
        // Decease co/unt of product from inventory
        dispenseProduct(product);
        // Clear On hold coins
        onHoldCoins.clear();



    }

    private Product dispenseProduct(Product product) {
        int availableProducts = productInventory.get(product);
        productInventory.put(product, availableProducts - 1);
        System.out.println("Dispensing " + product.getName());
        System.out.println("Remaining " + product.getName() + " in stock: " + productInventory.get(product));
        return product;
    }

    private EnumMap<Coin,Integer> returnChange(int change) {
        System.out.println("Returning " + change + " dirham in total.");
        EnumMap<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {

            int coinCount = (int) (change / coin.getValue());
            if (coinCount > 0) {
                int availableCoins = coinInventory.get(coin);
                int returnedCoins = Math.min(coinCount, availableCoins);
                coinInventory.put(coin, availableCoins - returnedCoins);
                change -= returnedCoins * coin.getValue();
                System.out.println(returnedCoins + " x " + coin.getValue() + " dirham");
                changeCoins.put(coin, returnedCoins);
            }
        }
        return changeCoins;
    }

    public EnumMap<Coin,Integer> cancelRequest() {
        System.out.println("Request cancelled. Returning " + sumCoins(onHoldCoins) + " dirham.");
        returnChange(sumCoins(onHoldCoins));
        onHoldCoins.clear();
        return returnChange(sumCoins(onHoldCoins));
    }
//reset the vending machine return all the coins and products to the initial state returning Coins and Products
public EnumMap<Coin, Integer> reset() {
    System.out.println("Resetting the vending machine. Returning " + sumCoins(onHoldCoins) + " dirham.");
    EnumMap<Coin, Integer> returnedCoins = returnChange(sumCoins(onHoldCoins));
    onHoldCoins.clear();
    initializeInventories();
    return returnedCoins;
}



    public int getUserBalance() {
        return sumCoins(onHoldCoins);
    }

    public int sumCoins(Map<Coin, Integer> coins ) {
        int sum = 0;
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            sum += entry.getKey().getValue() * entry.getValue();
        }
        return sum;
    }

    // 1:10 -> {1:2, 2:2, 5:0, 10:0}

    // Create a function
}

