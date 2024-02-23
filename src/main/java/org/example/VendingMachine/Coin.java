package org.example.VendingMachine;

public enum Coin {
    DIRHAM_1(1),
    DIRHAM_2(2),
    DIRHAM_5(5),
    DIRHAM_10(10);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
