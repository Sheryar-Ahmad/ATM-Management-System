# ATM Management System

A Java desktop project that simulates ATM services and bank account registration using **Swing**, **JDBC**, and **MySQL**.

## Features

- Card number and PIN login
- Three-step account registration
- Cash deposits and withdrawals
- Fast cash with preset amounts
- Balance enquiry and mini statements
- PIN changes and a startup splash screen

## Technology

| Component | Technology |
| --- | --- |
| User interface | Java Swing and AWT |
| Database | MySQL |
| Database connection | JDBC / MySQL Connector/J |
| Date picker | JCalendar |

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/Sheryar-Ahmad/ATM-Management-System.git
   ```

2. Open the project in a Java IDE and install a JDK.
3. Add **MySQL Connector/J** and **JCalendar** to the project libraries.
4. Place the Java files under the `bank.project` package. Ensure the original `icon` folder is on the runtime classpath.
5. Start MySQL and prepare the `bankSystem` database with the tables, views, and stored procedures used by the Java code. The Workbench model is in `database/New Database.mwb`.
6. Update `dbConnection.java` with your MySQL username, password, and connection URL. The default database is `bankSystem` on `localhost:3306`.
7. Run `bank.project.SplashScreen` to start the application, or `bank.project.Login` to open the login screen directly.

## Project Files

Java source files are in `src/`.

| File | Purpose |
| --- | --- |
| `SplashScreen.java`, `Login.java` | Startup and login |
| `Signup.java`, `Signup2.java`, `Signup3.java` | Account registration |
| `main_Class.java` | ATM transaction menu |
| `Deposit.java`, `Withdrawl.java`, `FastCash.java` | Transaction screens |
| `BalanceEnquiry.java`, `mini.java`, `Pin.java` | Balance, statement, and PIN screens |
| `dbConnection.java` | MySQL connection |
| `database/New Database.mwb` | MySQL Workbench database model |

## Project Scope

The repository currently does not include the `icon` assets, dependency JARs, or a SQL initialization script. Add these before running the app.

Built as an educational OOP and database project. Database-backed features require compatible tables, views, and stored procedures. This project is a learning prototype and is not intended for real banking use.

## License

[MIT](LICENSE).
