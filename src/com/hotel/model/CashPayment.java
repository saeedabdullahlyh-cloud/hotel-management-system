package com.hotel.model;

public class CashPayment extends Payment {

    @Override
    public void pay(double amount) {

        paid = true;

        transactionId =
                generateTransactionId();

        System.out.println(
                "\nCash Payment Successful!"
        );

        System.out.println(
                "Transaction ID: "
                        + transactionId
        );

        System.out.println(
                "Paid Amount: $"
                        + amount
        );
    }
}