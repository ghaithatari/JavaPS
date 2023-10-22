package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FXDeal {
    private String dealId;
    private String fromCurrency;
    private String toCurrency;
    private Date timestamp;
    private double amount;

    public FXDeal(String dealId, String fromCurrency, String toCurrency, String timestamp, double amount) {
        // Validate deal ID
        if (dealId == null || dealId.isEmpty()) {
            throw new IllegalArgumentException("Deal ID cannot be null or empty");
        }

        // Validate from currency
        if (fromCurrency == null || fromCurrency.isEmpty()) {
            throw new IllegalArgumentException("From currency cannot be null or empty");
        }

        // Validate to currency
        if (toCurrency == null || toCurrency.isEmpty()) {
            throw new IllegalArgumentException("To currency cannot be null or empty");
        }

        // Validate timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.timestamp = dateFormat.parse(timestamp);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid timestamp format: " + timestamp);
        }

        // Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        this.dealId = dealId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }

    public FXDeal(String dealId, String fromCurrency, String toCurrency, Date timestamp, double amount) {
        // Validate deal ID
        if (dealId == null || dealId.isEmpty()) {
            throw new IllegalArgumentException("Deal ID cannot be null or empty");
        }

        // Validate from currency
        if (fromCurrency == null || fromCurrency.isEmpty()) {
            throw new IllegalArgumentException("From currency cannot be null or empty");
        }

        // Validate to currency
        if (toCurrency == null || toCurrency.isEmpty()) {
            throw new IllegalArgumentException("To currency cannot be null or empty");
        }

        // Validate timestamp
        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }

        // Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        this.dealId = dealId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "FXDeal" + "\n" +
                "Deal ID: " + dealId + "\n" +
                "From Currency: " + fromCurrency + "\n" +
                "To Currency: " + toCurrency + "\n" +
                "Timestamp: " + dateFormat.format(timestamp) + "\n" +
                "Amount: " + amount + "\n";
    }
}
