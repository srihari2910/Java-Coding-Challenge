package com.hexaware.insurancems.service;

import com.hexaware.insurancems.beans.*;

import java.util.List;

public interface InsuranceService {

    // Policy
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId);
    List<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId);

    // Client
    boolean registerClient(Client client);
    List<Client> getAllClients();
    Client getClientById(int clientId);
    boolean updateClient(Client client);
    boolean deleteClient(int clientId);

    // Claim
    boolean fileClaim(Claim claim);
    boolean updateClaimStatus(int claimId, String status);
    List<Claim> getClaimsByClientId(int clientId);
    Claim getClaimById(int claimId);

    // Payment
    boolean addPayment(Payment payment);
    List<Payment> getPaymentsByClientId(int clientId);
}
