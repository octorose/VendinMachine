package org.example;

import org.example.VendingMachine.Coin;
import org.example.VendingMachine.EnumGetter;
import org.example.VendingMachine.Product;
import org.example.VendingMachine.VendinMachine;
import org.example.VendingMachine.exceptions.ProductOutOfStockException;
import org.example.VendingMachine.exceptions.UnsuficentMoneyException;
import org.junit.jupiter.api.Assertions;
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
            vendingMachine.initializeInventories();
        }

        @Test
        void insertValidCoin() {
            Coin coin =  enumGetter.getCoinByValue(5);
            vendingMachine.insertCoin(coin);
            assertEquals(5, vendingMachine.getUserBalance());
        }

        @Test
        void insertInvalidCoin() {
            Coin coin = enumGetter.getCoinByValue(0.5);
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                vendingMachine.insertCoin(coin);
            });

        }

        @Test
        void selectValidProductWithSufficientBalance() throws ProductOutOfStockException, UnsuficentMoneyException {
            Coin coin = enumGetter.getCoinByValue(10);
            Product product = enumGetter.getProductByName("twix");
            vendingMachine.insertCoin(coin);
            vendingMachine.selectProduct(product);
            assertEquals(0, vendingMachine.getUserBalance());
        }

        @Test
        void selectAProductOutOfStock() throws ProductOutOfStockException, UnsuficentMoneyException {

            for (int i = 0; i < 10; i++) {
                Coin coin = enumGetter.getCoinByValue(5);
                vendingMachine.insertCoin(coin);
                vendingMachine.selectProduct(Product.WATER);
            }
            Assertions.assertThrows(ProductOutOfStockException.class, () -> {
                Coin coin = enumGetter.getCoinByValue(5);
                vendingMachine.insertCoin(coin);
                vendingMachine.selectProduct(Product.WATER);
            });

//            assertEquals(0, vendingMachine.selectProduct(product));
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
//testing poroduct  null