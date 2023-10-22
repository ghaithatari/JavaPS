# JavaPS
progressSoft

# FX Deals Data Management Application

This application is designed to manage foreign exchange (FX) deals. It allows users to save and view FX deals in a MySQL database. The application is built using Java with JavaFX for the user interface and JDBC for database connectivity.

## Installation and Setup

To run the FX Deals Data Management Application, follow these steps:

1. Make sure you have Java Development Kit (JDK) installed on your system.
2. run the docker "compose.yaml"
3. Open the project in your preferred Java IDE.
4. Run the `Main.java` file to start the application.

## Application Features

- Save FX deals to the MySQL database.
- View all saved FX deals.
- Automatically create the necessary database table (`fx_deals`) if it does not already exist.

## Dependencies

- Java Development Kit (JDK)
- JavaFX
- Docker

## Database Configuration

The application connects to a MySQL database with the following configuration:

- Database URL: `jdbc:mysql://db:3306/fx_deals`
- Username: `deals`
- Password: `password`

## Test Sample

('deal1', 'USD', 'EUR', '2021-01-01 00:00:00', 1000.0)
('deal2', 'EUR', 'JPY', '2021-01-02 00:00:00', 2000.0)
('deal3', 'GBP', 'USD', '2021-01-03 00:00:00', 3000.0)
('deal4', 'USD', 'JPY', '2021-01-04 00:00:00', 4000.0)
('deal5', 'JPY', 'EUR', '2021-01-05 00:00:00', 5000.0)

