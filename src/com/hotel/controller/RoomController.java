package com.hotel.controller;

import com.hotel.service.Hotel;

import java.time.LocalDate;

public class RoomController {

    private Hotel hotel;

    public RoomController(Hotel hotel) {

        this.hotel = hotel;
    }

    public void showRooms(LocalDate in,
                          LocalDate out) {

        hotel.showRooms(in, out);
    }

    public void searchRoom(int roomId) {

        hotel.searchByRoom(roomId);
    }
}