# Banking-Project

## Description
​
   The Bank app is a browser-based application that simulates banking operations. A customer can apply for an account, view their balance, and make withdrawals and deposits. An employee can approve or deny customers and accounts. This is acheived through the use of HTML, CSS, JavaScript, Javalin, Java, JDBC, and PostgreSQL.
   
   Revature Bank is a banking application for anyone to use that also has management views for customers and employees. Login to view transactions, credit card information, and account information, as well as creating new transactions, credit cards, and accounts. Managers need to approve new accounts and credit cards, and they also have the ability to freeze and unfreeze any account. Customers can deposit, withdraw and manage accounts such as checking, savings and view transaction history. Manager can view list of customers and search any customer based on transaction id, customer name and account number.

## Technologies Used
-Java  version- 8
-Javalin version- 3.13.9
-PostgreSQL version- 42.2.18
-Mockito version-  1.9.3
-Javax Mail version- 2.5.0-b01
-Log4j version- 1.2.17
-Junit version- 4.13.1
-JavaScript
-HTML 5

## Features
-Responsive HTML/JavaScript user-interface
-Ability for customers to apply for accounts, deposit or withdraw money, transfer money, or view accounts
-Ability for employees to approve or reject customer accounts
-Abiltiy for employees to view transaction logs via a file
-Cookie validation for user sessions
-Input validation for application robustness
-Persistant Storage via PostgreSQL database
-Unit testing for business logic

To-do:
-Implement ability for employees to view full details regarding a customer
- Implement user feedback when invalid input is given
- Implement forwarding

# Getting Started
Using a git-based terminal such as gitbash, first navigate to the directoy where you'd like to clone the repository

For Windows
``
$ cd C:/Users/user/my_project
``
For Linux
```
$ cd /home/user/my_project
```
 Once you have changed to the directory where you wish to clone the repositry you then use 
 
 ```
 $ git clone https://github.com/Emerixean/Online-Banking-System---P1.git
 ```
 
 Once that is done you should have the majority of the project.
 
 Next you must use PostgreSQL 10 and a RDBMS application such as DBeaver to set up a local database installation.
 
 Next locate the file included with the repository labeled "RDBMSscript.txt"
 Running the script in the text file with a RDBMS will create the necessary database tables for the project. Then you must add an employee:
 
 ```
 insert into employee_table values(1,'William','Fargo','WellsFargo','NYNY1852');
 ```
 
 This creates an employee with username WellsFargo and password NYNY1852.
 Now the final step is to set up system enviroment variables for your database credentials.
 
 For Windows:
  Use the search function on the taskbar to find "Edit the system environment variables"
  Click the button near the bottom that says "Environment Variables"
  For the system variables section click "New.."
  Name the variable TRAINING_DB_USERNAME and set its value equal to your database username
    For the system variables section click "New.."
  Name the variable TRAINING_DB_PASSWORD and set its value equal to your database password
  
  For Unix systems you will need to find proper online guides to create the same system variables.
  
  ## Usage
  Once you have your system ready, you can compile and run the program with your IDE or compiler of choice.
  
  Once you have the program running open a web browser of your choice and connect to the local host:
http://localhost:8080/welcome-menu.html

You are now free to navigate the website, create customers, apply for accounts, etc.
 
