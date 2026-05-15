package com.hotel.controller;

import com.hotel.model.Payment;
import com.hotel.service.Hotel;

import java.time.LocalDate;
import java.util.Scanner;

public class BookingController {

    private Hotel hotelService;

    public BookingController(Hotel hotelService) {

        this.hotelService = hotelService;
    }

    // ================= BOOK ROOM =================

    public void bookRoom(
            String name,
            LocalDate in,
            LocalDate out,
            Payment payment,
            Scanner sc
    ) {

        hotelService.book(
                name,
                in,
                out,
                payment,
                sc
        );
    }

    // ================= CANCEL =================

    public void cancelBooking(int id) {

        hotelService.cancel(id);
    }

    // ================= SEARCH =================

    public void searchBooking(int roomId) {

        hotelService.searchByRoom(roomId);
    }

    // ================= SAVE BOOKINGS =================

    public void saveBookings() {

        hotelService.saveToFile();
    }

    // ================= LOAD BOOKINGS =================

    public void loadBookings() {

        hotelService.loadBookingsFromDB();
    }
}