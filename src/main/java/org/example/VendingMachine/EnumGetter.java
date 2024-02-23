package org.example.VendingMachine;

public class EnumGetter {
    public static Coin getCoinByValue(int coinValue) {
        for (Coin coin : Coin.values()) {
            if (coin.getValue() == coinValue) {
                return coin;
            }
        }
        return null;
    }

    public static Product getProductByName(String productName) {
        for (Product product : Product.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}
