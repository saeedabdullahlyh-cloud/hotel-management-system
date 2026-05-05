package com.hotel;
import java.util.Scanner;
public class Main {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel h = new Hotel();
        h.addRoom(new Room(1, "Single"));
        h.addRoom(new Room(2, "Double"));
        h.addRoom(new Room(3, "Sweet"));
        h.addRoom(new Room(4, "Luxury"));
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Show Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Exit");
            int ch = h.getValidInt(sc, "Enter choice: ");
            if (ch == 1) {
                h.showRooms();
            }
            else if (ch == 2) {
                h.simpleBooking(sc);
            }
            else if (ch == 3) {
                break;
            }
            else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
