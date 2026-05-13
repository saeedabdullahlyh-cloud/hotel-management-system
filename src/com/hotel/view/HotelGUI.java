package com.hotel.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HotelGUI extends JFrame {

    JTextField nameField;
    JTextField checkInField;
    JTextField checkOutField;
    JTextField roomCountField;

    JCheckBox poolBox;
    JCheckBox gymBox;
    JCheckBox breakfastBox;
    JCheckBox lunchBox;
    JCheckBox dinnerBox;

    JButton bookButton;
    JButton showRoomsButton;
    JButton revenueButton;
    JButton searchButton;
    JButton exitButton;

    JTextArea roomStatusArea;

    JPanel dynamicRoomPanel;

    ArrayList<JComboBox<String>> roomTypeBoxes =
            new ArrayList<>();

    ArrayList<Integer> bookedRooms =
            new ArrayList<>();

    HashMap<Integer, String> bookingRecords =
            new HashMap<>();

    int totalRevenue = 0;

    public HotelGUI() {

        setTitle("FINAL HOTEL MANAGEMENT SYSTEM");

        setSize(1500, 900);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ================= HEADER =================

        JPanel header = new JPanel();

        header.setBackground(
                new Color(44, 62, 80)
        );

        JLabel title = new JLabel(
                "HOTEL MANAGEMENT SYSTEM"
        );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        42
                )
        );

        header.add(title);

        add(header, BorderLayout.NORTH);

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(
                new GridLayout(
                        1,
                        2,
                        20,
                        20
                )
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // ================= LEFT CONTAINER =================

        JPanel leftContainer =
                new JPanel();

        leftContainer.setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        // ================= FORM PANEL =================

        JPanel formPanel =
                new JPanel();

        formPanel.setLayout(
                new GridLayout(
                        9,
                        2,
                        12,
                        12
                )
        );

        Font labelFont =
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                );

        Font fieldFont =
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                );

        JLabel nameLabel =
                new JLabel("Customer Name:");

        JLabel inLabel =
                new JLabel("Check-In Day:");

        JLabel outLabel =
                new JLabel("Check-Out Day:");

        JLabel roomLabel =
                new JLabel("How Many Rooms:");

        nameLabel.setFont(labelFont);
        inLabel.setFont(labelFont);
        outLabel.setFont(labelFont);
        roomLabel.setFont(labelFont);

        nameField = new JTextField();
        checkInField = new JTextField();
        checkOutField = new JTextField();
        roomCountField = new JTextField();

        JTextField[] fields = {

                nameField,

                checkInField,

                checkOutField,

                roomCountField
        };

        for (JTextField field : fields) {

            field.setFont(fieldFont);

            field.setPreferredSize(
                    new Dimension(
                            300,
                            45
                    )
            );
        }

        // ================= ENTER NAVIGATION =================

        nameField.addActionListener(e ->
                checkInField.requestFocus());

        checkInField.addActionListener(e ->
                checkOutField.requestFocus());

        checkOutField.addActionListener(e ->
                roomCountField.requestFocus());

        // ================= AUTO ROOM GENERATION =================

        roomCountField.addActionListener(e -> {

            try {

                dynamicRoomPanel.removeAll();

                roomTypeBoxes.clear();

                int roomCount =
                        Integer.parseInt(
                                roomCountField
                                        .getText()
                                        .trim()
                        );

                if (roomCount < 1 ||
                        roomCount > 20) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room count must be 1-20"
                    );

                    return;
                }

                for (int i = 1;
                     i <= roomCount;
                     i++) {

                    JPanel roomPanel =
                            new JPanel(
                                    new BorderLayout(
                                            15,
                                            15
                                    )
                            );

                    roomPanel.setBorder(
                            BorderFactory.createLineBorder(
                                    Color.GRAY,
                                    2
                            )
                    );

                    roomPanel.setPreferredSize(
                            new Dimension(
                                    600,
                                    80
                            )
                    );

                    JLabel roomTypeLabel =
                            new JLabel(
                                    "Room "
                                            + i
                                            + " Type:"
                            );

                    roomTypeLabel.setFont(
                            new Font(
                                    "Arial",
                                    Font.BOLD,
                                    22
                            )
                    );

                    JComboBox<String> combo =
                            new JComboBox<>(
                                    new String[]{

                                            "Single Room - $50",

                                            "Double Room - $100",

                                            "Sweet Room - $150",

                                            "Luxury Room - $300"
                                    }
                            );

                    combo.setFont(
                            new Font(
                                    "Arial",
                                    Font.PLAIN,
                                    20
                            )
                    );

                    combo.setPreferredSize(
                            new Dimension(
                                    350,
                                    50
                            )
                    );

                    roomTypeBoxes.add(combo);

                    roomPanel.add(
                            roomTypeLabel,
                            BorderLayout.WEST
                    );

                    roomPanel.add(
                            combo,
                            BorderLayout.CENTER
                    );

                    dynamicRoomPanel.add(roomPanel);
                }

                dynamicRoomPanel.revalidate();

                dynamicRoomPanel.repaint();
            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid room count"
                );
            }
        });

        // ================= ADD FIELDS =================

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(inLabel);
        formPanel.add(checkInField);

        formPanel.add(outLabel);
        formPanel.add(checkOutField);

        formPanel.add(roomLabel);
        formPanel.add(roomCountField);

        // ================= ACTIVITIES =================

        poolBox =
                new JCheckBox(
                        "Swimming Pool - $20"
                );

        gymBox =
                new JCheckBox(
                        "Gym Access - $15"
                );

        breakfastBox =
                new JCheckBox(
                        "Breakfast - $10"
                );

        lunchBox =
                new JCheckBox(
                        "Lunch - $20"
                );

        dinnerBox =
                new JCheckBox(
                        "Dinner - $25"
                );

        JCheckBox[] boxes = {

                poolBox,

                gymBox,

                breakfastBox,

                lunchBox,

                dinnerBox
        };

        for (JCheckBox box : boxes) {

            box.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            18
                    )
            );

            formPanel.add(box);
        }

        leftContainer.add(
                formPanel,
                BorderLayout.NORTH
        );

        // ================= ROOM PANEL =================

        dynamicRoomPanel =
                new JPanel();

        dynamicRoomPanel.setLayout(
                new GridLayout(
                        10,
                        1,
                        20,
                        20
                )
        );

        dynamicRoomPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                Color.GRAY,
                                2
                        ),
                        "Room Type Selection",
                        0,
                        0,
                        new Font(
                                "Arial",
                                Font.BOLD,
                                26
                        )
                )
        );

        JScrollPane roomScroll =
                new JScrollPane(
                        dynamicRoomPanel
                );

        roomScroll.setPreferredSize(
                new Dimension(
                        700,
                        500
                )
        );

        leftContainer.add(
                roomScroll,
                BorderLayout.CENTER
        );

        mainPanel.add(leftContainer);

        // ================= ROOM STATUS =================

        roomStatusArea =
                new JTextArea();

        roomStatusArea.setEditable(false);

        roomStatusArea.setFont(
                new Font(
                        "Monospaced",
                        Font.BOLD,
                        20
                )
        );

        updateRoomStatus();

        JScrollPane scrollPane =
                new JScrollPane(
                        roomStatusArea
                );

        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        // ================= BUTTON PANEL =================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                15,
                                15
                        )
                );

        bookButton =
                new JButton("BOOK ROOM");

        showRoomsButton =
                new JButton("SHOW ROOMS");

        revenueButton =
                new JButton("REVENUE");

        searchButton =
                new JButton("SEARCH ROOM");

        exitButton =
                new JButton("EXIT");

        JButton[] buttons = {

                bookButton,

                showRoomsButton,

                revenueButton,

                searchButton,

                exitButton
        };

        for (JButton btn : buttons) {

            btn.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            22
                    )
            );

            btn.setForeground(Color.WHITE);

            btn.setFocusPainted(false);

            btn.setPreferredSize(
                    new Dimension(
                            200,
                            60
                    )
            );
        }

        bookButton.setBackground(
                new Color(46, 204, 113)
        );

        showRoomsButton.setBackground(
                new Color(155, 89, 182)
        );

        revenueButton.setBackground(
                new Color(243, 156, 18)
        );

        searchButton.setBackground(
                new Color(52, 152, 219)
        );

        exitButton.setBackground(
                new Color(231, 76, 60)
        );

        buttonPanel.add(bookButton);

        buttonPanel.add(showRoomsButton);

        buttonPanel.add(revenueButton);

        buttonPanel.add(searchButton);

        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ================= BOOK BUTTON =================

        bookButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText().trim();

                if (!name.matches("[a-zA-Z ]+")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid customer name"
                    );

                    return;
                }

                int inDay =
                        Integer.parseInt(
                                checkInField.getText().trim()
                        );

                int outDay =
                        Integer.parseInt(
                                checkOutField.getText().trim()
                        );

                int totalDays =
                        outDay - inDay;

                if (totalDays <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid stay duration"
                    );

                    return;
                }

                int total = 0;

                StringBuilder invoice =
                        new StringBuilder();

                invoice.append(
                        "=========== HOTEL INVOICE ===========\n\n"
                );

                invoice.append(
                        "Customer : "
                                + name
                                + "\n\n"
                );

                invoice.append(
                        "Stay Days : "
                                + totalDays
                                + "\n\n"
                );

                for (int i = 0;
                     i < roomTypeBoxes.size();
                     i++) {

                    int roomId = i + 1;

                    String roomType =
                            roomTypeBoxes
                                    .get(i)
                                    .getSelectedItem()
                                    .toString();

                    int price = 0;

                    if (roomType.contains("Single"))
                        price = 50;

                    else if (roomType.contains("Double"))
                        price = 100;

                    else if (roomType.contains("Sweet"))
                        price = 150;

                    else if (roomType.contains("Luxury"))
                        price = 300;

                    int roomTotal =
                            price * totalDays;

                    total += roomTotal;

                    bookedRooms.add(roomId);

                    invoice.append(
                            "Room ID : "
                                    + roomId
                                    + "\n"
                    );

                    invoice.append(
                            "Room Type : "
                                    + roomType
                                    + "\n"
                    );

                    invoice.append(
                            "$"
                                    + price
                                    + " × "
                                    + totalDays
                                    + " Days = $"
                                    + roomTotal
                                    + "\n\n"
                    );

                    bookingRecords.put(
                            roomId,

                            "Customer : "
                                    + name
                                    + "\n"
                                    + "Room Type : "
                                    + roomType
                                    + "\n"
                                    + "Stay Days : "
                                    + totalDays
                    );
                }

                invoice.append(
                        "=========== ACTIVITIES ===========\n\n"
                );

                if (poolBox.isSelected()) {

                    total += 20;

                    invoice.append(
                            "Swimming Pool = $20\n"
                    );
                }

                if (gymBox.isSelected()) {

                    total += 15;

                    invoice.append(
                            "Gym Access = $15\n"
                    );
                }

                if (breakfastBox.isSelected()) {

                    total += 10;

                    invoice.append(
                            "Breakfast = $10\n"
                    );
                }

                if (lunchBox.isSelected()) {

                    total += 20;

                    invoice.append(
                            "Lunch = $20\n"
                    );
                }

                if (dinnerBox.isSelected()) {

                    total += 25;

                    invoice.append(
                            "Dinner = $25\n"
                    );
                }

                invoice.append(
                        "\n===================================\n"
                );

                invoice.append(
                        "GRAND TOTAL = $"
                                + total
                );

                totalRevenue += total;

                updateRoomStatus();

                JTextArea area =
                        new JTextArea(
                                invoice.toString()
                        );

                area.setFont(
                        new Font(
                                "Monospaced",
                                Font.BOLD,
                                18
                        )
                );

                area.setEditable(false);

                JOptionPane.showMessageDialog(
                        this,
                        new JScrollPane(area),
                        "FINAL INVOICE",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid input values"
                );
            }
        });

        // ================= SHOW ROOMS =================

        showRoomsButton.addActionListener(e -> {

            updateRoomStatus();

            JOptionPane.showMessageDialog(
                    this,
                    new JScrollPane(roomStatusArea),
                    "ROOM STATUS",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // ================= REVENUE =================

        revenueButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Total Hotel Revenue = $"
                            + totalRevenue
            );
        });

        // ================= SEARCH ROOM =================

        searchButton.addActionListener(e -> {

            try {

                String input =
                        JOptionPane.showInputDialog(
                                this,
                                "Enter Room ID:"
                        );

                if (input == null) {

                    return;
                }

                int roomId =
                        Integer.parseInt(input);

                if (bookingRecords.containsKey(roomId)) {

                    JTextArea area =
                            new JTextArea(
                                    bookingRecords.get(roomId)
                            );

                    area.setFont(
                            new Font(
                                    "Arial",
                                    Font.BOLD,
                                    18
                            )
                    );

                    area.setEditable(false);

                    JOptionPane.showMessageDialog(
                            this,
                            new JScrollPane(area),
                            "ROOM RECORD",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No booking found for Room ID "
                                    + roomId
                    );
                }
            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Room ID"
                );
            }
        });

        // ================= EXIT =================

        exitButton.addActionListener(e -> {

            System.exit(0);
        });

        setVisible(true);
    }

    // ================= ROOM STATUS =================

    public void updateRoomStatus() {

        StringBuilder sb =
                new StringBuilder();

        sb.append(
                "=========== ROOM STATUS ===========\n\n"
        );

        for (int i = 1; i <= 20; i++) {

            if (bookedRooms.contains(i)) {

                sb.append(
                        "Room ID "
                                + i
                                + " → BOOKED\n"
                );
            }

            else {

                sb.append(
                        "Room ID "
                                + i
                                + " → AVAILABLE\n"
                );
            }
        }

        roomStatusArea.setText(
                sb.toString()
        );
    }
}