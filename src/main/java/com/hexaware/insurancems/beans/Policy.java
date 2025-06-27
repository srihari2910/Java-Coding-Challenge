package com.hexaware.insurancems.beans;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Policy {
    private int policyId;
    private String policyName;
    private String policyType;
    private BigDecimal coverageAmount;
    private BigDecimal premium;
    private LocalDate startDate;
    private LocalDate endDate;

    public Policy() {}

    public Policy(int policyId, String policyName, String policyType,
                  BigDecimal coverageAmount, BigDecimal premium,
                  LocalDate startDate, LocalDate endDate) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPolicyId() { return policyId; }
    public String getPolicyName() { return policyName; }
    public String getPolicyType() { return policyType; }
    public BigDecimal getCoverageAmount() { return coverageAmount; }
    public BigDecimal getPremium() { return premium; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    public void setPolicyId(int policyId) { this.policyId = policyId; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }
    public void setCoverageAmount(BigDecimal coverageAmount) { this.coverageAmount = coverageAmount; }
    public void setPremium(BigDecimal premium) { this.premium = premium; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        return "Policy[" + policyId + ", " + policyName + ", " + policyType +
               ", Coverage: " + coverageAmount + ", Premium: " + premium +
               ", " + startDate + " to " + endDate + "]";
    }
}
