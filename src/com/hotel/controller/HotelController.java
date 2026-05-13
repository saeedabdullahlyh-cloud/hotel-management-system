package com.hotel.controller;

import com.hotel.service.Hotel;

import java.time.LocalDate;

public class HotelController {

    private Hotel hotel;

    public HotelController(Hotel hotel) {

        this.hotel = hotel;
    }

    public void showRooms(LocalDate in,
                          LocalDate out) {

        hotel.showRooms(in, out);
    }

    public void showRevenue() {

        hotel.revenue();
    }
}