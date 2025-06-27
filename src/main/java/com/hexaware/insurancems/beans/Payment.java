package com.hexaware.insurancems.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private LocalDateTime paymentDate;
    private BigDecimal paymentAmount;
    private Client client;

    public Payment() {}

    public Payment(int paymentId, LocalDateTime paymentDate,
                   BigDecimal paymentAmount, Client client) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.client = client;
    }

    public int getPaymentId() { return paymentId; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public BigDecimal getPaymentAmount() { return paymentAmount; }
    public Client getClient() { return client; }

    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public void setPaymentAmount(BigDecimal paymentAmount) { this.paymentAmount = paymentAmount; }
    public void setClient(Client client) { this.client = client; }

    @Override
    public String toString() {
        return "Payment[" + paymentId + ", Amount: " + paymentAmount +
               ", Date: " + paymentDate + ", Client ID: " +
               (client != null ? client.getClientId() : "Unknown") + "]";
    }
}
