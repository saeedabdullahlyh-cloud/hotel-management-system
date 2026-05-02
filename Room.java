package com.hotel;
class Room {
    int id;
    String type;
    double price;
    public Room(int id, String type) {
        this.id=id;
        this.type=type;
        if (type.equalsIgnoreCase("Single"))
            this.price=50;
        else if (type.equalsIgnoreCase("Double"))
            this.price=100;
        else if (type.equalsIgnoreCase("Sweet"))
            this.price=200;   
        else if (type.equalsIgnoreCase("Luxury"))
            this.price=300;   
        else
            this.price=0;
    }}
