package com.hotel.service;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.Booking;
import com.hotel.model.Payment;
import com.hotel.model.Room;
import com.hotel.model.Service;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {

    // ================= DATA =================

    List<Room> rooms =
            new ArrayList<>();

    public List<Booking> bookings =
            new ArrayList<>();

    private double totalRevenue = 0;

    // ================= ADD ROOM =================

    public void addRoom(Room r) {

        rooms.add(r);
    }

    // ================= ROOM TYPES =================

    public void showRoomTypes() {

        System.out.println("\n--- ROOM TYPES ---");

        System.out.println("1. Single  : $50");

        System.out.println("2. Double  : $100");

        System.out.println("3. Sweet   : $150");

        System.out.println("4. Luxury  : $300");
    }

    // ================= SHOW ROOMS =================

    public void showRooms(
            LocalDate in,
            LocalDate out
    ) {

        System.out.println(
                "\n--- ROOM STATUS ---"
        );

        for (int i = 1; i <= 20; i++) {

            boolean available =
                    isAvailable(
                            i,
                            in,
                            out
                    );

            if (available) {

                System.out.println(
                        "Room ID: "
                                + i
                                + " → AVAILABLE"
                );
            }

            else {

                System.out.println(
                        "Room ID: "
                                + i
                                + " → BOOKED"
                );
            }
        }
    }

    // ================= AVAILABILITY =================

    public boolean isAvailable(

            int roomId,

            LocalDate in,

            LocalDate out
    ) {

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
                    DatabaseConnection
                            .getConnection();

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
                        rs.getDate(
                                "check_in"
                        ).toLocalDate();

                LocalDate dbOut =
                        rs.getDate(
                                "check_out"
                        ).toLocalDate();

                if (!(out.isBefore(dbIn)
                        || in.isAfter(dbOut))) {

                    return false;
                }
            }
        }

        catch (Exception e) {

            e.printStackTrace();
        }

        return true;
    }

    // ================= VALID INTEGER =================

    public int getValidInt(
            Scanner sc,
            String msg
    ) {

        while (true) {

            System.out.print(msg);

            if (sc.hasNextInt()) {

                return sc.nextInt();
            }

            else {

                System.out.println(
                        "Invalid input!"
                );

                sc.next();
            }
        }
    }

    // ================= BOOK ROOM =================

    public void book(

            String name,

            LocalDate in,

            LocalDate out,

            Payment payment,

            Scanner sc
    ) {

        Booking b =
                new Booking(
                        name,
                        in,
                        out
                );

        int n =
                getValidInt(
                        sc,
                        "\nHow many rooms? "
                );

        for (int i = 0; i < n; i++) {

            int roomId =
                    getValidInt(
                            sc,
                            "Enter Room ID: "
                    );

            if (!isAvailable(
                    roomId,
                    in,
                    out
            )) {

                System.out.println(
                        "Room not available!"
                );

                i--;

                continue;
            }

            showRoomTypes();

            int t =
                    getValidInt(
                            sc,
                            "Select type: "
                    );

            String type =
                    (t == 1) ? "Single"
                            : (t == 2) ? "Double"
                            : (t == 3) ? "Sweet"
                            : (t == 4) ? "Luxury"
                            : "";

            if (type.equals("")) {

                System.out.println(
                        "Invalid type!"
                );

                i--;

                continue;
            }

            Room newRoom =
                    new Room(
                            roomId,
                            type
                    );

            b.addRoom(newRoom);
        }

        // ================= FREE SERVICES =================

        b.addService(
                new Service(
                        "WiFi",
                        0,
                        true
                )
        );

        b.addService(
                new Service(
                        "Parking",
                        0,
                        true
                )
        );

        b.addService(
                new Service(
                        "Welcome Drink",
                        0,
                        true
                )
        );

        // ================= EXTRA SERVICES =================

        System.out.println(
                "\n--- EXTRA ACTIVITIES ---"
        );

        System.out.println(
                "1. Swimming Pool ($20)"
        );

        System.out.println(
                "2. Gym Access ($15)"
        );

        System.out.println(
                "3. Indoor Sports ($25)"
        );

        System.out.println(
                "4. Breakfast Buffet ($10)"
        );

        System.out.println(
                "5. Lunch Buffet ($20)"
        );

        System.out.println(
                "6. Dinner Buffet ($25)"
        );

        System.out.println(
                "7. Airport Pickup ($30)"
        );

        System.out.println(
                "8. Done"
        );

        while (true) {

            int c =
                    getValidInt(
                            sc,
                            "Select service: "
                    );

            if (c == 8)
                break;

            if (c == 1)

                b.addService(
                        new Service(
                                "Swimming Pool",
                                20,
                                false
                        )
                );

            else if (c == 2)

                b.addService(
                        new Service(
                                "Gym Access",
                                15,
                                false
                        )
                );

            else if (c == 3)

                b.addService(
                        new Service(
                                "Indoor Sports",
                                25,
                                false
                        )
                );

            else if (c == 4)

                b.addService(
                        new Service(
                                "Breakfast Buffet",
                                10,
                                false
                        )
                );

            else if (c == 5)

                b.addService(
                        new Service(
                                "Lunch Buffet",
                                20,
                                false
                        )
                );

            else if (c == 6)

                b.addService(
                        new Service(
                                "Dinner Buffet",
                                25,
                                false
                        )
                );

            else if (c == 7)

                b.addService(
                        new Service(
                                "Airport Pickup",
                                30,
                                false
                        )
                );

            else {

                System.out.println(
                        "Invalid choice!"
                );
            }
        }

        System.out.println(
                "\nTotal Bill = $"
                        + b.total()
        );

        payment.pay(
                b.total()
        );

        bookings.add(b);

        addRevenue(
                b.total()
        );

        saveBookingToDB(b);

        saveToFile();

        saveRevenue();

        System.out.println(
                "\nRoom Booking Confirmed!"
        );
        b.invoice();
    }

    // ================= CANCEL =================

    // ================= CANCEL ROOM =================

    public boolean cancel(int roomId) {

        Booking removeBooking = null;

        for(Booking b : bookings) {

            for(Room r : b.rooms) {

                if(r.id == roomId) {

                    removeBooking = b;

                    deleteBookingFromDB(
                            roomId
                    );

                    break;
                }
            }
        }

        if(removeBooking != null) {

            bookings.remove(
                    removeBooking
            );

            saveToFile();

            saveRevenue();

            return true;
        }

        return false;
    }
    // ================= REVENUE =================

    public void addRevenue(
            double amount
    ) {

        totalRevenue += amount;
    }

    public double getTotalRevenue() {

        return totalRevenue;
    }

    public void revenue() {

        System.out.println(
                "Total Revenue: $"
                        + totalRevenue
        );
    }

    // ================= SEARCH ROOM =================

    public String searchByRoom(int roomId) {

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String query =
                    "SELECT * FROM bookings WHERE room_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, roomId);

            ResultSet rs =
                    ps.executeQuery();

            StringBuilder sb =
                    new StringBuilder();

            boolean found = false;

            while(rs.next()) {

                found = true;

                String guestName =
                        rs.getString(
                                "guest_name"
                        );

                String roomType =
                        rs.getString(
                                "room_type"
                        );

                LocalDate in =
                        rs.getDate(
                                "check_in"
                        ).toLocalDate();

                LocalDate out =
                        rs.getDate(
                                "check_out"
                        ).toLocalDate();

                double roomPrice =
                        rs.getDouble(
                                "room_price"
                        );

                int totalDays =
                        rs.getInt(
                                "total_days"
                        );

                double grandTotal =
                        rs.getDouble(
                                "grand_total"
                        );

                sb.append(
                        "=========== HOTEL INVOICE ===========\n\n"
                );

                sb.append(
                        "Customer Name : "
                                + guestName
                                + "\n"
                );

                sb.append(
                        "Room ID : "
                                + roomId
                                + "\n"
                );

                sb.append(
                        "Room Type : "
                                + roomType
                                + "\n"
                );

                sb.append(
                        "Stay : "
                                + in
                                + " to "
                                + out
                                + "\n\n"
                );

                sb.append(
                        "=========== ROOM DETAILS ===========\n\n"
                );

                sb.append(
                        "Price Per Day : $"
                                + roomPrice
                                + "\n"
                );

                sb.append(
                        "Total Days : "
                                + totalDays
                                + "\n"
                );

                sb.append(
                        "Room Charges : $"
                                + (roomPrice * totalDays)
                                + "\n\n"
                );

                sb.append(
                        "=========== COMPLIMENTARY ===========\n\n"
                );

                sb.append(
                        "Welcome Drinks : FREE\n"
                );

                sb.append(
                        "Free WiFi : FREE\n"
                );

                sb.append(
                        "Free Parking : FREE\n"
                );

                sb.append(
                        "Room Cleaning : FREE\n\n"
                );

                sb.append(
                        "=========== GRAND TOTAL ===========\n\n"
                );

                sb.append(
                        "Grand Total : $"
                                + grandTotal
                                + "\n"
                );

                sb.append(
                        "\n===================================="
                );
            }

            con.close();

            if(found) {

                return sb.toString();
            }

        }

        catch(Exception e) {

            e.printStackTrace();
        }

        return "Room ID " + roomId + " is AVAILABLE";
    }
    // ================= FILE SAVE =================

    public void saveToFile() {

        try {

            java.io.FileWriter fw =
                    new java.io.FileWriter(
                            "bookings.txt"
                    );

            for (Booking b : bookings) {



                fw.write(
                        "Guest Name: "
                                + b.guestName
                                + "\n"
                );

                for (Room r : b.rooms) {

                    fw.write(
                            "Room: "
                                    + r.type
                                    + " | ID: "
                                    + r.id
                                    + "\n"
                    );
                }

                fw.write(
                        "----------------------\n"
                );
            }

            fw.close();

            System.out.println(
                    "Bookings saved!"
            );
        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= SAVE REVENUE =================

    public void saveRevenue() {

        try {

            java.io.FileWriter fw =
                    new java.io.FileWriter(
                            "revenue.txt"
                    );

            fw.write(
                    String.valueOf(
                            totalRevenue
                    )
            );

            fw.close();

            System.out.println(
                    "Revenue saved!"
            );
        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= DATABASE SAVE =================

    public void saveBookingToDB(
            Booking b
    ) {

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();
            String query =

                    "INSERT INTO bookings " +

                            "(room_id, guest_name, room_type, check_in, check_out, room_price, total_days, grand_total) " +

                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps =
                    con.prepareStatement(query);

            for (Room r : b.rooms) {

                long totalDays =
                        b.days();

                double grandTotal =
                        (r.price * totalDays);

                ps.setInt(1, r.id);

                ps.setString(2, b.guestName);

                ps.setString(3, r.type);

                ps.setDate(
                        4,
                        java.sql.Date.valueOf(b.in)
                );

                ps.setDate(
                        5,
                        java.sql.Date.valueOf(b.out)
                );

                ps.setDouble(6, r.price);

                ps.setInt(
                        7,
                        (int) totalDays
                );

                ps.setDouble(
                        8,
                        grandTotal
                );
                ps.executeUpdate();
            }

            System.out.println(
                    "Booking saved to DB!"
            );
        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= DATABASE DELETE =================

    public void deleteBookingFromDB(
            int roomId
    ) {

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String query =
                    "DELETE FROM bookings " +
                            "WHERE room_id = ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(
                    1,
                    roomId
            );

            ps.executeUpdate();
        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= DATABASE LOAD =================

    public void loadBookingsFromDB() {

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String query =
                    "SELECT * FROM bookings";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                String guestName =
                        rs.getString(
                                "guest_name"
                        );

                int roomId =
                        rs.getInt(
                                "room_id"
                        );

                String roomType =
                        rs.getString(
                                "room_type"
                        );

                LocalDate in =
                        rs.getDate(
                                "check_in"
                        ).toLocalDate();

                LocalDate out =
                        rs.getDate(
                                "check_out"
                        ).toLocalDate();

                Booking b =
                        new Booking(
                                guestName,
                                in,
                                out
                        );

                Room r =
                        new Room(
                                roomId,
                                roomType
                        );

                b.addRoom(r);

                bookings.add(b);
            }

            System.out.println(
                    "Bookings loaded from DB!"
            );
        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }
    // ================= NEXT AVAILABLE ROOM =================

    public int getNextAvailableRoomId(

            LocalDate in,

            LocalDate out
    ) {

        for(int i = 1; i <= 20; i++) {

            if(isAvailable(i, in, out)) {

                return i;
            }
        }

        return -1;
    }

// ================= GUI BOOK ROOM =================

    public void guiBookRoom(

            String customerName,

            LocalDate in,

            LocalDate out,

            List<Room> selectedRooms,

            List<Service> selectedServices
    ) {

        Booking booking =
                new Booking(
                        customerName,
                        in,
                        out
                );

        // ================= ADD ROOMS =================

        for(Room r : selectedRooms) {

            if(!isAvailable(
                    r.id,
                    in,
                    out
            )) {

                throw new RuntimeException(

                        "Room ID "
                                + r.id
                                + " already booked!"
                );
            }

            booking.addRoom(r);
        }

        // ================= COMPLIMENTARY =================

        booking.addService(

                new Service(
                        "Welcome Drinks",
                        0,
                        true
                )
        );

        booking.addService(

                new Service(
                        "Free WiFi",
                        0,
                        true
                )
        );

        booking.addService(

                new Service(
                        "Free Parking",
                        0,
                        true
                )
        );

        booking.addService(

                new Service(
                        "Room Cleaning",
                        0,
                        true
                )
        );

        // ================= EXTRA SERVICES =================

        for(Service s : selectedServices) {

            booking.addService(s);
        }

        // ================= SAVE =================

        bookings.add(booking);

        addRevenue(
                booking.total()
        );

        saveBookingToDB(
                booking
        );

        saveToFile();

        saveRevenue();
    }



}