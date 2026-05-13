package com.hotel.controller;

import com.hotel.service.Hotel;

public class BookingController {

    private Hotel hotel;

    public BookingController(Hotel hotel) {

        this.hotel = hotel;
    }
}