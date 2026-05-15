package com.hotel.view;

import com.hotel.controller.RoomController;
import com.hotel.model.Booking;
import com.hotel.model.Room;
import com.hotel.model.Service;
import com.hotel.service.PaymentService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    JCheckBox indoorSportsBox;
    JCheckBox outdoorSportsBox;

    JButton bookButton;
    JButton showRoomsButton;
    JButton revenueButton;
    JButton searchButton;
    JButton cancelButton;
    JButton exitButton;

    JTextArea roomStatusArea;

    JPanel dynamicRoomPanel;

    ArrayList<JComboBox<String>> roomTypeBoxes =
            new ArrayList<>();

    RoomController roomController =
            new RoomController();

    PaymentService paymentService =
            new PaymentService();
    public HotelGUI() {

        setTitle("Advanced Hotel Management System");

        setSize(1600, 900);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        roomController
                .getHotelService()
                .loadBookingsFromDB();

        JPanel header =
                new JPanel();


        header.setBackground(
                new Color(44, 62, 80)
        );

        JLabel title =
                new JLabel(
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

        JPanel mainPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                15
                        )
                );

        JPanel leftPanel =
                new JPanel();

        leftPanel.setLayout(
                new BoxLayout(
                        leftPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                7,
                                2,
                                15,
                                15
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
                new JLabel("How Many Rooms?");

        nameLabel.setFont(labelFont);
        inLabel.setFont(labelFont);
        outLabel.setFont(labelFont);
        roomLabel.setFont(labelFont);

        nameField =
                new JTextField();

        checkInField =
                new JTextField();

        checkOutField =
                new JTextField();

        roomCountField =
                new JTextField();

        JTextField[] fields = {

                nameField,
                checkInField,
                checkOutField,
                roomCountField
        };

        for (JTextField field : fields) {

            field.setFont(fieldFont);
        }

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(inLabel);
        formPanel.add(checkInField);

        formPanel.add(outLabel);
        formPanel.add(checkOutField);

        formPanel.add(roomLabel);
        formPanel.add(roomCountField);

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
                        "Breakfast Buffet - $10"
                );

        lunchBox =
                new JCheckBox(
                        "Lunch Buffet - $20"
                );

        dinnerBox =
                new JCheckBox(
                        "Dinner Buffet - $25"
                );
        indoorSportsBox =
                new JCheckBox(
                        "Indoor Sports - $50"
                );

        outdoorSportsBox =
                new JCheckBox(
                        "Outdoor Sports - $40"
                );

        JCheckBox[] boxes = {

                poolBox,
                gymBox,
                breakfastBox,
                lunchBox,
                dinnerBox,
                indoorSportsBox,
                outdoorSportsBox
        };

        for (JCheckBox box : boxes) {

            box.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            18
                    )
            );
        }

        formPanel.add(poolBox);
        formPanel.add(gymBox);

        formPanel.add(breakfastBox);
        formPanel.add(lunchBox);

        formPanel.add(dinnerBox);
        formPanel.add(indoorSportsBox);

        formPanel.add(outdoorSportsBox);

        leftPanel.add(formPanel);

        dynamicRoomPanel =
                new JPanel();

        dynamicRoomPanel.setLayout(
                new GridLayout(
                        0,
                        2,
                        15,
                        15
                )
        );

        dynamicRoomPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Room Type Selection"
                )
        );

        JScrollPane roomScroll =
                new JScrollPane(
                        dynamicRoomPanel
                );

        roomScroll.setPreferredSize(
                new Dimension(
                        700,
                        320
                )
        );

        leftPanel.add(roomScroll);

        nameField.addActionListener(e ->
                checkInField.requestFocus()
        );

        checkInField.addActionListener(e ->
                checkOutField.requestFocus()
        );

        checkOutField.addActionListener(e ->
                roomCountField.requestFocus()
        );

        roomCountField.addActionListener(e ->
                generateRoomSelection()
        );

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

        roomStatusArea.setText(
                "Click SHOW ROOMS button"
        );

        JScrollPane rightScroll =
                new JScrollPane(
                        roomStatusArea
                );

        mainPanel.add(leftPanel);

        mainPanel.add(rightScroll);

        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                6,
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

        cancelButton =
                new JButton("CANCEL ROOM");

        exitButton =
                new JButton("EXIT");

        JButton[] buttons = {

                bookButton,
                showRoomsButton,
                revenueButton,
                searchButton,
                cancelButton,
                exitButton
        };

        for (JButton btn : buttons) {

            btn.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    )
            );

            btn.setForeground(Color.WHITE);
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

        cancelButton.setBackground(
                new Color(192, 57, 43)
        );

        exitButton.setBackground(
                new Color(231, 76, 60)
        );

        for (JButton btn : buttons) {

            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        bookButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText().trim();

                if (name.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Enter customer name"
                    );

                    return;
                }

                int inDay =
                        Integer.parseInt(
                                checkInField.getText()
                        );

                int outDay =
                        Integer.parseInt(
                                checkOutField.getText()
                        );

                if (inDay <= 0 || outDay <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Days must be greater than 0"
                    );

                    return;
                }

                if (outDay <= inDay) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Check-Out must be greater than Check-In"
                    );

                    return;
                }

                if (roomTypeBoxes.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Generate rooms first"
                    );

                    return;
                }

                LocalDate in =
                        LocalDate.of(
                                2025,
                                1,
                                inDay
                        );

                LocalDate out =
                        LocalDate.of(
                                2025,
                                1,
                                outDay
                        );

                List<Room> selectedRooms =
                        new ArrayList<>();

                List<Service> selectedServices =
                        new ArrayList<>();

                for (int i = 0;
                     i < roomTypeBoxes.size();
                     i++) {

                    String roomType =
                            roomTypeBoxes
                                    .get(i)
                                    .getSelectedItem()
                                    .toString();

                    String cleanType =
                            roomType
                                    .replace(" Room", "")
                                    .replace(" - $50", "")
                                    .replace(" - $100", "")
                                    .replace(" - $150", "")
                                    .replace(" - $300", "");

                    int autoRoomId = -1;

                    for (int roomCheck = 1;
                         roomCheck <= 20;
                         roomCheck++) {

                        boolean alreadySelected = false;

                        for (Room selected : selectedRooms) {

                            if (selected.id == roomCheck) {

                                alreadySelected = true;
                                break;
                            }
                        }

                        if (alreadySelected) {

                            continue;
                        }

                        boolean available =

                                roomController
                                        .getHotelService()
                                        .isAvailable(
                                                roomCheck,
                                                in,
                                                out
                                        );

                        if (available) {

                            autoRoomId = roomCheck;
                            break;
                        }
                    }

                    if (autoRoomId == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "No Rooms Available!"
                        );

                        return;
                    }

                    Room room =
                            new Room(
                                    autoRoomId,
                                    cleanType
                            );

                    selectedRooms.add(room);
                }

                if (poolBox.isSelected()) {

                    selectedServices.add(
                            new Service(
                                    "Swimming Pool",
                                    20,
                                    false
                            )
                    );
                }

                if (gymBox.isSelected()) {

                    selectedServices.add(
                            new Service(
                                    "Gym Access",
                                    15,
                                    false
                            )
                    );
                }

                if (breakfastBox.isSelected()) {

                    selectedServices.add(
                            new Service(
                                    "Breakfast Buffet",
                                    10,
                                    false
                            )
                    );
                }

                if (lunchBox.isSelected()) {

                    selectedServices.add(
                            new Service(
                                    "Lunch Buffet",
                                    20,
                                    false
                            )
                    );
                }

                if (indoorSportsBox.isSelected()) {

                    selectedServices.add(

                            new Service(
                                    "Indoor Sports",
                                    50,
                                    false
                            )
                    );
                }

                if (outdoorSportsBox.isSelected()) {

                    selectedServices.add(

                            new Service(
                                    "Outdoor Sports",
                                    40,
                                    false
                            )
                    );
                }

                if (dinnerBox.isSelected()) {

                    selectedServices.add(
                            new Service(
                                    "Dinner Buffet",
                                    25,
                                    false
                            )
                    );
                }

                roomController
                        .getHotelService()
                        .guiBookRoom(
                                name,
                                in,
                                out,
                                selectedRooms,
                                selectedServices
                        );

                Booking invoiceBooking =
                        new Booking(
                                name,
                                in,
                                out
                        );

                for (Room r : selectedRooms) {

                    invoiceBooking.addRoom(r);
                }

                for (Service s : selectedServices) {

                    invoiceBooking.addService(s);
                }

                StringBuilder invoice =
                        new StringBuilder();

                invoice.append(
                        "=========== HOTEL INVOICE ===========\n\n"
                );

                invoice.append(
                        "Customer Name : "
                                + name
                                + "\n"
                );


                invoice.append(
                        "Stay : "
                                + invoiceBooking.in
                                + " to "
                                + invoiceBooking.out
                                + "\n"
                );

                long days =
                        invoiceBooking.days();

                invoice.append(
                        "\n=========== ROOM DETAILS ===========\n\n"
                );

                double roomTotal = 0;

                for (Room r : selectedRooms) {

                    double roomCost =
                            r.price * days;

                    roomTotal += roomCost;

                    invoice.append(
                            "Room ID : "
                                    + r.id
                                    + "\nRoom Type : "
                                    + r.type
                                    + "\nPrice : $"
                                    + r.price
                                    + " × "
                                    + days
                                    + " Days"
                                    + "\nRoom Total : $"
                                    + roomCost
                                    + "\n\n"
                    );
                }

                invoice.append(
                        "=========== ACTIVITIES ===========\n\n"
                );

                double serviceTotal = 0;

                if (selectedServices.isEmpty()) {

                    invoice.append(
                            "No Activities Selected\n"
                    );
                }

                else {

                    for (Service s : selectedServices) {

                        invoice.append(
                                s.name
                                        + " : $"
                                        + s.price
                                        + "\n"
                        );

                        serviceTotal += s.price;
                    }
                }

                invoice.append(
                        "\n=========== COMPLIMENTARY ===========\n\n"
                );

                invoice.append(
                        "Welcome Drinks : FREE\n"
                );

                invoice.append(
                        "Free WiFi : FREE\n"
                );

                invoice.append(
                        "Free Parking : FREE\n"
                );

                invoice.append(
                        "Room Cleaning : FREE\n"
                );

                invoice.append(
                        "Swimming Towels : FREE\n"
                );

                double grandTotal =
                        roomTotal + serviceTotal;

                invoice.append(
                        "\n=========== BILL SUMMARY ===========\n\n"
                );

                invoice.append(
                        "Room Charges : $"
                                + roomTotal
                                + "\n"
                );

                invoice.append(
                        "Activities Charges : $"
                                + serviceTotal
                                + "\n"
                );

                invoice.append(
                        "Grand Total : $"
                                + grandTotal
                                + "\n"
                );

                invoice.append(
                        "\n===================================="
                );
                JTextArea invoiceArea =
                        new JTextArea(
                                invoice.toString()
                        );

                invoiceArea.setEditable(false);

                invoiceArea.setLineWrap(true);

                invoiceArea.setWrapStyleWord(true);

                invoiceArea.setCaretPosition(0);

                invoiceArea.setFont(
                        new Font(
                                "Monospaced",
                                Font.BOLD,
                                18
                        )
                );

                JScrollPane invoiceScroll =
                        new JScrollPane(
                                invoiceArea
                        );

                invoiceScroll.setPreferredSize(
                        new Dimension(
                                700,
                                700
                        )
                );

                invoiceScroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                );

                JOptionPane.showMessageDialog(

                        this,

                        invoiceScroll,

                        "FINAL HOTEL INVOICE",

                        JOptionPane.INFORMATION_MESSAGE
                );
                roomStatusArea.setText(
                        roomController.getRoomStatus(
                                in,
                                out
                        )
                );
            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        showRoomsButton.addActionListener(e -> {

            try {

                int inDay =
                        Integer.parseInt(
                                checkInField.getText()
                        );

                int outDay =
                        Integer.parseInt(
                                checkOutField.getText()
                        );

                LocalDate in =
                        LocalDate.of(
                                2025,
                                1,
                                inDay
                        );

                LocalDate out =
                        LocalDate.of(
                                2025,
                                1,
                                outDay
                        );

                roomStatusArea.setText(
                        roomController.getRoomStatus(
                                in,
                                out
                        )
                );
            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid dates first"
                );
            }
        });

        revenueButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Total Revenue = $"
                            + roomController
                            .getHotelService()
                            .getTotalRevenue()
            );
        });

        searchButton.addActionListener(e -> {

            String input =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Room ID:"
                    );

            if (input == null || input.isEmpty()) {
                return;
            }

            try {

                int roomId =
                        Integer.parseInt(input);

                String result =
                        roomController.searchRoom(
                                roomId
                        );

                JTextArea area =
                        new JTextArea(
                                result
                        );

                area.setEditable(false);

                area.setFont(

                        new Font(
                                "Monospaced",
                                Font.BOLD,
                                18
                        )
                );

                area.setLineWrap(true);

                area.setWrapStyleWord(true);

                JScrollPane pane =
                        new JScrollPane(area);

                pane.setPreferredSize(

                        new Dimension(
                                700,
                                600
                        )
                );

                JOptionPane.showMessageDialog(

                        this,

                        pane,

                        "ROOM DETAILS",

                        JOptionPane.INFORMATION_MESSAGE
                );

            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(

                        this,

                        "Invalid Room ID"
                );
            }
        });

        cancelButton.addActionListener(e -> {

            try {

                String input =
                        JOptionPane.showInputDialog(
                                this,
                                "Enter Room ID To Cancel:"
                        );

                if (input == null)
                    return;

                int roomId =
                        Integer.parseInt(input);

                roomController.cancelRoom(
                        roomId
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Room Cancelled Successfully!"
                );

                roomStatusArea.setText(
                        "Room ID "
                                + roomId
                                + " cancelled."
                );
            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Room ID"
                );
            }
        });

        exitButton.addActionListener(e -> {

            System.exit(0);
        });

        setVisible(true);
    }

    private void generateRoomSelection() {

        try {

            dynamicRoomPanel.removeAll();

            roomTypeBoxes.clear();

            int count =
                    Integer.parseInt(
                            roomCountField.getText()
                    );

            if (count <= 0 || count > 20) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room count must be 1-20"
                );

                return;
            }

            Font labelFont =
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    );

            Font comboFont =
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            18
                    );

            for (int i = 1; i <= count; i++) {

                JLabel label =
                        new JLabel(
                                "Room "
                                        + i
                                        + " Type:"
                        );

                label.setFont(labelFont);

                JComboBox<String> combo =
                        new JComboBox<>(

                                new String[]{

                                        "Single Room - $50",

                                        "Double Room - $100",

                                        "Sweet Room - $150",

                                        "Luxury Room - $300"
                                }
                        );

                combo.setFont(comboFont);

                roomTypeBoxes.add(combo);

                dynamicRoomPanel.add(label);

                dynamicRoomPanel.add(combo);
            }

            dynamicRoomPanel.revalidate();

            dynamicRoomPanel.repaint();

            if (!roomTypeBoxes.isEmpty()) {

                roomTypeBoxes
                        .get(0)
                        .requestFocus();
            }
        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid room count"
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new HotelGUI();
        });
    }
}