# Financial Portfolio Management Web Application

This web application is a comprehensive tool for managing financial portfolios, including trading stocks and managing accounts for Tax-Free Savings Accounts (TFSA) and cryptocurrency. It uses JSON Web Tokens (JWT) for authentication and a REST API for a microservices architecture, implemented in Java.

## Features

- **Authentication with JWT:** Securely authenticate users and manage sessions using JWT.
- **Stock Trading:** Buy and sell stocks through the application.
- **TFSA Management:** Manage Tax-Free Savings Accounts, including deposits and withdrawals.
- **Cryptocurrency Management:** Manage cryptocurrency accounts, including transactions and holdings.
- **Microservice Architecture:** Use REST API for a scalable and modular microservices architecture.
- **Data Visualization:** Visualize portfolio performance and market trends through charts and graphs.

## Technologies Used

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java, TomCat
- **Database:** mySQL, JDBC
- **Authentication:** JSON Web Tokens (JWT)
- **API:** REST API

## Additional Information
This project is built using a REST API microservices architecture. Each project folder represents a separate service that communicates with other services through internal API calls. The main project folders include:

FrontEnd: Manages the user interface and interacts with other services to display data and handle user actions.
CryptoTransaction: Handles transactions and management of cryptocurrency accounts.
StockTransaction: Manages stock trading functionality, including buying and selling stocks.
Signup: Handles user registration and authentication, providing secure access to the application.
The use of microservices allows for greater scalability, flexibility, and maintainability of the application, as each service can be developed, deployed, and scaled independently. This architecture enables the application to efficiently handle complex operations and provide a seamless user experience.

