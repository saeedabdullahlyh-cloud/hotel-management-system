package com.hotel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
class Hotel {
    List<Room> rooms=new ArrayList<>();
    List<Booking> bookings=new ArrayList<>();
 public void addRoom(Room r) {
       rooms.add(r);
   }
    public void showRooms() {
        System.out.println("\n--- ALL ROOMS ---");
        for (Room r : rooms) {
            System.out.println("Room ID: " + r.id + " | Type: " + r.type);
     }
    }
    public boolean isAvailable(int roomId, LocalDate in, LocalDate out) {
        for (Booking b : bookings) {
            for (Room r : b.rooms) {
                if (r.id==roomId &&
                        !(out.isBefore(b.in) || in.isAfter(b.out))) {
                    return false;
                }            }
        }
        return true;
    }
    public void addBooking(Booking b) {
        bookings.add(b);
    }}
public int getValidInt(java.util.Scanner sc, String msg) {
    while (true) {
        System.out.print(msg);
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            System.out.println("Invalid input!");
            sc.next();
        }
    }
    public void simpleBooking(java.util.Scanner sc) {
    System.out.print("Enter Name: ");
    sc.nextLine();
    String name = sc.nextLine();
    int roomId = getValidInt(sc, "Enter Room ID: ");
    Room selected = null;
    for (Room r : rooms) {
        if (r.id == roomId) {
            selected = r;
            break;
        }
    }
    if (selected == null) {
        System.out.println("Invalid Room ID!");
        return;
    }
    java.time.LocalDate in=java.time.LocalDate.now();
    java.time.LocalDate out=in.plusDays(1);
    Booking b = new Booking(name, in, out);
    b.addRoom(selected);

    bookings.add(b);

    System.out.println("Booking Done! ID: " + b.id);
}
}
