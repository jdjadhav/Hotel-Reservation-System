# 🏨 Hotel Reservation System

A console-based Hotel Reservation System developed using **Java**, **JDBC**, and **MySQL**. The application allows users to manage hotel reservations through a menu-driven interface and demonstrates CRUD operations with database connectivity.

---

## 📌 Features

- ✅ Reserve a Room
- ✅ View All Reservations
- ✅ Get Room Number by Reservation Details
- ✅ Update Reservation Information
- ✅ Delete Reservations
- ✅ MySQL Database Integration using JDBC
- ✅ Console-Based User Interface

---

## 🛠️ Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL
- VS Code

---

## 📂 Database Schema

### Reservations Table

| Column Name | Data Type |
|------------|-----------|
| reservation_id | INT (Primary Key, Auto Increment) |
| guest_name | VARCHAR(255) |
| room_number | INT |
| contact_number | VARCHAR(10) |
| reservation_date | TIMESTAMP |

---

## 🚀 Project Structure

```text
Hotel_JDBC/
│
├── HotelReservationSystem.java
├── lib/
│   └── mysql-connector-j-9.7.0.jar
├── .gitignore
└── README.md
```

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/Hotel-Reservation-System.git
```

### 2. Create Database

```sql
CREATE DATABASE hotel_db;
USE hotel_db;
```

### 3. Create Table

```sql
CREATE TABLE reservations(
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(255) NOT NULL,
    room_number INT NOT NULL,
    contact_number VARCHAR(10) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 4. Update Database Credentials

```java
String url = "jdbc:mysql://localhost:3306/hotel_db";
String username = "root";
String password = "your_password";
```

### 5. Compile and Run

```bash
javac -cp ".;lib/mysql-connector-j-9.7.0.jar" HotelReservationSystem.java

java -cp ".;lib/mysql-connector-j-9.7.0.jar" HotelReservationSystem
```

---

# 📸 Screenshots

## Main Menu

![Main Menu](images/main-menu.png)

---

## Database Structure

![Database Structure](images/database-schema.png)

---

## Reservation Records

![Reservation Records](images/reservation-data.png)

---

## 📖 Learning Outcomes

This project helped in understanding:

- JDBC Connectivity
- SQL Queries
- CRUD Operations
- Exception Handling
- Java Programming
- Database Management
- Console Application Development

---

## 🎯 Future Improvements

- GUI using Java Swing / JavaFX
- User Authentication
- Room Availability Tracking
- Billing & Payment Module
- Search & Filter Reservations
- Export Reservation Reports

---

## 👨‍💻 Author

**Jay Jadhav**

Computer Science Engineering Student

---

⭐ If you found this project useful, consider giving it a star.
