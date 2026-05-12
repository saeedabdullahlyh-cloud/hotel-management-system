package com.hotel.model;

public class Service {

    public String name;

    public double price;

    public boolean isComplimentary;

    public Service(String name,
                   double price,
                   boolean isComplimentary) {

        this.name = name;

        this.price = price;

        this.isComplimentary = isComplimentary;
    }
}