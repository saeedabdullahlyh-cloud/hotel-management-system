package com.hotel.model;

public class CashPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Cash.");
    }
}