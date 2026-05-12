package com.hotel.view;

import com.hotel.service.Hotel;
import com.hotel.model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HotelGUI extends JFrame {

    // ===== HOTEL OBJECT =====

    Hotel h = new Hotel();

    // ===== BUTTONS =====

    JButton bookBtn;
    JButton showBtn;
    JButton searchBtn;
    JButton cancelBtn;
    JButton revenueBtn;
    JButton exitBtn;

    public HotelGUI() {

        // ===== WINDOW SETTINGS =====

        setTitle("Hotel Management System");

        setSize(500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new GridLayout(6, 1, 10, 10));

        // ===== BUTTONS =====

        bookBtn =
                new JButton("Book Room");

        showBtn =
                new JButton("Show Rooms");

        searchBtn =
                new JButton("Search Booking");

        cancelBtn =
                new JButton("Cancel Booking");

        revenueBtn =
                new JButton("Total Revenue");

        exitBtn =
                new JButton("Exit");

        // ===== ADD BUTTONS =====

        add(bookBtn);

        add(showBtn);

        add(searchBtn);

        add(cancelBtn);

        add(revenueBtn);

        add(exitBtn);

        // ===== DEFAULT ROOMS =====

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

        // ===== EXIT BUTTON =====

        exitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        // ===== SHOW BUTTON =====

        showBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Rooms Loaded Successfully!"
                );
            }
        });

        // ===== BOOK BUTTON =====

        bookBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String name =
                        JOptionPane.showInputDialog(
                                "Enter Guest Name");

                String roomIdText =
                        JOptionPane.showInputDialog(
                                "Enter Room ID");

                String[] roomTypes = {
                        "Single",
                        "Double",
                        "Sweet",
                        "Luxury"
                };

                String roomType =
                        (String) JOptionPane.showInputDialog(
                                null,
                                "Select Room Type",
                                "Room Type",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                roomTypes,
                                roomTypes[0]
                        );

                int roomId =
                        Integer.parseInt(roomIdText);

                Room r =
                        new Room(roomId, roomType);

                // ===== ACTIVITIES CHECKBOXES =====

                JCheckBox swim =
                        new JCheckBox(
                                "Swimming Pool ($20)");

                JCheckBox gym =
                        new JCheckBox(
                                "Gym Access ($15)");

                JCheckBox sports =
                        new JCheckBox(
                                "Indoor Sports ($25)");

                JCheckBox breakfast =
                        new JCheckBox(
                                "Breakfast Buffet ($10)");

                JCheckBox lunch =
                        new JCheckBox(
                                "Lunch Buffet ($20)");

                JCheckBox dinner =
                        new JCheckBox(
                                "Dinner Buffet ($25)");

                JCheckBox airport =
                        new JCheckBox(
                                "Airport Pickup ($30)");

                JPanel panel =
                        new JPanel();

                panel.setLayout(
                        new GridLayout(0, 1));

                panel.add(swim);

                panel.add(gym);

                panel.add(sports);

                panel.add(breakfast);

                panel.add(lunch);

                panel.add(dinner);

                panel.add(airport);

                JOptionPane.showMessageDialog(
                        null,
                        panel,
                        "Select Activities",
                        JOptionPane.PLAIN_MESSAGE
                );

                // ===== SELECTED ACTIVITIES =====

                String activities = "";

                if (swim.isSelected())
                    activities += "Swimming Pool\n";

                if (gym.isSelected())
                    activities += "Gym Access\n";

                if (sports.isSelected())
                    activities += "Indoor Sports\n";

                if (breakfast.isSelected())
                    activities += "Breakfast Buffet\n";

                if (lunch.isSelected())
                    activities += "Lunch Buffet\n";

                if (dinner.isSelected())
                    activities += "Dinner Buffet\n";

                if (airport.isSelected())
                    activities += "Airport Pickup\n";

                if (activities.equals(""))
                    activities = "No Activities Selected";

                // ===== SUCCESS MESSAGE =====

                JOptionPane.showMessageDialog(
                        null,
                        "Room Booked Successfully!\n\n"
                                + "Guest: " + name
                                + "\nRoom ID: " + roomId
                                + "\nType: " + roomType
                                + "\n\nActivities:\n"
                                + activities
                );
            }
        });

        // ===== SEARCH BUTTON =====

        searchBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Search Feature Coming Next!"
                );
            }
        });

        // ===== CANCEL BUTTON =====

        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Cancel Feature Coming Next!"
                );
            }
        });

        // ===== REVENUE BUTTON =====

        revenueBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Revenue Feature Coming Next!"
                );
            }
        });

        // ===== SHOW WINDOW =====

        setVisible(true);
    }
}