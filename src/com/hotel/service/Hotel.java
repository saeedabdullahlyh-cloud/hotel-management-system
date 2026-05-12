package com.hotel.service;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.Payment;
import com.hotel.model.Booking;
import com.hotel.model.Room;
import com.hotel.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {

    List<Room> rooms = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void showRoomTypes() {

        System.out.println("\n--- ROOM TYPES ---");
        System.out.println("1. Single  : $50");
        System.out.println("2. Double  : $100");
        System.out.println("3. Sweet   : $150");
        System.out.println("4. Luxury  : $300");
    }

    public void showRooms(LocalDate in, LocalDate out) {

        System.out.println("\n--- ROOM STATUS ---");

        for (Room r : rooms) {

            boolean available =
                    isAvailable(r.id, in, out);

            if (available)
                System.out.println(
                        "Room ID: " + r.id +
                                " → AVAILABLE");

            else
                System.out.println(
                        "Room ID: " + r.id +
                                " → BOOKED");
        }
    }

    public boolean isAvailable(
            int roomId,
            LocalDate in,
            LocalDate out) {

        // ===== CHECK ARRAYLIST =====

        for (Booking b : bookings) {

            for (Room r : b.rooms) {

                if (r.id == roomId &&
                        !(out.isBefore(b.in)
                                || in.isAfter(b.out))) {

                    return false;
                }
            }
        }

        // ===== CHECK DATABASE =====

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM bookings " +
                            "WHERE room_id = ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, roomId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                LocalDate dbIn =
                        rs.getDate("check_in")
                                .toLocalDate();

                LocalDate dbOut =
                        rs.getDate("check_out")
                                .toLocalDate();

                // overlap check
                if (!(out.isBefore(dbIn)
                        || in.isAfter(dbOut))) {

                    return false;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return true;
    }

    public int getValidInt(
            Scanner sc,
            String msg) {

        while (true) {

            System.out.print(msg);

            if (sc.hasNextInt()) {
                return sc.nextInt();
            }

            else {

                System.out.println(
                        "Invalid input! Enter number.");

                sc.next();
            }
        }
    }

    // ================= BOOKING =================

    public void book(
            String name,
            LocalDate in,
            LocalDate out,
            Payment payment,
            Scanner sc) {

        Booking b =
                new Booking(name, in, out);

        int n =
                getValidInt(sc,
                        "\nHow many rooms? ");

        for (int i = 0; i < n; i++) {

            int roomId =
                    getValidInt(sc,
                            "Enter Room ID: ");

            if (!isAvailable(roomId, in, out)) {

                System.out.println(
                        "Room not available!");

                i--;
                continue;
            }

            showRoomTypes();

            int t =
                    getValidInt(sc,
                            "Select type: ");

            String type =
                    (t == 1) ? "Single"
                            : (t == 2) ? "Double"
                            : (t == 3) ? "Sweet"
                            : (t == 4) ? "Luxury"
                            : "";

            if (type.equals("")) {

                System.out.println("Invalid type!");

                i--;
                continue;
            }

            Room newRoom =
                    new Room(roomId, type);

            b.addRoom(newRoom);
        }

        // ========= FREE SERVICES =========

        b.addService(
                new Service(
                        "WiFi", 0, true));

        b.addService(
                new Service(
                        "Parking", 0, true));

        b.addService(
                new Service(
                        "Welcome Drink", 0, true));

        // ========= EXTRA SERVICES =========

        System.out.println(
                "\n--- EXTRA ACTIVITIES / SERVICES ---");

        System.out.println(
                "1. Swimming Pool ($20)");

        System.out.println(
                "2. Gym Access ($15)");

        System.out.println(
                "3. Indoor Sports ($25)");

        System.out.println(
                "4. Breakfast Buffet ($10)");

        System.out.println(
                "5. Lunch Buffet ($20)");

        System.out.println(
                "6. Dinner Buffet ($25)");

        System.out.println(
                "7. Airport Pickup ($30)");

        System.out.println(
                "8. Done");

        while (true) {

            int c =
                    getValidInt(sc,
                            "Select service: ");

            if (c == 8)
                break;

            if (c == 1)
                b.addService(
                        new Service(
                                "Swimming Pool",
                                20,
                                false));

            else if (c == 2)
                b.addService(
                        new Service(
                                "Gym Access",
                                15,
                                false));

            else if (c == 3)
                b.addService(
                        new Service(
                                "Indoor Sports",
                                25,
                                false));

            else if (c == 4)
                b.addService(
                        new Service(
                                "Breakfast Buffet",
                                10,
                                false));

            else if (c == 5)
                b.addService(
                        new Service(
                                "Lunch Buffet",
                                20,
                                false));

            else if (c == 6)
                b.addService(
                        new Service(
                                "Dinner Buffet",
                                25,
                                false));

            else if (c == 7)
                b.addService(
                        new Service(
                                "Airport Pickup",
                                30,
                                false));

            else
                System.out.println(
                        "Invalid choice!");
        }

        System.out.println(
                "\nTotal Bill = $" + b.total());

        payment.pay(b.total());

        bookings.add(b);

        // ========= SAVE TO DATABASE =========

        saveBookingToDB(b);

        System.out.println(
                "\nBooking Confirmed! ID: "
                        + b.id);

        b.invoice();
    }

    // ================= CANCEL =================

    public void cancel(int id) {

        boolean found = false;

        for (Booking b : bookings) {

            if (b.id == id) {

                found = true;

                // delete booked rooms from DB
                for (Room r : b.rooms) {

                    deleteBookingFromDB(r.id);
                }

                bookings.remove(b);

                System.out.println(
                        "Booking cancelled!");

                break;
            }
        }

        if (!found) {

            System.out.println(
                    "Booking ID not found!");
        }
    }

    // ================= REVENUE =================

    public void revenue() {

        double total = 0;

        for (Booking b : bookings) {

            total += b.total();
        }

        System.out.println(
                "Total Revenue: $" + total);
    }

    // ================= SEARCH =================

    public void searchByRoom(int roomId) {

        boolean found = false;

        for (Booking b : bookings) {

            for (Room r : b.rooms) {

                if (r.id == roomId) {

                    System.out.println(
                            "\n--- BOOKING FOUND ---");

                    System.out.println(
                            "Guest Name : "
                                    + b.guestName);

                    System.out.println(
                            "Room Type  : "
                                    + r.type);

                    System.out.println(
                            "Room ID    : "
                                    + r.id);

                    System.out.println(
                            "Stay       : "
                                    + b.in +
                                    " to " +
                                    b.out);

                    System.out.println(
                            "Total Bill : $"
                                    + b.total());

                    found = true;
                }
            }
        }

        if (!found) {

            System.out.println(
                    "No booking found!");
        }
    }

    // ================= FILE HANDLING =================

    public void saveToFile() {

        try {

            java.io.FileWriter fw =
                    new java.io.FileWriter(
                            "bookings.txt");

            for (Booking b : bookings) {

                fw.write(
                        "Booking ID: "
                                + b.id + "\n");

                fw.write(
                        "Guest Name: "
                                + b.guestName + "\n");

                for (Room r : b.rooms) {

                    fw.write(
                            "Room: "
                                    + r.type
                                    + " | ID: "
                                    + r.id + "\n");
                }

                fw.write(
                        "----------------------\n");
            }

            fw.close();

            System.out.println(
                    "Bookings saved to file!");

        }

        catch (Exception e) {

            System.out.println(
                    "Error saving file!");
        }
    }

    // ================= DATABASE SAVE =================

    public void saveBookingToDB(Booking b) {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String query =
                    "INSERT INTO bookings " +
                            "(guest_name, room_id, room_type, check_in, check_out) " +
                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            for (Room r : b.rooms) {

                ps.setString(
                        1,
                        b.guestName);

                ps.setInt(
                        2,
                        r.id);

                ps.setString(
                        3,
                        r.type);

                ps.setDate(
                        4,
                        java.sql.Date.valueOf(b.in));

                ps.setDate(
                        5,
                        java.sql.Date.valueOf(b.out));

                ps.executeUpdate();
            }

            System.out.println(
                    "Booking saved to database!");

        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= DATABASE DELETE =================

    public void deleteBookingFromDB(int roomId) {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String query =
                    "DELETE FROM bookings WHERE room_id = ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, roomId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Booking deleted from database!");
            }

            else {

                System.out.println(
                        "No booking found!");
            }

        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void loadBookingsFromDB() {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM bookings";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                String guestName =
                        rs.getString("guest_name");

                int roomId =
                        rs.getInt("room_id");

                String roomType =
                        rs.getString("room_type");

                LocalDate in =
                        rs.getDate("check_in")
                                .toLocalDate();

                LocalDate out =
                        rs.getDate("check_out")
                                .toLocalDate();

                Booking b =
                        new Booking(
                                guestName,
                                in,
                                out);

                Room r =
                        new Room(
                                roomId,
                                roomType);

                b.addRoom(r);

                bookings.add(b);
            }

            System.out.println(
                    "Bookings loaded from database!");

        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

}