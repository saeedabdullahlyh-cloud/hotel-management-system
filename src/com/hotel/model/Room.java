package com.hotel.model;

public class Room {

    public int id;

    public String type;

    public double price;

    public Room(int id, String type) {

        this.id = id;

        this.type = type;

        if (type.equals("Single"))
            price = 50;

        else if (type.equals("Double"))
            price = 100;

        else if (type.equals("Sweet"))
            price = 150;

        else if (type.equals("Luxury"))
            price = 300;
    }
}