package com.hotel.controller;

import com.hotel.model.User;
import com.hotel.service.AuthService;

    public class LoginController {

        AuthService authService =
                new AuthService();

        public User login(
                String username,
                String password
        ) {

            return authService.login(
                    username,
                    password
            );
        }
    }

