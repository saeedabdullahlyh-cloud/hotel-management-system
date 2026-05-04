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
