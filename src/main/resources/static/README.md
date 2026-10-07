# 📚 Library Management System

A full-stack web application designed to streamline library inventory workflows, manage book availability, and automate borrow/return cycles. Built with Spring Boot, Spring Data JPA, MySQL, and a vanilla JavaScript frontend.

---

## 🚀 Features

* **Book Catalog:** Browse available books with dynamic stock tracking.
* **Borrow Engine:** Issue books instantly with automated 14-day due-date calculation and stock deduction.
* **Stock Guard:** Automatically disables borrowing when a book is out of stock.
* **Return System:** Return borrowed books to replenish inventory count.
* **Add Books:** Expand catalog in real-time using a clean UI form.

---

## 🛠️ Tech Stack

* **Backend:** Java 17+, Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Database:** MySQL 8.x, Hibernate ORM
* **Frontend:** HTML5, CSS3, JavaScript (Fetch API)
* **Build Tool:** Maven

---

## ⚙️ Setup & Installation

### 1. Database Setup
Ensure MySQL is running locally. Log into MySQL and create the database:
```sql
CREATE DATABASE library_db;