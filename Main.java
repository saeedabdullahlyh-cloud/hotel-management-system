
package com.hotel;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Hotel h = new Hotel();

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

            int ch = h.getValidInt(sc, "Enter choice: ");

            // SHOW ROOMS
            if (ch == 1) {

                int d1 = h.getValidInt(sc, "Enter check-in day: ");
                int d2 = h.getValidInt(sc, "Enter check-out day: ");

                LocalDate in = LocalDate.of(2026, 5, d1);
                LocalDate out = LocalDate.of(2026, 5, d2);

                h.showRooms(in, out);
            }

            // BOOK ROOM
            else if (ch == 2) {

                int d1 = h.getValidInt(sc, "Check-in day: ");
                int d2 = h.getValidInt(sc, "Check-out day: ");

                if (d2 <= d1) {
                    System.out.println("Invalid dates!");
                    continue;
                }

                LocalDate in = LocalDate.of(2026, 5, d1);
                LocalDate out = LocalDate.of(2026, 5, d2);

                System.out.print("Enter Name: ");
                sc.nextLine();
                String name = sc.nextLine();

                System.out.println("Payment: 1. Cash  2. Card");

                int p = h.getValidInt(sc, "Enter choice: ");

                Payment pay = (p == 1)
                        ? new CashPayment()
                        : new CardPayment();

                h.book(name, in, out, pay, sc);
            }

            // CANCEL BOOKING
            else if (ch == 3) {

                int id = h.getValidInt(sc, "Enter Booking ID: ");

                h.cancel(id);
            }

            // TOTAL REVENUE
            else if (ch == 4) {

                h.revenue();
            }

            // SEARCH BOOKING
            else if (ch == 5) {

                int roomId = h.getValidInt(sc, "Enter Room ID: ");

                h.searchByRoom(roomId);
            }

            // SAVE TO FILE
            else if (ch == 6) {

                h.saveToFile();
            }

            // EXIT
            else if (ch == 7) {

                System.out.println("Exiting system...");

                break;
            }

            else {

                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
```

---

# DatabaseConnection.java

```java
package com.hotel;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {

        try {

            Connection con =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/hotel_db",
                            "root",
                            "YOUR_PASSWORD"
                    );

            return con;

        }

        catch (Exception e) {

            System.out.println("Connection Failed!");

            e.printStackTrace();

            return null;
        }
    }
}
```

---

# Hotel.java EXTRA METHOD

Paste this method INSIDE Hotel.java

```java
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
```
