package com.hotel.service;

import com.hotel.database.DatabaseConnection;
import com.hotel.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

    public class AuthService {

        public User login(
                String username,
                String password
        ) {

            try {

                Connection con =
                        DatabaseConnection.getConnection();

                String query =
                        "SELECT * FROM users WHERE username=? AND password=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setString(1, username);

                ps.setString(2, password);

                ResultSet rs =
                        ps.executeQuery();

                if(rs.next()) {

                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }

                con.close();
            }

            catch(Exception e) {

                e.printStackTrace();
            }

            return null;
        }
    }

