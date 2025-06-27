package com.hexaware.insurancems.beans;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Claim {
    private int claimId;
    private String claimNumber;
    private LocalDate dateFiled;
    private BigDecimal claimAmount;
    private String status;
    private Policy policy;
    private Client client;

    public Claim() {}

    public Claim(int claimId, String claimNumber, LocalDate dateFiled,
                 BigDecimal claimAmount, String status,
                 Policy policy, Client client) {
        this.claimId = claimId;
        this.claimNumber = claimNumber;
        this.dateFiled = dateFiled;
        this.claimAmount = claimAmount;
        this.status = status;
        this.policy = policy;
        this.client = client;
    }

    public int getClaimId() { return claimId; }
    public String getClaimNumber() { return claimNumber; }
    public LocalDate getDateFiled() { return dateFiled; }
    public BigDecimal getClaimAmount() { return claimAmount; }
    public String getStatus() { return status; }
    public Policy getPolicy() { return policy; }
    public Client getClient() { return client; }

    public void setClaimId(int claimId) { this.claimId = claimId; }
    public void setClaimNumber(String claimNumber) { this.claimNumber = claimNumber; }
    public void setDateFiled(LocalDate dateFiled) { this.dateFiled = dateFiled; }
    public void setClaimAmount(BigDecimal claimAmount) { this.claimAmount = claimAmount; }
    public void setStatus(String status) { this.status = status; }
    public void setPolicy(Policy policy) { this.policy = policy; }
    public void setClient(Client client) { this.client = client; }

    @Override
    public String toString() {
        return "Claim[" + claimId + ", " + claimNumber + ", " + dateFiled +
               ", Amount: " + claimAmount + ", Status: " + status +
               ", Client: " + (client != null ? client.getClientName() : "Unknown") + "]";
    }
}
