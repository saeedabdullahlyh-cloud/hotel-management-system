package com.hotel.controller;

import com.hotel.service.Hotel;

public class PaymentController {

    private Hotel hotel;

    public PaymentController(Hotel hotel) {

        this.hotel = hotel;
    }
}