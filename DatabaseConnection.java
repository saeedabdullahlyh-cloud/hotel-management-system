package com.hotel;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            Connection con =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/hotel_db",
                            "root",
                            "imrankhan804943"
                    );
            System.out.println("Database Connected!");
            return con;
        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
           return null;
        }
    }}
