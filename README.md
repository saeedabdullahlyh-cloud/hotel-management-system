Hotel Management System (Java + MVC)

A professional Hotel Management System developed using Java Swing, MySQL, and MVC Architecture.
This project provides a modern desktop-based hotel booking environment with dynamic room management, invoice generation, revenue tracking, and database synchronization.

Features
Room Booking System
Dynamic room booking
Multiple room reservation
Automatic room allocation
Room availability checking
Room cancellation system
Search rooms by Room ID
Invoice Management
Professional hotel invoice generation
Scrollable invoice window
Detailed room charges
Activities & services pricing
Complimentary services display
Grand total calculation
Hotel Activities
Swimming Pool
Gym Access
Breakfast Buffet
Lunch Buffet
Dinner Buffet
Indoor Sports
Outdoor Sports
Revenue Management
Total hotel revenue calculation
Revenue persistence using files
Revenue updates after cancellation
Database Integration
MySQL database connectivity
Booking records storage
Real-time database synchronization
Load bookings automatically on startup
Delete booking records from database
Technologies Used
Technology	Purpose
Java	Core Programming
Java Swing	GUI Development
MySQL	Database Management
JDBC	Database Connectivity
MVC Architecture	Project Structure
OOP Concepts	System Design
MVC Architecture
Model

Handles project data and entities.

Booking.java
Room.java
Service.java
Payment.java
View

Handles graphical user interface.

HotelGUI.java
Controller

Handles user interaction and communication between GUI and backend.

RoomController.java
BookingController.java
Service Layer

Handles business logic and hotel operations.

Hotel.java
PaymentService.java
System Functionalities
Booking Management
Book hotel rooms
Multiple room support
Automatic Room IDs
Room type selection
Search Management
Search booking using Room ID
Display complete invoice details
Check room availability
Cancellation System
Cancel room bookings
Automatic database updates
Revenue adjustment after cancellation
GUI Features
Modern desktop interface
Professional color theme
Dynamic room panels
Scrollable invoice system
Interactive buttons
Real-time room status display
Database Structure
bookings Table
Column	Type
guest_name	VARCHAR
room_id	INT
room_type	VARCHAR
check_in	DATE
check_out	DATE 
Author
Abdullah Saeed

Java Developer | MVC Architecture | Swing GUI | MySQL Integration
License

This project is developed for educational and learning purposes.
room_price	DOUBLE
total_days	INT
grand_total	DOUBLE
