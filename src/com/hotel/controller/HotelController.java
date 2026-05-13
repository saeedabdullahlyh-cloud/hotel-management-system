package com.hotel.controller;

import com.hotel.service.Hotel;

public class HotelController {

    private Hotel hotel;

    public HotelController(Hotel hotel) {

        this.hotel = hotel;
    }

    public Hotel getHotel() {

        return hotel;
    }
}