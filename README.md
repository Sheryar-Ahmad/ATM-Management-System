**ATM Management System**

This is a complete ATM Management System I built using Java Swing and MySQL. It basically simulates how a real ATM works - you can create an account, log in, deposit or withdraw money, check your balance, and even get a mini statement of recent transactions.

**What It Does**

When you open the application, you get a splash screen and then you can either log in with your card number and PIN or sign up for a new account. The signup process is pretty detailed - it asks for personal info like name, date of birth, address, and then additional details like CNIC, income, education, and occupation.

Once you're logged in, you get access to all the main ATM functions:

**1.Deposit** - Add cash to your account

**2.Withdraw** - Take cash out (with balance check so you can't overdraw)

**3.Fast Cash** - Quick withdrawal with preset amounts (500, 1000, etc.)

**4.Balance Enquiry** - Shows your current balance

**5.Mini Statement** - Shows your last few transactions

**How It's Built**
I used a three-tier architecture for this project:

**Frontend:** Java Swing - all the screens you see (login, signup forms, the main ATM interface)
**Backend Logic:**  JDBC with stored procedures - this handles all the database operations
**Database:** MySQL - stores all user data, account details, and transaction records
**The database has four main tables:**

**1. customer** - personal info like name, DOB, address
**2. customer_details** - extra stuff like CNIC, income, occupation
**3. account** - card numbers, encrypted PINs, balances
**4. transactions** - record of every deposit and withdrawal

**Security Stuff**
I made sure to include proper security features because it's a banking app:
1.PINs are encrypted in the database (no plain text passwords)
2.Used PreparedStatement to prevent SQL injection
3.Created stored procedures for login, deposit, and withdrawal
4.Added a trigger that blocks any transaction that would make balance go negative
5.Used database views to restrict direct access to tables
6.The trigger is actually a nice touch - even if the Java code has a bug, the database itself will prevent overdrafts.

**Technologies Used**
1.Java (Swing for UI)

2.MySQL for database

3.JDBC for connecting Java with MySQL

4.Stored Procedures and Triggers for database-level logic

**How to Run This**
**If you want to try it out on your machine:**
First, set up the database. Run the Bank Database.sql file in MySQL to create all the tables, stored procedures, and triggers.
Open dbConnection.java and change the database password to your own MySQL password.
Make sure you have the MySQL Connector/J jar file added to your project classpath.
Compile all the Java files and run main_Class.java.

That's pretty much it. The app should start up with the splash screen.

**Project Files**
Here's what each file does:

File	What It Does
main_Class.java	Where the program starts
Login.java	Login screen
Signup.java, Signup2.java, Signup3.java	Three-step registration form
Deposit.java	Handles deposits
Withdrawal.java	Handles withdrawals
FastCash.java	Fast cash options
BalanceEnquiry.java	Shows balance
mini.java	Mini statement
Pin.java	PIN management
dbConnection.java	Database connection
Bank Database.sql	Complete database with schema, stored procs, and triggers

**Things I Want to Add Later**
1.Fund transfer between accounts
2.Bill payments
3.Email or SMS after transactions
4.Maybe a mobile version

**License**
This project is under the MIT license. Feel free to use it for learning or building upon it.
