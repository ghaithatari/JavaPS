package org.example;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
public class SQLConnector {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/fx_deals";
    private static final String DB_USER = "deals";
    private static final String DB_PASSWORD = "password";

    private Connection conn;

    SQLConnector() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

    }
public void createTable(){

    try  {
        String sql = "CREATE TABLE fx_deals (" +
                "deal_id VARCHAR(50) NOT NULL," +
                "from_currency VARCHAR(3) NOT NULL," +
                "to_currency VARCHAR(3) NOT NULL," +
                "timestamp TIMESTAMP NOT NULL," +
                "amount DOUBLE NOT NULL," +
                "PRIMARY KEY (deal_id)" +
                ")";
        Statement stmt = getConnection().createStatement();
        stmt.executeUpdate(sql);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "fx_deals table created", ButtonType.OK);
        alert.showAndWait();
    } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error creating fx_deals table: " + ex.getMessage(),
                ButtonType.OK);
        alert.showAndWait();
    }
}
public Connection getConnection()  {
    return conn;}
}
