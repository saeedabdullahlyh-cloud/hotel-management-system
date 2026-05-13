package com.hotel.controller;
import com.hotel.service.Hotel;
import com.hotel.model.Payment;
import java.time.LocalDate;
import java.util.Scanner;
public class BookingController {
    private Hotel hotel;
    public BookingController(Hotel hotel) {
        this.hotel = hotel;
    }
    public void bookRoom(String name,
                         LocalDate in,
                         LocalDate out,
                         Payment payment,
                         Scanner sc) {
        hotel.book(name, in, out, payment, sc);
    }
    public void cancelBooking(int id) {
        hotel.cancel(id);
    }
    public void searchBooking(int roomId) {
        hotel.searchByRoom(roomId);
    }
}