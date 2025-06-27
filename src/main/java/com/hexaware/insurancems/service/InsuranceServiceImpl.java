package com.hexaware.insurancems.service;

import com.hexaware.insurancems.beans.Claim;
import com.hexaware.insurancems.beans.Client;
import com.hexaware.insurancems.beans.Payment;
import com.hexaware.insurancems.beans.Policy;
import com.hexaware.insurancems.dao.*;

import java.util.List;

public class InsuranceServiceImpl implements InsuranceService {

    private final IPolicyService policyDAO = new PolicyDAOImpl();
    private final IClientDAO clientDAO = new ClientDAOImpl();
    private final IClaimDAO claimDAO = new ClaimDAOImpl();
    private final IPaymentDAO paymentDAO = new PaymentDAOImpl();

    public boolean createPolicy(Policy policy) {
        return policyDAO.createPolicy(policy);
    }

    public Policy getPolicy(int policyId) {
        return policyDAO.getPolicy(policyId);
    }

    public List<Policy> getAllPolicies() {
        return policyDAO.getAllPolicies();
    }

    public boolean updatePolicy(Policy policy) {
        return policyDAO.updatePolicy(policy);
    }

    public boolean deletePolicy(int policyId) {
        return policyDAO.deletePolicy(policyId);
    }

   
    public boolean registerClient(Client client) {
        return clientDAO.registerClient(client);
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    public Client getClientById(int clientId) {
        return clientDAO.getClientById(clientId);
    }

    public boolean updateClient(Client client) {
        return clientDAO.updateClient(client);
    }

    public boolean deleteClient(int clientId) {
        return clientDAO.deleteClient(clientId);
    }

    
    public boolean fileClaim(Claim claim) {
        return claimDAO.fileClaim(claim);
    }

    public boolean updateClaimStatus(int claimId, String status) {
        return claimDAO.updateClaimStatus(claimId, status);
    }

    public List<Claim> getClaimsByClientId(int clientId) {
        return claimDAO.getClaimsByClientId(clientId);
    }

    public Claim getClaimById(int claimId) {
        return claimDAO.getClaimById(claimId);
    }

    public boolean addPayment(Payment payment) {
        return paymentDAO.addPayment(payment);
    }

    public List<Payment> getPaymentsByClientId(int clientId) {
        return paymentDAO.getPaymentsByClientId(clientId);
    }
}
