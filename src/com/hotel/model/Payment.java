package com.hotel.model;

public abstract class Payment {

    public boolean paid = false;

    public String transactionId;

    public abstract void pay(double amount);

    public String generateTransactionId() {

        return "TXN"
                + System.currentTimeMillis();
    }
}