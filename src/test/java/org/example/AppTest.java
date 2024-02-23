package org.example;

import org.example.VendingMachine.Coin;
import org.example.VendingMachine.EnumGetter;
import org.example.VendingMachine.Product;
import org.example.VendingMachine.VendinMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit test for simple App.
 */
public class AppTest{
        private VendinMachine vendingMachine;
        private  EnumGetter enumGetter;

        @BeforeEach
        void setUp() {
            vendingMachine = new VendinMachine();
            enumGetter = new EnumGetter();
        }

        @Test
        void insertValidCoin() {
            Coin coin =  enumGetter.getCoinByValue(5);
            vendingMachine.insertCoin(coin);
            assertEquals(5, vendingMachine.getUserBalance());
        }

        @Test
        void insertInvalidCoin() {
            Coin coin = enumGetter.getCoinByValue(3);
            vendingMachine.insertCoin(coin);
            assertEquals(0, vendingMachine.getUserBalance());
        }

        @Test
        void selectValidProductWithSufficientBalance() {
            Coin coin = enumGetter.getCoinByValue(10);
            Product product = enumGetter.getProductByName("twix");
            vendingMachine.insertCoin(coin);
            vendingMachine.selectProduct(product);
            assertEquals(0, vendingMachine.getUserBalance());
        }

        @Test
        void selectValidProductWithInsufficientBalance() {
            Coin coin = enumGetter.getCoinByValue(5);
            Product product = enumGetter.getProductByName("bueno");
            vendingMachine.insertCoin(coin);
            vendingMachine.selectProduct(product);
            assertEquals(5, vendingMachine.getUserBalance());
        }

        @Test
        void selectInvalidProduct() {
            Coin coin = enumGetter.getCoinByValue(10);
            Product product = enumGetter.getProductByName("invalidProduct");
            vendingMachine.insertCoin(coin);
            vendingMachine.selectProduct(product);
            assertEquals(10, vendingMachine.getUserBalance());
        }

        @Test
        void cancelRequest() {
            Coin coin = enumGetter.getCoinByValue(2);
            vendingMachine.insertCoin(coin);
            vendingMachine.cancelRequest();
            assertEquals(0, vendingMachine.getUserBalance());
        }

        @Test
        void resetVendingMachine() {
            Coin coin = enumGetter.getCoinByValue(5);
            vendingMachine.insertCoin(coin);
            vendingMachine.reset();
            assertEquals(0, vendingMachine.getUserBalance());
        }
}