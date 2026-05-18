package com.hotel.service;

public class PaymentService {

    // ================= ROOM PRICE =================

    public int getRoomPrice(String roomType) {

        if (roomType.contains("Single"))
            return 50;

        if (roomType.contains("Double"))
            return 100;

        if (roomType.contains("Sweet"))
            return 150;

        if (roomType.contains("Luxury"))
            return 300;

        return 0;
    }

    // ================= ACTIVITIES TOTAL =================

    public int calculateActivities(

            boolean pool,

            boolean gym,

            boolean breakfast,

            boolean lunch,

            boolean dinner
    ) {

        int total = 0;

        if (pool)
            total += 20;

        if (gym)
            total += 15;

        if (breakfast)
            total += 10;

        if (lunch)
            total += 20;

        if (dinner)
            total += 25;

        return total;
    }

    // ================= ROOM TOTAL =================

    public int calculateRoomTotal(
            int roomPrice,
            int stayDays
    ) {

        return roomPrice * stayDays;
    }

    // ================= GRAND TOTAL =================

    public int calculateGrandTotal(

            int roomTotal,

            int activitiesTotal
    ) {

        return roomTotal + activitiesTotal;
    }
}