# mediciERS+

Project 2 - Expense Reimbursement System (Team Project)

Project Description
-----
The foundation of this project is based our team lead's original application, Medici Expense Reimbursement System. A user-friendly application that manages the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

This project includes a significant enhancement of the first Medici Expense Reimbursement System that I created. The application was fully converted in the backend from Javalin Framework to Hibernate / Spring Framework and the front end features a major design overhaul to transform the overall look and feel of the application. As an team member, I was assigned for Jasime/Karma Testing in the frontend, and reported any bugs I've came across to the team lead including a follow-up solution. I also assisted with any code-related issues that arised from our team memebers.
Technologies Used
-----
* Java (Programming Language) - version 11.0.12
* Eclipse (IDE)
* Apache Maven (Project Management)
* PostgreSQL (Database)
* Apache Log4j2 (Logging Utility)
* Spring Data
* JUnit 5 (Testing Framework)
* Angular2+ (Web Framework)
* Microsoft Visual Studio Code (Source Code Editor)
* Postman (API Framework)
* Spring
* Hibernate
* Jasmine/Karma(Testing Framework)

Project Requirements
-----
An **Employee** can:
1. Login
2. View the employee home page
3. Submit a reimbursement request
4. View their pending reimbursement requests
5. View their resolved reimbursement requests
6. View their information
7. Update their information
8. Logout

A **Manager** can:
1. Login
2. View the manager home page
3. Approve/deny pending reimbursement requests
4. View all pending requests of all employees
5. View all resolved requests of all employees
6. View reimbursement requests of a specific employee
7. View all employees
8. Logout

**Bonus**
An **Employee** can:
1. Upload an image of their receipt as part of the reimbursement request
2. Receive an email when one of their reimbursement requests is resolved
3. Reset their password

A **Manager** can:
1. View the image of the receipt of a reimbursement request
2. Register an employee and send an email to that employee with their credentials

Features
-----
List of features ready and TODOs for future development
* Ability to login as an employee or manager
* Ability to view the appropriate home page upon login (employee or manager)
* Ability to submit a reimbursement request as an employee or manager (with receipt image)
* Ability to approve or deny pending reimbursement requests as a manager
* Ability to view all pending reimbursement requests as a manager
* Ability to view all resolved reimbursement requests as a manager
* Ability to view all reimbursement requests of a specific employee as a manager
* Ability to view all active employees as a manager
* Ability to create a new employee account as a manager
* Ability to view personal pending reimbursement requests as an employee
* Ability to view personal resolved reimbursement requests as an employee
* Ability to view account information as an employee or manager
* Ability to update account information as an employee or manager
* Ability to update or edit an existing reimbursement request as an employee or manager
* Ability to logout as an employee or manager

To-do list:
* Enlarge file upload (receipt image) on click for better view
* Implement responsive design throughout on front-end
* Merge my front-end with form validation into the main front-end without any merge conflicts

Getting Started
-----
git clone `https://github.com/sirbobbertin/MediciERS--Project2.git`

Contributors
-----
Team Lead: 
Michelle Ng

Team Members: 
Anthony Johnson (Unit Testing: Front-End, Form Validation: Front-End)
Deepthi Ramana (Unit Testing: Back-End)
