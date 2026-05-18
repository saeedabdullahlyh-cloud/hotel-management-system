# 🏨 Hotel Management System (Java + MVC)

> A professional desktop-based **Hotel Management System** developed using **Java Swing, MySQL, JDBC, and MVC Architecture**.

---

# 📌 Project Overview

The **Hotel Management System** is a complete Java desktop application that helps manage hotel operations such as:

- Room Booking
- Room Searching
- Room Cancellation
- Invoice Generation
- Revenue Management
- Database Synchronization
- Multiple Room Allocation

This project follows the **MVC (Model View Controller)** design pattern and demonstrates strong implementation of:

- Object-Oriented Programming (OOP)
- GUI Development
- Database Connectivity
- File Handling
- Business Logic Separation

---

# ✨ Features

## 🛏️ Room Booking System

✔ Dynamic Room Booking  
✔ Multiple Room Reservation  
✔ Automatic Room Allocation  
✔ Room Availability Checking  
✔ Room Cancellation System  
✔ Auto Room ID Management  
✔ Real-Time Room Status  

---

## 🧾 Invoice Management

✔ Professional Invoice Generation  
✔ Scrollable Invoice Window  
✔ Detailed Room Charges  
✔ Activities Pricing  
✔ Complimentary Services  
✔ Grand Total Calculation  

---

## 🎯 Hotel Activities

- 🏊 Swimming Pool
- 🏋 Gym Access
- 🍳 Breakfast Buffet
- 🍽 Lunch Buffet
- 🍗 Dinner Buffet
- 🎮 Indoor Sports
- ⚽ Outdoor Sports

---

## 💰 Revenue Management

✔ Total Revenue Calculation  
✔ Revenue Tracking  
✔ Revenue Persistence using Files  
✔ Automatic Revenue Update after Cancellation  

---

## 🗄️ Database Integration

✔ MySQL Database Connectivity  
✔ JDBC Integration  
✔ Save Bookings to Database  
✔ Load Bookings on Startup  
✔ Delete Booking Records  
✔ Real-Time Database Synchronization  

---

# 🧱 MVC Architecture

## 📦 Model

Handles project data and entities.

```text
Booking.java
Room.java
Service.java
Payment.java
```

---

## 🖥️ View

Handles graphical user interface.

```text
HotelGUI.java
```

---

## 🎮 Controller

Handles communication between GUI and backend.

```text
RoomController.java
BookingController.java
```

---

## ⚙️ Service Layer

Contains business logic and hotel operations.

```text
Hotel.java
PaymentService.java
```

---

# 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core Programming |
| Java Swing | GUI Development |
| MySQL | Database Management |
| JDBC | Database Connectivity |
| MVC Architecture | Project Structure |
| OOP Concepts | System Design |

---

# 📂 Project Functionalities

## ✅ Booking Management

- Book hotel rooms
- Allocate multiple rooms
- Dynamic room selection
- Automatic room IDs

---

## ✅ Search System

- Search rooms using Room ID
- Display full invoice details
- Check room availability

---

## ✅ Cancellation System

- Cancel room bookings
- Remove booking from database
- Update revenue automatically

---

## ✅ GUI Features

✔ Professional Desktop Interface  
✔ Dynamic Room Panels  
✔ Interactive Buttons  
✔ Scrollable Invoice  
✔ Live Room Status Panel  
✔ Modern Color Theme  

---

# 🗃️ Database Structure

## 📋 bookings Table

| Column Name | Data Type |
|-------------|-----------|
| guest_name | VARCHAR |
| room_id | INT |
| room_type | VARCHAR |
| check_in | DATE |
| check_out | DATE |
| room_price | DOUBLE |
| total_days | INT |
| grand_total | DOUBLE |

---

# 🚀 How To Run Project

## 1️⃣ Clone Repository

```bash
git clone https://github.com/saeedabdullahlyh-cloud/hotel-management-system.git
```

---

## 2️⃣ Open Project

Open project in:

- IntelliJ IDEA
- Eclipse
- NetBeans

---

## 3️⃣ Create Database

```sql
CREATE DATABASE hotel_db;
```

---

## 4️⃣ Configure Database Connection

Update credentials inside:

```text
DatabaseConnection.java
```

Example:

```java
jdbc:mysql://localhost:3306/hotel_db
```

---

## 5️⃣ Run Project

Run:

```text
Main.java
```

---

# 📚 Learning Concepts Used

- Object-Oriented Programming (OOP)
- MVC Architecture
- Java Swing GUI
- JDBC Connectivity
- MySQL Integration
- File Handling
- Exception Handling
- Dynamic GUI Components

---


# 👨‍💻 Author

## Abdullah Saeed

### Java Developer | Swing GUI | MVC Architecture | MySQL Integration

📌 GitHub:  
https://github.com/saeedabdullahlyh-cloud

---

# ⭐ Project Highlights

✔ Professional Java Swing GUI  
✔ MVC Architecture  
✔ MySQL Database Integration  
✔ Dynamic Invoice System  
✔ Multiple Room Booking  
✔ Revenue Tracking  
✔ Real-Time Database Synchronization  

---

# 📄 License

This project is developed for **educational and learning purposes**.

---

# ❤️ Thank You

If you like this project, give it a ⭐ on GitHub.
