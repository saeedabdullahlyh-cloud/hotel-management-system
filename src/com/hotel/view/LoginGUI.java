package com.hotel.view;

import com.hotel.controller.LoginController;
import com.hotel.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    JTextField usernameField;

    JPasswordField passwordField;

    JButton loginButton;

    JButton exitButton;


    LoginController loginController =
            new LoginController();

    public LoginGUI() {

        setTitle("Hotel Login System");

        setSize(500, 350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(
                new Color(15, 23, 42)
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel(
                        "HOTEL LOGIN SYSTEM"
                );

        title.setBounds(70, 20, 400, 40);

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        add(title);

        // ================= USERNAME LABEL =================

        JLabel userLabel =
                new JLabel("Username:");

        userLabel.setBounds(60, 100, 120, 30);

        userLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );
        userLabel.setForeground(Color.WHITE);
        add(userLabel);

        // ================= USERNAME FIELD =================

        usernameField =
                new JTextField();

        usernameField.setBounds(190, 100, 200, 35);

        usernameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );
        usernameField.setBackground(
                new Color(51, 65, 85)
        );

        usernameField.setForeground(Color.WHITE);

        usernameField.setCaretColor(Color.WHITE);

        usernameField.setBorder(
                BorderFactory.createLineBorder(
                        new Color(59,130,246),
                        2
                )
        );
        add(usernameField);

        // ================= PASSWORD LABEL =================

        JLabel passLabel =
                new JLabel("Password:");

        passLabel.setBounds(60, 160, 120, 30);

        passLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );
        passLabel.setForeground(Color.WHITE);
        add(passLabel);

        // ================= PASSWORD FIELD =================

        passwordField =
                new JPasswordField();

        passwordField.setBounds(190, 160, 200, 35);

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );
        passwordField.setBackground(
                new Color(51, 65, 85)
        );

        passwordField.setForeground(Color.WHITE);

        passwordField.setCaretColor(Color.WHITE);

        passwordField.setBorder(
                BorderFactory.createLineBorder(
                        new Color(59,130,246),
                        2
                )
        );
        add(passwordField);

        // ================= LOGIN BUTTON =================

        loginButton =
                new JButton("LOGIN");

        loginButton.setBounds(80, 240, 130, 40);

        loginButton.setBackground(
                new Color(37, 99, 235)
        );
        loginButton.setFocusPainted(false);

        loginButton.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        add(loginButton);
// ================= ENTER KEY NAVIGATION =================

        usernameField.addActionListener(e -> {

            passwordField.requestFocus();
        });

        passwordField.addActionListener(e -> {

            loginButton.doClick();
        });
        // ================= EXIT BUTTON =================

        exitButton =
                new JButton("EXIT");

        exitButton.setBounds(260, 240, 130, 40);

        exitButton.setBackground(
                new Color(220, 38, 38)
        );
        exitButton.setFocusPainted(false);

        exitButton.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        exitButton.setForeground(Color.WHITE);

        exitButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        add(exitButton);

        // ================= LOGIN ACTION =================

        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText();

            String password =
                    String.valueOf(
                            passwordField.getPassword()
                    );

            if (username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields"
                );

                return;
            }

            User user =
                    loginController.login(
                            username,
                            password
                    );

            if (user != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful as " + user.role
                );

                new DashboardGUI().setVisible(true);

                dispose();
            }

            else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password"
                );
            }
        });

        // ================= EXIT ACTION =================

        exitButton.addActionListener(e -> {

            System.exit(0);
        });
    }
}