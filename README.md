# Hospital Management System (SC2002 Project)

This is a **Hospital Management System (HMS)** designed for managing hospital operations and data using Object-Oriented Design principles. This project was developed as part of the **SC2002 Object-Oriented Design & Programming** course for AY24/25 Semester 1, SCS4, Group 6.

## Project Overview

The HMS system manages hospital functionalities for different types of users, including **Patients**, **Doctors**, **Pharmacists**, and **Administrators**. It is designed with a **Boundary-Control-Entity (BCE)** architecture to maintain high cohesion and loose coupling, ensuring maintainability, scalability, and ease of future extensions.

### Key Features

- **User Registration & Authentication**: New patients can self-register, and password management includes a password reset feature.
- **Patient Actions**: View and update personal information, schedule, reschedule, and cancel appointments, and view medical records and appointment histories.
- **Doctor Actions**: View and update patient medical records, manage appointment availability, accept/decline requests, and record appointment outcomes.
- **Pharmacist Actions**: View appointment outcome records, update prescription statuses, view/manage medication inventory, and submit replenishment requests.
- **Administrator Actions**: Manage hospital staff, view appointments, manage medication inventory, and approve pharmacist requests.
- **Security**: Passwords are encrypted with SHA3-256 for enhanced security.
- **User Interface Customization**: Different roles have unique color-coded UIs.

### Design Principles

The following design principles were applied:

1. **Single Responsibility Principle (SRP)**: Each class has a single responsibility, enhancing modularity and ease of testing.
2. **Open/Closed Principle (OCP)**: Classes are open for extension but closed for modification, achieved through abstraction and inheritance.
3. **Liskov Substitution Principle (LSP)**: Subtypes are substitutable for base types, ensuring role-specific behavior in user classes.
4. **Interface Segregation Principle (ISP)**: Specific interfaces are used for different user roles, reducing unnecessary dependencies.
5. **Dependency Inversion Principle (DIP)**: Higher-level modules depend on abstractions rather than concrete implementations.

### Design Patterns

- **Boundary-Control-Entity (BCE)**: Ensures separation of concerns by organizing classes into Boundary (UI), Control, and Entity layers.
- **Factory Pattern**: Used in the login process to encapsulate object creation, enabling flexible and extendable authentication.

## Project Structure

- **Boundary:** Handles user interactions
- **Control:** Manages the application's logic and coordination
- **Entity:** Represents core data and maintains business rules


## Additional Features

- **Backtracking**: Users can navigate back to previous menus.
- **Billing System**: Patients are billed based on appointment details and prescribed medications.
- **Color-Coded UI**: Each user role has a distinct color theme for a better user experience.

## Testing

Extensive testing was conducted, covering scenarios such as:

- **Login System**: Tested for successful login, password changes, and invalid credentials.
- **Patient, Doctor, Pharmacist, and Administrator Actions**: Verified the functionality of each user role, including managing appointments, medical records, inventory, and staff.

## Usage

### Prerequisites

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (Version 7 or higher)
- IDE (Eclipse, IntelliJ, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AuroraVane/SC2002_Project.git
2. Open the project in your IDE.
3. Run the **Hospital** class to start the HMS system

## Contributors
1. Gay Ming Kai
2. Liang Qiaoyun
3. Lee Han Hua
4. Aditi Vasudevan

## Links
- **Github Repository:** https://github.com/AuroraVane/SC2002_Project.git
- **Project Video:** https://youtu.be/yoPL52y4f9I

## Reflection
This project has been a valuable learning experience in OOP and teamwork. Challenges included creating a comprehensive UML diagram, task coordination, and applying OOP principles. We developed a functional skeleton first and iteratively improved the design, leading to a robust, user-friendly HMS.

