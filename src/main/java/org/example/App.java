package org.example;

import org.example.VendingMachine.*;
import org.example.VendingMachine.exceptions.ProductOutOfStockException;
import org.example.VendingMachine.exceptions.UnsuficentMoneyException;

import java.util.Locale;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) throws ProductOutOfStockException, UnsuficentMoneyException {
    VendinMachine  vendingMachine = new VendinMachine();
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        while (true) {
            printMenu();
            int choice = selectChoice(scanner);
            switch (choice) {
                case 1:
                    System.out.println("Available Coins: 1, 2, 5, 10");
                    System.out.print("Enter coin value: ");
                    System.out.println("heere");
                    double coinValue = scanner.nextDouble();
                    Coin coin = getCoinByValue(coinValue);

                    if (coin != null) {
                        vendingMachine.insertCoin(coin);
                    } else {
                        System.out.println("Invalid coin value. Please insert 1, 2, 5, or 10 dirham coins.");
                    }
                    break;
                case 2:
                    System.out.println("Available Products: water, coca, twix, bueno");
                    System.out.print("Enter product name: ");
                    String productName = scanner.next().toUpperCase();
                    Product product = getProductByName(productName);
                    if (product != null) {
                        vendingMachine.selectProduct(product);
                    } else {
                        System.out.println("Invalid product selection.");
                    }
                    break;
                case 3:
                    vendingMachine.cancelRequest();
                    break;
                case 4:
                    System.out.print("Enter reset code: ");
                    int resetCode = scanner.nextInt();
                    if (resetCode == 1234) {  // Replace with a secure code
                        vendingMachine.reset();
                    } else {
                        System.out.println("Incorrect reset code. Access denied.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting Vending Machine. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
    }

}
private  static int selectChoice(Scanner scanner){
    int choice = scanner.nextInt();
    return choice;
}
private  static void printMenu(){
    System.out.println("\nVending Machine Menu:");
    System.out.println("1. Insert Coin");
    System.out.println("2. Select Product");
    System.out.println("3. Cancel Request");
    System.out.println("4. Reset Vending Machine (Supplier Only)");
    System.out.println("5. Exit");

    System.out.print("Enter your choice: ");
}
    private static Coin getCoinByValue(double coinValue) {
        for (Coin coin : Coin.values()) {
            if (coin.getValue() == coinValue) {
                return coin;
            }
        }
        return null;
    }

    private static Product getProductByName(String productName) {
        for (Product product : Product.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}