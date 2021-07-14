# Banking-Project


## Minimum Technical Requirements

•	The front-end view will use JavaScript and AJAX to call server-side components. The web pages should look **presentable** (utilizing a combination of CSS and Bootstrap)

•	Project code and package as to be appropriately structured (no spaghetti code) and utilize appropriate design patterns.

•	All tables must be in at least 3rd Normal Form.

•	The back-end system shall use JDBC to connect to a PostgreSQL database.

•	Logging has to be implemented, utilizing an external logging framework (e.g. Log4J)

•	Unit test coverage, utilizing Mockito and JUnit

## Stretch Goal (Optional requirements)

•	The application will send an email to customers letting them know that their account has been approved 


## Description
​
   The Bank app is a browser-based application that simulates banking operations. A customer can apply for an account, view their balance, and make withdrawals and deposits. An employee can approve or deny customers and accounts.



# Welcome to Revature Bank!
## Details
This banking program is designed to allow potential customers the ability to sign-up for a user account. Once the user has signed up for an account, they may go ahead and pre=apply for a bank account
with the bank. Potential customer's may choose what type of banking account they wish to apply for , the starting deposit, and they can even apply for multiple accounts. 
Employees of Revature Bank may review a customer's account and decide whether or not the customer should be approved or denied. 

Approved customer's will be able to enjoy the benefits of banking with Revature. This includes being able to deposit money, withdraw money, and post transfers to other accounts.



##### Technical Details
This program follows the Three-Tier Architecture design pattern. To enable persistence of data, a database is used to store customer, employee, and bank account data. The frontend of the system is designed using HTML, Javascript, and Javalin to allow an enchanced user experience.
