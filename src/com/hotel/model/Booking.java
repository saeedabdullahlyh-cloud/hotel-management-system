package com.hotel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    public static int counter = 1;

    public int id;

    public String guestName;
    public String cnic;

    public String phone;

    public String email;

    public String address;

    public List<Room> rooms = new ArrayList<>();

    public List<Service> services = new ArrayList<>();

    public LocalDate in, out;

    public Booking(

            String guestName,

            String cnic,

            String phone,

            String email,

            String address,

            LocalDate in,

            LocalDate out
    ) {

        this.id = counter++;

        this.guestName = guestName;

        this.cnic = cnic;

        this.phone = phone;

        this.email = email;

        this.address = address;

        this.in = in;

        this.out = out;
    }
    public long days() {

        return out.toEpochDay()
                - in.toEpochDay();
    }

    public void addRoom(Room r) {

        rooms.add(r);
    }

    public void addService(Service s) {

        services.add(s);
    }

    public double totalRoomCost() {

        double cost = 0;

        for (Room r : rooms) {

            cost += days() * r.price;
        }

        return cost;
    }

    public double serviceCost() {

        double cost = 0;

        for (Service s : services) {

            if (!s.isComplimentary) {

                cost += s.price;
            }
        }

        return cost;
    }

    public double total() {

        return totalRoomCost()
                + serviceCost();
    }

    public void invoice() {

        System.out.println(
                "\n=========== INVOICE ===========");

        System.out.println(
                "Booking ID : " + id);

        System.out.println(
                "Guest Name : " + guestName);

        System.out.println(
                "Stay       : " + in + " to " + out);

        long days = days();

        java.util.Map<String, Integer> count =
                new java.util.HashMap<>();

        java.util.Map<String, Double> price =
                new java.util.HashMap<>();

        for (Room r : rooms) {

            count.put(
                    r.type,
                    count.getOrDefault(r.type, 0) + 1);

            price.put(r.type, r.price);
        }

        System.out.println(
                "\n--- Rooms Summary ---");

        double totalRoom = 0;

        for (String t : count.keySet()) {

            int c = count.get(t);

            double p = price.get(t);

            double cost = c * days * p;

            totalRoom += cost;

            System.out.println(
                    t + " (" + c + " rooms × "
                            + days + " days) : $"
                            + cost);
        }

        System.out.println(
                "Room Total : $" + totalRoom);

        System.out.println(
                "\n--- Services ---");

        double sCost = 0;

        for (Service s : services) {

            if (!s.isComplimentary) {

                System.out.println(
                        s.name + " : $" + s.price);

                sCost += s.price;
            }
        }

        System.out.println(
                "\n--- Complimentary ---");

        for (Service s : services) {

            if (s.isComplimentary) {

                System.out.println(
                        s.name + " : FREE");
            }
        }

        System.out.println(
                "\nTOTAL BILL : $"
                        + (totalRoom + sCost));

        System.out.println(
                "==============================\n");
    }
}