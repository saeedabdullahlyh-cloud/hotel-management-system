package com.hotel.service;
import java.io.PrintWriter;
import java.sql.DriverManager;
import com.hotel.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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


// ================= CONSTRUCTOR =================

    public Hotel() {

        loadBookingsFromDB();

        loadRevenue();
    }

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
                "\n=========== ROOM STATUS ===========\n"
        );

        for (int i = 1; i <= 20; i++) {

            boolean available =
                    isAvailable(i);

            if (available) {

                System.out.println(
                        "Room ID "
                                + i
                                + " → AVAILABLE"
                );
            }

            else {

                System.out.println(
                        "Room ID "
                                + i
                                + " → BOOKED"
                );
            }
        }
    }
    // ================= AVAILABILITY =================

    // ================= CHECK ROOM AVAILABILITY =================
    public boolean isAvailable(int roomId) {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM bookings WHERE room_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, roomId);

            ResultSet rs =
                    ps.executeQuery();

            boolean booked = rs.next();

            con.close();

            return !booked;
        }

        catch(Exception e) {

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

            String guestName,

            String cnic,

            String phone,

            String email,

            String address,

            LocalDate in,

            LocalDate out,

            Payment payment,

            Scanner sc
    ){


        Booking b =

                new Booking(

                        guestName,

                        cnic,

                        phone,

                        email,

                        address,

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

            if(!isAvailable(roomId))
             {

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


    // ================= CANCEL ROOM =================

    // ================= CANCEL ROOM =================
    public boolean cancel(int roomId) {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            // ================= CHECK ROOM =================

            PreparedStatement check =
                    con.prepareStatement(

                            "SELECT * FROM bookings WHERE room_id=?"
                    );

            check.setInt(1, roomId);

            ResultSet rs =
                    check.executeQuery();

            if(!rs.next()) {

                System.out.println("Room Not Found");

                return false;
            }

            // ================= SAVE HISTORY =================

            PreparedStatement history =
                    con.prepareStatement(

                            "INSERT INTO booking_history " +
                                    "(guest_name, cnic, phone, email, address, room_id, room_type, check_in, check_out, total_days, grand_total, payment_method, transaction_id, status) " +
                                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    );

            history.setString(1, rs.getString("guest_name"));
            history.setString(2, rs.getString("cnic"));
            history.setString(3, rs.getString("phone"));
            history.setString(4, rs.getString("email"));
            history.setString(5, rs.getString("address"));
            history.setInt(6, rs.getInt("room_id"));
            history.setString(7, rs.getString("room_type"));
            history.setDate(8, rs.getDate("check_in"));
            history.setDate(9, rs.getDate("check_out"));
            history.setInt(10, rs.getInt("total_days"));
            history.setDouble(11, rs.getDouble("grand_total"));
            history.setString(12, "Cash");
            history.setString(13, "TXN" + System.currentTimeMillis());
            history.setString(14, "CANCELLED");

            history.executeUpdate();

            // ================= DELETE CHILD TABLES FIRST =================

            PreparedStatement deletePayment =
                    con.prepareStatement(

                            "DELETE FROM payments " +
                                    "WHERE booking_id IN " +
                                    "(SELECT booking_id FROM booking_details WHERE room_id=?)"
                    );

            deletePayment.setInt(1, roomId);

            deletePayment.executeUpdate();

            PreparedStatement deleteDetails =
                    con.prepareStatement(

                            "DELETE FROM booking_details WHERE room_id=?"
                    );

            deleteDetails.setInt(1, roomId);

            deleteDetails.executeUpdate();

            // ================= DELETE MAIN BOOKING =================

            PreparedStatement deleteBooking =
                    con.prepareStatement(

                            "DELETE FROM bookings WHERE room_id=?"
                    );

            deleteBooking.setInt(1, roomId);

            deleteBooking.executeUpdate();

            // ================= DELETE CUSTOMER =================

            PreparedStatement deleteCustomer =
                    con.prepareStatement(

                            "DELETE FROM customers " +
                                    "WHERE customer_id NOT IN " +
                                    "(SELECT customer_id FROM booking_details)"
                    );

            deleteCustomer.executeUpdate();
            //===================================================
                        PreparedStatement deleteRoom =
                    con.prepareStatement(
                            "DELETE FROM rooms WHERE room_id=?"
                    );

            deleteRoom.setInt(1, roomId);

            deleteRoom.executeUpdate();


            // ================= ROOM AVAILABLE =================

            PreparedStatement updateRoom =
                    con.prepareStatement(

                            "UPDATE rooms " +
                                    "SET status='AVAILABLE' " +
                                    "WHERE room_id=?"
                    );

            updateRoom.setInt(1, roomId);

            updateRoom.executeUpdate();

            con.close();

            // ================= MEMORY REFRESH =================

            bookings.clear();

            loadBookingsFromDB();

            System.out.println("Room Cancelled Successfully!");

            return true;
        }

        catch(Exception e) {

            e.printStackTrace();

            return false;
        }
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
//--------------load revenue----------------------------
public void loadRevenue() {

    try {

        java.io.File file =
                new java.io.File(
                        "revenue.txt"
                );

        if(!file.exists()) {

            return;
        }

        Scanner sc =
                new Scanner(file);

        if(sc.hasNextDouble()) {

            totalRevenue =
                    sc.nextDouble();
        }

        sc.close();

        System.out.println(
                "Revenue loaded!"
        );
    }

    catch (Exception e) {

        e.printStackTrace();
    }
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
                String cnic =
                        rs.getString(
                                "cnic"
                        );

                String phone =
                        rs.getString(
                                "phone"
                        );

                String email =
                        rs.getString(
                                "email"
                        );

                String address =
                        rs.getString(
                                "address"
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
                        "CNIC : "
                                + cnic
                                + "\n"
                );

                sb.append(
                        "Phone : "
                                + phone
                                + "\n"
                );

                sb.append(
                        "Email : "
                                + email
                                + "\n"
                );

                sb.append(
                        "Address : "
                                + address
                                + "\n\n"
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
    // ================= SEARCH CUSTOMER =================

    public String searchCustomer(
            String keyword
    ) {

        StringBuilder sb =
                new StringBuilder();

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String query =

                    "SELECT * FROM bookings " +

                            "WHERE guest_name LIKE ? " +

                            "OR cnic LIKE ? " +

                            "OR phone LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(
                    1,
                    "%" + keyword + "%"
            );

            ps.setString(
                    2,
                    "%" + keyword + "%"
            );

            ps.setString(
                    3,
                    "%" + keyword + "%"
            );

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;

            while(rs.next()) {

                found = true;

                sb.append(
                        "\n=========== CUSTOMER RECORD ===========\n\n"
                );

                sb.append(
                        "Customer Name : "
                                + rs.getString("guest_name")
                                + "\n"
                );

                sb.append(
                        "CNIC : "
                                + rs.getString("cnic")
                                + "\n"
                );

                sb.append(
                        "Phone : "
                                + rs.getString("phone")
                                + "\n"
                );

                sb.append(
                        "Email : "
                                + rs.getString("email")
                                + "\n"
                );

                sb.append(
                        "Address : "
                                + rs.getString("address")
                                + "\n"
                );

                sb.append(
                        "Room ID : "
                                + rs.getInt("room_id")
                                + "\n"
                );

                sb.append(
                        "Room Type : "
                                + rs.getString("room_type")
                                + "\n"
                );

                sb.append(
                        "Check-In : "
                                + rs.getDate("check_in")
                                + "\n"
                );

                sb.append(
                        "Check-Out : "
                                + rs.getDate("check_out")
                                + "\n\n"
                );
            }

            con.close();

            if(!found) {

                return "No Customer Found!";
            }
        }

        catch (Exception e) {

            return e.getMessage();
        }

        return sb.toString();
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
// ================= CLEAR REVENUE =================

    public void clearRevenue() {

        totalRevenue = 0;

        try {

            PrintWriter pw =
                    new PrintWriter(
                            "revenue.txt"
                    );

            pw.print("0");

            pw.close();
        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
    // ================= DATABASE SAVE =================
    public void saveBookingToDB(
            Booking booking
    ) {

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String query =

                    "INSERT INTO bookings " +

                            "(guest_name, cnic, phone, email, address, room_id, room_type, check_in, check_out, room_price, total_days, grand_total) " +

                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps =
                    con.prepareStatement(query);

            for(Room room : booking.rooms) {

                ps.setString(
                        1,
                        booking.guestName
                );

                ps.setString(
                        2,
                        booking.cnic
                );

                ps.setString(
                        3,
                        booking.phone
                );

                ps.setString(
                        4,
                        booking.email
                );

                ps.setString(
                        5,
                        booking.address
                );

                ps.setInt(
                        6,
                        room.id
                );

                ps.setString(
                        7,
                        room.type
                );

                ps.setDate(
                        8,
                        java.sql.Date.valueOf(
                                booking.in
                        )
                );

                ps.setDate(
                        9,
                        java.sql.Date.valueOf(
                                booking.out
                        )
                );
                ps.setDouble(
                        10,
                        room.price
                );

                ps.setInt(
                        11,
                        (int) booking.days()
                );

                ps.setDouble(
                        12,
                        booking.total()
                );

                ps.executeUpdate();
            }

            con.close();

        }

        catch (Exception e) {

            System.out.println(e);
        }
    }
    // ================= SAVE CUSTOMER =================

    public int saveCustomer(

            Booking booking
    ) {

        int customerId = -1;

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String query =

                    "INSERT INTO customers " +

                            "(guest_name, cnic, phone, email, address) " +

                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(

                            query,

                            Statement.RETURN_GENERATED_KEYS
                    );

            ps.setString(
                    1,
                    booking.guestName
            );

            ps.setString(
                    2,
                    booking.cnic
            );

            ps.setString(
                    3,
                    booking.phone
            );

            ps.setString(
                    4,
                    booking.email
            );

            ps.setString(
                    5,
                    booking.address
            );

            ps.executeUpdate();

            ResultSet rs =
                    ps.getGeneratedKeys();

            if(rs.next()) {

                customerId =
                        rs.getInt(1);
            }

            con.close();
        }

        catch(Exception e) {

            e.printStackTrace();
        }

        return customerId;
    }
// ================= SAVE BOOKING DETAILS =================

    // ================= SAVE BOOKING DETAILS =================
    public int saveBookingDetails(

            int customerId,

            Booking booking
    ) {

        int bookingId = -1;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            for(Room room : booking.rooms) {

                String query =

                        "INSERT INTO booking_details " +

                                "(customer_id, room_id, check_in, check_out, total_days, grand_total) " +

                                "VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(

                                query,

                                Statement.RETURN_GENERATED_KEYS
                        );

                ps.setInt(
                        1,
                        customerId
                );

                ps.setInt(
                        2,
                        room.id
                );

                ps.setDate(
                        3,
                        java.sql.Date.valueOf(
                                booking.in
                        )
                );

                ps.setDate(
                        4,
                        java.sql.Date.valueOf(
                                booking.out
                        )
                );

                ps.setInt(
                        5,
                        (int) booking.days()
                );

                ps.setDouble(
                        6,
                        booking.total()
                );

                ps.executeUpdate();

                ResultSet rs =
                        ps.getGeneratedKeys();

                if(rs.next()) {

                    bookingId =
                            rs.getInt(1);
                }
            }

            con.close();
        }

        catch(Exception e) {

            e.printStackTrace();
        }

        return bookingId;
    }
    public void saveRoom(Room room) {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            // CHECK ROOM EXISTS
            PreparedStatement check =
                    con.prepareStatement(

                            "SELECT * FROM rooms WHERE room_id=?"
                    );

            check.setInt(1, room.id);

            ResultSet rs =
                    check.executeQuery();

            // IF ROOM EXISTS -> UPDATE
            if(rs.next()) {

                PreparedStatement update =
                        con.prepareStatement(

                                "UPDATE rooms " +
                                        "SET room_type=?, room_price=?, status=? " +
                                        "WHERE room_id=?"
                        );

                update.setString(1, room.type);

                update.setDouble(2, room.price);

                update.setString(3, "BOOKED");

                update.setInt(4, room.id);

                update.executeUpdate();
            }

            // OTHERWISE INSERT
            else {

                PreparedStatement insert =
                        con.prepareStatement(

                                "INSERT INTO rooms " +
                                        "(room_id, room_type, room_price, status) " +
                                        "VALUES (?, ?, ?, ?)"
                        );

                insert.setInt(1, room.id);

                insert.setString(2, room.type);

                insert.setDouble(3, room.price);

                insert.setString(4, "BOOKED");

                insert.executeUpdate();
            }

            con.close();
        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
    // ================= SAVE PAYMENT =================

    public void savePayment(

            int bookingId,

            String paymentMethod,

            String transactionId,

            double amount
    ) {

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String query =

                    "INSERT INTO payments " +

                            "(booking_id, payment_method, transaction_id, payment_status, amount) " +

                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(
                    1,
                    bookingId
            );

            ps.setString(
                    2,
                    paymentMethod
            );

            ps.setString(
                    3,
                    transactionId
            );

            ps.setString(
                    4,
                    "PAID"
            );

            ps.setDouble(
                    5,
                    amount
            );

            ps.executeUpdate();

            con.close();
        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
    // ================= SAVE ROOM =================



    // ================= SAVE HISTORY =================

    public void saveBookingHistory(
            int roomId
    ) {

        try {

            Connection con =
                    DatabaseConnection
                            .getConnection();

            String selectQuery =

                    "SELECT * FROM bookings " +

                            "WHERE room_id=?";

            PreparedStatement selectPs =
                    con.prepareStatement(
                            selectQuery
                    );

            selectPs.setInt(
                    1,
                    roomId
            );

            ResultSet rs =
                    selectPs.executeQuery();

            while(rs.next()) {

                String insertQuery =

                        "INSERT INTO booking_history " +

                                "(guest_name, cnic, phone, email, address, room_id, room_type, check_in, check_out, total_days, grand_total, payment_method, transaction_id, status) " +

                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement insertPs =
                        con.prepareStatement(
                                insertQuery
                        );

                insertPs.setString(
                        1,
                        rs.getString("guest_name")
                );

                insertPs.setString(
                        2,
                        rs.getString("cnic")
                );

                insertPs.setString(
                        3,
                        rs.getString("phone")
                );

                insertPs.setString(
                        4,
                        rs.getString("email")
                );

                insertPs.setString(
                        5,
                        rs.getString("address")
                );

                insertPs.setInt(
                        6,
                        rs.getInt("room_id")
                );

                insertPs.setString(
                        7,
                        rs.getString("room_type")
                );

                insertPs.setDate(
                        8,
                        rs.getDate("check_in")
                );

                insertPs.setDate(
                        9,
                        rs.getDate("check_out")
                );

                insertPs.setInt(
                        10,
                        rs.getInt("total_days")
                );

                insertPs.setDouble(
                        11,
                        rs.getDouble("grand_total")
                );

                insertPs.setString(
                        12,
                        "Cash"
                );

                insertPs.setString(
                        13,
                        "Archived"
                );

                insertPs.setString(
                        14,
                        "CANCELLED"
                );

                insertPs.executeUpdate();
            }

            con.close();
        }

        catch(Exception e) {

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
        bookings.clear();
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
                String cnic =
                        rs.getString(
                                "cnic"
                        );

                String phone =
                        rs.getString(
                                "phone"
                        );

                String email =
                        rs.getString(
                                "email"
                        );

                String address =
                        rs.getString(
                                "address"
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

                                cnic,

                                phone,

                                email,

                                address,

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

            if(isAvailable(i)) {

                return i;
            }
        }

        return -1;
    }

// ================= GUI BOOK ROOM =================

    public void guiBookRoom(

            String customerName,

            String cnic,

            String phone,

            String email,

            String address,

            LocalDate in,

            LocalDate out,

            List<Room> selectedRooms,

            List<Service> selectedServices
    ) {

        try {

            Booking booking =
                    new Booking(

                            customerName,

                            cnic,

                            phone,

                            email,

                            address,

                            in,

                            out
                    );

            // ========= ADD ROOMS =========

            // ========= ADD ROOMS =========

            for(Room r : selectedRooms) {

                if(!isAvailable(r.id)) {

                    throw new RuntimeException(
                            "Room ID " + r.id + " already booked!"
                    );
                }



                    booking.addRoom(r);

                    // SAVE ROOM IN SQL
                    saveRoom(r);

                    // UPDATE STATUS
                    updateRoomStatus(
                            r.id,
                            "BOOKED"
                    );
                }

            // ========= SERVICES =========

            for(Service s : selectedServices) {

                booking.addService(s);
            }

            bookings.add(booking);

            addRevenue(
                    booking.total()
            );

            // =====================================
            // SAVE CUSTOMER
            // =====================================

            int customerId =
                    saveCustomer(booking);

            // =====================================
            // SAVE BOOKING DETAILS
            // =====================================

            int bookingId =
                    saveBookingDetails(
                            customerId,
                            booking
                    );

            // =====================================
            // SAVE PAYMENT
            // =====================================

            savePayment(

                    bookingId,

                    "Cash",

                    "TXN" + System.currentTimeMillis(),

                    booking.total()
            );

            // =====================================
            // SAVE BOOKINGS TABLE
            // =====================================

            saveBookingToDB(booking);
            refreshRoomStatus();
            saveToFile();

            saveRevenue();

            System.out.println(
                    "Booking Saved Successfully!"
            );
        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
// ================= BOOKED ROOM COUNT =================

    public int getBookedRoomCount() {

        return bookings.size();
    }

// ================= AVAILABLE ROOM COUNT =================

    public int getAvailableRoomCount() {

        return 20 - bookings.size();
    }

// ================= OCCUPANCY RATE =================

    public double getOccupancyRate() {

        return (

                getBookedRoomCount()

                        / 20.0

        ) * 100;
    }
    // ================= RECENT BOOKINGS =================

    public String getRecentBookings() {

        StringBuilder sb =
                new StringBuilder();

        int count = 0;

        for(int i = bookings.size() - 1;

            i >= 0;

            i--) {

            Booking b =
                    bookings.get(i);

            for(Room r : b.rooms) {

                sb.append(

                        b.guestName

                                + " → Room "

                                + r.id

                                + "\n"
                );

                count++;

                if(count == 5) {

                    return sb.toString();
                }
            }
        }

        if(sb.length() == 0) {

            return "No Recent Bookings";
        }

        return sb.toString();
    }
    public double getRevenue() {

        return totalRevenue;
    }
    public void updateRoomStatus(

            int roomId,

            String status
    ) {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String query =
                    "UPDATE rooms SET status=? WHERE room_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(
                    1,
                    status
            );

            ps.setInt(
                    2,
                    roomId
            );

            ps.executeUpdate();

            con.close();
        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
    public void refreshRoomStatus() {

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            // FIRST ALL AVAILABLE
            PreparedStatement reset =
                    con.prepareStatement(

                            "UPDATE rooms SET status='AVAILABLE'"
                    );

            reset.executeUpdate();

            // NOW BOOKED ROOMS
            PreparedStatement booked =
                    con.prepareStatement(

                            "UPDATE rooms r " +
                                    "JOIN bookings b " +
                                    "ON r.room_id = b.room_id " +
                                    "SET r.status='BOOKED'"
                    );

            booked.executeUpdate();

            con.close();
        }

        catch(Exception e) {

            e.printStackTrace();
        }

    }
}