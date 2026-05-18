package com.hotel.controller;

import com.hotel.model.Booking;
import com.hotel.model.Room;
import com.hotel.service.Hotel;

import java.time.LocalDate;

public class RoomController {

    private Hotel hotelService =
            new Hotel();

    // ================= CREATE BOOKING =================

    public Booking createBooking(

            String customerName,

            String cnic,

            String phone,

            String email,

            String address,

            LocalDate in,

            LocalDate out
    ) {

        return new Booking(

                customerName,

                cnic,

                phone,

                email,

                address,

                in,

                out
        );
    }

    // ================= ADD ROOM =================

    public void addRoomToBooking(

            Booking booking,

            int roomId,

            String roomType
    ) {

        String cleanType =
                roomType
                        .replace(" Room", "")
                        .replace(" - $50", "")
                        .replace(" - $100", "")
                        .replace(" - $150", "")
                        .replace(" - $300", "");

        Room room =
                new Room(
                        roomId,
                        cleanType
                );

        booking.addRoom(room);
    }

    // ================= SAVE BOOKING =================

    public void saveBooking(
            Booking booking
    ) {

        hotelService.bookings.add(
                booking
        );

        hotelService.addRevenue(
                booking.total()
        );

        hotelService.saveBookingToDB(
                booking
        );

        hotelService.saveToFile();

        hotelService.saveRevenue();
    }

    // ================= ROOM STATUS =================

    public String getRoomStatus(

            LocalDate in,

            LocalDate out
    ) {

        StringBuilder sb =
                new StringBuilder();

        sb.append(
                "=========== ROOM STATUS ===========\n\n"
        );

        for (int i = 1; i <= 20; i++) {

            boolean available =
                    hotelService.isAvailable(
                            i,
                            in,
                            out
                    );

            if (available) {

                sb.append(
                        "Room ID "
                                + i
                                + " → AVAILABLE\n"
                );
            }

            else {

                sb.append(
                        "Room ID "
                                + i
                                + " → BOOKED\n"
                );
            }
        }

        return sb.toString();
    }

    // ================= SEARCH ROOM =================

    public String searchRoom(
            int roomId
    ) {

        return hotelService.searchByRoom(
                roomId
        );
    }

    // ================= CANCEL ROOM =================

    public boolean cancelRoom(
            int roomId
    ) {

        return hotelService.cancel(
                roomId
        );
    }

    // ================= LOAD BOOKINGS =================

    public void loadBookings() {

        hotelService.loadBookingsFromDB();
    }

    // ================= GET HOTEL SERVICE =================

    public Hotel getHotelService() {

        return hotelService;
    }
}