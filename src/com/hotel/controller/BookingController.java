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

    // ================= BOOK ROOM =================

    public void bookRoom(String name,
                         String roomType,
                         int checkInDay,
                         int checkOutDay) {

        System.out.println("\n===== BOOKING DETAILS =====");

        System.out.println("Customer Name: " + name);

        System.out.println("Room Type: " + roomType);

        System.out.println("Check-In Day: " + checkInDay);

        System.out.println("Check-Out Day: " + checkOutDay);

        int totalDays =
                checkOutDay - checkInDay;

        int pricePerDay = 0;

        // ================= ROOM PRICES =================

        if (roomType.equals("Single")) {

            pricePerDay = 2000;
        }

        else if (roomType.equals("Double")) {

            pricePerDay = 4000;
        }

        else if (roomType.equals("Sweet")) {

            pricePerDay = 7000;
        }

        else if (roomType.equals("Luxury")) {

            pricePerDay = 10000;
        }

        int total =
                totalDays * pricePerDay;

        System.out.println("Total Days: " + totalDays);

        System.out.println("Total Price: " + total);

        System.out.println("==============================");
    }

    // ================= OLD METHOD =================

    public void bookRoom(String name,
                         LocalDate in,
                         LocalDate out,
                         Payment payment,
                         Scanner sc) {

        hotel.book(name, in, out, payment, sc);
    }

    // ================= CANCEL =================

    public void cancelBooking(int id) {

        hotel.cancel(id);
    }

    // ================= SEARCH =================

    public void searchBooking(int roomId) {

        hotel.searchByRoom(roomId);
    }
}