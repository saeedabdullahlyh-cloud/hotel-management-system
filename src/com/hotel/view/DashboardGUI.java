package com.hotel.view;

import com.hotel.service.Hotel;

import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    JButton bookingButton;

    JButton revenueButton;

    JButton logoutButton;

    JLabel totalRoomsLabel;

    JLabel bookedRoomsLabel;

    JLabel availableRoomsLabel;

    JLabel revenueLabel;

    Hotel hotel =
            new Hotel();

    public DashboardGUI() {

        // ================= LOAD BOOKINGS =================

//        hotel.loadBookingsFromDB();

        // ================= FRAME =================

        setTitle("Hotel Dashboard");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ================= HEADER =================

        JPanel header =
                new JPanel();

        header.setBackground(
                new Color(44,62,80)
        );

        JLabel title =
                new JLabel(
                        "HOTEL MANAGEMENT DASHBOARD"
                );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        header.add(title);

        add(header, BorderLayout.NORTH);

        // ================= SIDEBAR =================

        JPanel sidebar =
                new JPanel();

        sidebar.setLayout(
                new GridLayout(10,1,10,10)
        );

        sidebar.setBackground(
                new Color(52,73,94)
        );

        bookingButton =
                new JButton("BOOK ROOMS");

        revenueButton =
                new JButton("REVENUE");

        logoutButton =
                new JButton("LOGOUT");

        JButton[] buttons = {

                bookingButton,
                revenueButton,
                logoutButton
        };

        for(JButton button : buttons) {

            button.setFocusPainted(false);

            button.setBackground(
                    new Color(41,128,185)
            );

            button.setForeground(Color.WHITE);

            button.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            18
                    )
            );

            sidebar.add(button);
        }

        add(sidebar, BorderLayout.WEST);

        // ================= CENTER PANEL =================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new GridLayout(2,2,30,30)
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        40,
                        40,
                        40
                )
        );

        // ================= REAL DATA =================

        int totalRooms = 20;

        int bookedRooms =
                hotel.bookings.size();

        int availableRooms =
                totalRooms - bookedRooms;

        double revenue =
                hotel.getRevenue();

        // ================= CARDS =================

        totalRoomsLabel =
                createCard(
                        "TOTAL ROOMS",
                        String.valueOf(totalRooms)
                );

        bookedRoomsLabel =
                createCard(
                        "BOOKED ROOMS",
                        String.valueOf(bookedRooms)
                );

        availableRoomsLabel =
                createCard(
                        "AVAILABLE ROOMS",
                        String.valueOf(availableRooms)
                );

        revenueLabel =
                createCard(
                        "TOTAL REVENUE",
                        "$" + revenue
                );

        centerPanel.add(totalRoomsLabel);

        centerPanel.add(bookedRoomsLabel);

        centerPanel.add(availableRoomsLabel);

        centerPanel.add(revenueLabel);

        add(centerPanel, BorderLayout.CENTER);

        // ================= BUTTON ACTIONS =================

        bookingButton.addActionListener(e -> {

            new HotelGUI().setVisible(true);
        });

        revenueButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Total Revenue = $" + revenue
            );
        });

        logoutButton.addActionListener(e -> {

            new LoginGUI().setVisible(true);

            dispose();
        });
    }

    // ================= CARD METHOD =================

    public JLabel createCard(

            String title,

            String value
    ) {

        JLabel label =
                new JLabel(

                        "<html><center>"
                                + "<h1>"
                                + value
                                + "</h1>"
                                + "<h2>"
                                + title
                                + "</h2>"
                                + "</center></html>",

                        SwingConstants.CENTER
                );

        label.setOpaque(true);

        label.setBackground(
                new Color(236,240,241)
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        label.setBorder(
                BorderFactory.createLineBorder(
                        Color.GRAY,
                        2
                )
        );

        return label;
    }
}