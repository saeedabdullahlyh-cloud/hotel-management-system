package com.hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        }    }
    public void simpleBooking(java.util.Scanner sc) {
    System.out.print("Enter Name: ");
    sc.nextLine();
    String name=sc.nextLine();
    int roomId=getValidInt(sc, "Enter Room ID: ");
    Room selected=null;
    for (Room r : rooms) {
        if (r.id==roomId) {
            selected=r;
            break;
        }   }
    if (selected == null) {
       System.out.println("Invalid Room ID!");
      return;
   }
   java.time.LocalDate in=java.time.LocalDate.now();
    java.time.LocalDate out=in.plusDays(1);
  Booking b=new Booking(name, in, out);
   b.addRoom(selected);
    bookings.add(b);
  System.out.println("Booking Done! ID: " + b.id);
}}
public void saveToFile() {
    try {
        java.io.FileWriter fw =
               new java.io.FileWriter("bookings.txt");
        for (Booking b : bookings) {
            fw.write("Booking ID: " + b.id + "\n");
            fw.write("Guest Name: " + b.guestName + "\n");
            for (Room r : b.rooms) {
                fw.write("Room: " + r.type +
                       " | ID: " + r.id + "\n");
            }
       fw.write("----------------------\n");
        }
        fw.close();
        System.out.println("Bookings saved to file!");
    } catch (Exception e) {
        System.out.println("Error saving file!");
    }
    public void cancel(int id) {
    boolean removed=bookings.removeIf(b -> b.id == id);
    if (removed) {
        System.out.println("Booking cancelled!");
        saveToFile();
    } else {
        System.out.println("Booking not found!");
    }
    public void searchByRoom(int roomId) {
    boolean found = false;
    for (Booking b : bookings) {
        for (Room r : b.rooms) {
            if (r.id == roomId) {
                System.out.println("\n--- BOOKING FOUND ---");
                System.out.println("Guest Name : " + b.guestName);
                System.out.println("Room Type  : " + r.type);
                System.out.println("Room ID    : " + r.id);
                System.out.println("Stay       : " + b.in + " to " + b.out);
                found = true;
            }   }  }
    if (!found) {
        System.out.println("No booking found!");
    }}


