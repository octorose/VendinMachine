package org.example.VendingMachine;

public enum Coin {

//    DIRHAM_HALF(0.5),
    DIRHAM_1(1),
    DIRHAM_2(2),
    DIRHAM_5(5),
    DIRHAM_10(10);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
