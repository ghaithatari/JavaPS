package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App extends Application
{


    static SQLConnector sqlConnector;
    private TextField dealIdField;
    private TextField fromCurrencyField;
    private TextField toCurrencyField;
    private TextField timestampField;
    private TextField amountField;
    private TextArea dealsArea;

    public static void main(String[] args) throws SQLException {
        sqlConnector = new SQLConnector();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create UI controls
        Label dealIdLabel = new Label("Deal ID:");
        dealIdField = new TextField();
        Label fromCurrencyLabel = new Label("From Currency:");
        fromCurrencyField = new TextField();
        Label toCurrencyLabel = new Label("To Currency:");
        toCurrencyField = new TextField();
        Label timestampLabel = new Label("Timestamp (yyyy-MM-dd HH:mm:ss):");
        timestampField = new TextField();
        Label amountLabel = new Label("Amount:");
        amountField = new TextField();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveDeal());
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> refreshDeals());
        dealsArea = new TextArea();
        dealsArea.setEditable(false);

        // Create UI layout
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(10));
        inputGrid.add(dealIdLabel, 0, 0);
        inputGrid.add(dealIdField, 1, 0);
        inputGrid.add(fromCurrencyLabel, 0, 1);
        inputGrid.add(fromCurrencyField, 1, 1);
        inputGrid.add(toCurrencyLabel, 0, 2);
        inputGrid.add(toCurrencyField, 1, 2);
        inputGrid.add(timestampLabel, 0, 3);
        inputGrid.add(timestampField, 1, 3);
        inputGrid.add(amountLabel, 0, 4);
        inputGrid.add(amountField, 1, 4);
        HBox buttonBox = new HBox(10, saveButton, refreshButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        VBox root = new VBox(10, inputGrid, buttonBox, dealsArea);
        root.setPadding(new Insets(10));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("FX Deals");
        primaryStage.show();

        try  {

            String sql = "SELECT 1 FROM fx_deals LIMIT 1";
            Statement stmt = sqlConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // If the above query succeeds, the table exists
        } catch (SQLException e) {
            // If the above query fails, the table does not exist
//             Create the fx_deals table
            sqlConnector.createTable();
        }

        // Refresh deals on startup
        refreshDeals();
    }

    private void saveDeal() {
        // Create a new FXDeal object
        FXDeal deal;
        try {
            deal = new FXDeal(dealIdField.getText(), fromCurrencyField.getText(), toCurrencyField.getText(),
                    timestampField.getText(), Double.parseDouble(amountField.getText()));
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            return;
        }

        try  {
            String sql = "INSERT INTO fx_deals (deal_id, from_currency, to_currency, timestamp, amount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = sqlConnector.getConnection().prepareStatement(sql);
            stmt.setString(1, deal.getDealId());
            stmt.setString(2, deal.getFromCurrency());
            stmt.setString(3, deal.getToCurrency());
            stmt.setTimestamp(4, new Timestamp(deal.getTimestamp().getTime()));
            stmt.setDouble(5, deal.getAmount());
            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deal saved to database", ButtonType.OK);
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving deal to database: " + e.getMessage(),
                    ButtonType.OK);
            alert.showAndWait();
        }
        refreshDeals();
    }

    private void refreshDeals() {
        // Retrieve all deals from the database
        List<FXDeal> deals = new ArrayList<>();
        try  {
            String sql = "SELECT * FROM fx_deals";
            Statement stmt = sqlConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("deal_id");
                String from = rs.getString("from_currency");
                String to = rs.getString("to_currency");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                double amt = rs.getDouble("amount");
                FXDeal d = new FXDeal(id, from, to, timestamp, amt);
                deals.add(d);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Error retrieving deals from database: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

        // Print all deals
        StringBuilder sb = new StringBuilder();
        for (FXDeal d : deals) {
            sb.append(d).append('\n');
        }
        dealsArea.setText(sb.toString());
    }
//    public static void main( String[] args )
//    {
//        System.out.println( "Hello World!" );
//
//        String dbUrl = "jdbc:mysql://localhost:3306/fx_deals"; // "db" is the service name
//        String dbUser = "deals";
//        String dbPassword = "password";
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//
//            if (connection != null) {
//                System.out.println("Connected to the database!");
//                // Perform database operations here if needed
//                connection.close();
//            } else {
//                System.out.println("Failed to connect to the database.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
