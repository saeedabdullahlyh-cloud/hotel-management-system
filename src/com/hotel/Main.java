package com.hotel;

import com.hotel.model.CardPayment;
import com.hotel.model.CashPayment;
import com.hotel.model.Payment;
import com.hotel.model.Room;

import com.hotel.service.Hotel;

import com.hotel.view.HotelGUI;

import com.hotel.controller.BookingController;
import com.hotel.controller.HotelController;
import com.hotel.controller.PaymentController;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        new HotelGUI();

        Scanner sc = new Scanner(System.in);

        Hotel h = new Hotel();

        HotelController hotelController =
                new HotelController(h);

        BookingController bookingController =
                new BookingController(h);

//        PaymentController paymentController =
//                new PaymentController();

        // LOAD BOOKINGS FROM DATABASE

        h.loadBookingsFromDB();

        // DEFAULT ROOMS

        for (int i = 1; i <= 25; i++) {

            if (i <= 10)

                h.addRoom(new Room(i, "Single"));

            else if (i <= 15)

                h.addRoom(new Room(i, "Double"));

            else if (i <= 20)

                h.addRoom(new Room(i, "Sweet"));

            else

                h.addRoom(new Room(i, "Luxury"));
        }

        while (true) {

            System.out.println("\n--- MENU ---");

            System.out.println("1. Show Rooms");

            System.out.println("2. Book");

            System.out.println("3. Cancel");

            System.out.println("4. Total Revenue");

            System.out.println("5. Search by Room ID");

            System.out.println("6. Save Bookings");

            System.out.println("7. Exit");

            int ch =
                    h.getValidInt(sc,
                            "Enter choice: ");

            // ================= SHOW ROOMS =================

            if (ch == 1) {

                int d1 =
                        h.getValidInt(sc,
                                "Enter check-in day: ");

                int d2 =
                        h.getValidInt(sc,
                                "Enter check-out day: ");

                LocalDate in =
                        LocalDate.of(2026, 5, d1);

                LocalDate out =
                        LocalDate.of(2026, 5, d2);

                hotelController.showRooms(in, out);
            }

            // ================= BOOK =================

            else if (ch == 2) {

                int d1 =
                        h.getValidInt(sc,
                                "Check-in day: ");

                int d2 =
                        h.getValidInt(sc,
                                "Check-out day: ");

                if (d2 <= d1) {

                    System.out.println(
                            "Invalid dates!");

                    continue;
                }

                LocalDate in =
                        LocalDate.of(2026, 5, d1);

                LocalDate out =
                        LocalDate.of(2026, 5, d2);

                System.out.print("Enter Name: ");

                sc.nextLine();

                String name =
                        sc.nextLine();

                System.out.println(
                        "Payment: 1. Cash  2. Card");

                int p =
                        h.getValidInt(sc,
                                "Enter choice: ");

                Payment pay =
                        (p == 1)
                                ? new CashPayment()
                                : new CardPayment();

                bookingController.bookRoom(
                        name,
                        in,
                        out,
                        pay,
                        sc
                );
            }

            // ================= CANCEL =================

            else if (ch == 3) {

                int id =
                        h.getValidInt(sc,
                                "Enter Booking ID: ");

                bookingController.cancelBooking(id);
            }

            // ================= REVENUE =================

            else if (ch == 4) {

                hotelController.showRevenue();
            }

            // ================= SEARCH =================

            else if (ch == 5) {

                int roomId =
                        h.getValidInt(sc,
                                "Enter Room ID: ");

                bookingController.searchBooking(roomId);
            }

            // ================= FILE SAVE =================

            else if (ch == 6) {

                h.saveToFile();
            }

            // ================= EXIT =================

            else if (ch == 7) {

                System.out.println(
                        "Exiting system...");

                break;
            }

            else {

                System.out.println(
                        "Invalid choice!");
            }
        }

        sc.close();
    }
}