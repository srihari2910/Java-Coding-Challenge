package com.hexaware.insurancems.beans;

public class Client {
    private int clientId;
    private String clientName;
    private String contactInfo;
    private Policy policy;

    public Client() {}

    public Client(int clientId, String clientName, String contactInfo, Policy policy) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.contactInfo = contactInfo;
        this.policy = policy;
    }

    public int getClientId() { return clientId; }
    public String getClientName() { return clientName; }
    public String getContactInfo() { return contactInfo; }
    public Policy getPolicy() { return policy; }

    public void setClientId(int clientId) { this.clientId = clientId; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public void setPolicy(Policy policy) { this.policy = policy; }

    @Override
    public String toString() {
        return "Client[" + clientId + ", " + clientName + ", " + contactInfo +
               ", Policy ID: " + (policy != null ? policy.getPolicyId() : "None") + "]";
    }
}
