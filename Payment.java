package com.hotel;
abstract class Payment {
    abstract void pay(double amount);
}
class CashPayment extends Payment {
    void pay(double amount) {
        System.out.println("Paid $" + amount + " using Cash.");
    }}
class CardPayment extends Payment {
    void pay(double amount) {
    System.out.println("Paid $" + amount + " using Card.");}
}
