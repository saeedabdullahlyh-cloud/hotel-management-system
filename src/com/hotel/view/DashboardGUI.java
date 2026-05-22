package com.hotel.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.hotel.service.Hotel;

import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    JButton bookingButton;

    JButton revenueButton;

    JButton logoutButton;
    JButton clearRevenueButton;
    JLabel totalRoomsLabel;

    JLabel bookedRoomsLabel;

    JLabel availableRoomsLabel;

    JLabel revenueLabel;

    Hotel hotel =
            new Hotel();

    public DashboardGUI() {

        // ================= FRAME =================

        setTitle("Hotel Dashboard");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        getContentPane().setBackground(
                new Color(226,232,240)
        );

        // ================= HEADER =================

        JPanel header =
                new JPanel();

        header.setBackground(
                new Color(15,23,42)
        );

        JLabel title =
                new JLabel(
                        "HOTEL MANAGEMENT DASHBOARD"
                );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        34
                )
        );

        header.add(title);

        add(header, BorderLayout.NORTH);

        // ================= SIDEBAR =================

// ================= SIDEBAR =================

        JPanel sidebar =
                new JPanel();

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBackground(
                new Color(30,41,59)
        );

        sidebar.setPreferredSize(
                new Dimension(
                        280,
                        700
                )
        );

        sidebar.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        20,
                        40,
                        20
                )
        );

        bookingButton =
                new JButton("BOOK ROOMS");

        revenueButton =
                new JButton("REVENUE");

        logoutButton =
                new JButton("LOGOUT");
        clearRevenueButton =
                new JButton("CLEAR REVENUE");

        JButton[] buttons = {

                bookingButton,
                revenueButton,
                logoutButton,
                clearRevenueButton
        };

        for(JButton button : buttons) {

            button.setFocusPainted(false);

            button.setBackground(
                    new Color(59,130,246)
            );

            button.setForeground(Color.WHITE);

            button.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            22
                    )
            );

            button.setMaximumSize(
                    new Dimension(
                            220,
                            70
                    )
            );

            button.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

            button.setBorder(
                    BorderFactory.createEmptyBorder(
                            15,
                            20,
                            15,
                            20
                    )
            );

            sidebar.add(button);

            sidebar.add(
                    Box.createRigidArea(
                            new Dimension(
                                    0,
                                    25
                            )
                    )
            );
        }

        add(sidebar, BorderLayout.WEST);

        // ================= CENTER PANEL =================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setBackground(
                new Color(226,232,240)
        );

        centerPanel.setLayout(
                new GridLayout(2,2,40,40)
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        20,
                        30
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

        // ================= PIE CHART =================
// ================= PIE CHART =================

        DefaultPieDataset dataset =
                new DefaultPieDataset();

        dataset.setValue(
                "Booked Rooms",
                bookedRooms
        );

        dataset.setValue(
                "Available Rooms",
                availableRooms
        );

        JFreeChart chart =
                ChartFactory.createPieChart(

                        "Room Occupancy",

                        dataset,

                        true,

                        true,

                        false
                );

        ChartPanel chartPanel =
                new ChartPanel(chart);

        chartPanel.setPreferredSize(
                new Dimension(
                        350,
                        220
                )
        );

        JPanel chartContainer =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                10
                        )
                );

        chartContainer.setBackground(
                new Color(226,232,240)
        );

        chartContainer.add(chartPanel);

        add(chartContainer, BorderLayout.SOUTH);

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
// ================= CLEAR REVENUE =================

        clearRevenueButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(

                            this,

                            "Are you sure you want to clear all revenue?",

                            "Admin Control",

                            JOptionPane.YES_NO_OPTION
                    );

            if(choice == JOptionPane.YES_OPTION) {

                hotel.clearRevenue();

                revenueLabel.setText(

                        "<html><center>"
                                + "<h1>$0</h1>"
                                + "<h2>TOTAL REVENUE</h2>"
                                + "</center></html>"
                );

                JOptionPane.showMessageDialog(

                        this,

                        "Revenue cleared successfully!"
                );
            }
        });
        logoutButton.addActionListener(e -> {

            new LoginGUI().setVisible(true);

            dispose();
        });

        setVisible(true);
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
                new Color(248,250,252)
        );
        label.setPreferredSize(
                new Dimension(
                        300,
                        140
                )
        );
        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        label.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(203,213,225),
                                2
                        ),

                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        return label;
    }
}