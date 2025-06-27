package com.hexaware.insurancems.main;

import com.hexaware.insurancems.beans.*;
import com.hexaware.insurancems.service.InsuranceService;
import com.hexaware.insurancems.service.InsuranceServiceImpl;
import com.hexaware.insurancems.exception.PolicyNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final InsuranceService service = new InsuranceServiceImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- Insurance Management System ---");
            System.out.println("\n--- SERVICES ---");
            System.out.println("\n--- USER SERVICES ---");
            System.out.println("1. View Available Policies");
            System.out.println("2. Register as a New User");
            System.out.println("3. File a Claim");
            System.out.println("4. Make a Payment");
            System.out.println("\n--- ADMIN SERVICES ---");
            System.out.println("5. Update Policy");
            System.out.println("6. Delete Policy");
            System.out.println("7. View All Clients");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                case 1:
                    viewPolicies();
                    break;
                case 2:
                    registerClient(sc);
                    break;
                case 3:
                    fileClaim(sc);
                    break;
                case 4:
                    makePayment(sc);
                    break;
                case 5:
                    try {
                        updatePolicy(sc);
                    } catch (PolicyNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    deletePolicy(sc);
                    break;
                case 7:
                    viewAllClients();
                    break;
                case 8:
                    System.out.println("Thank you");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void viewPolicies() {
        List<Policy> policies = service.getAllPolicies();
        System.out.println("\nAvailable Policies:");
        for (Policy p : policies) {
            System.out.println(p);
        }
    }

    private static void registerClient(Scanner sc) {
        System.out.print("Enter Client Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String contact = sc.nextLine();
        System.out.print("Enter Policy ID to assign: ");
        int policyId = Integer.parseInt(sc.nextLine());

        Policy policy = service.getPolicy(policyId);
        if (policy == null) {
            System.out.println("Policy not found.");
            return;
        }

        Client client = new Client(0, name, contact, policy);
        boolean success = service.registerClient(client);
        System.out.println(success ? "Client registered." : "Client registration failed.");
    }

    private static void fileClaim(Scanner sc) {
        System.out.print("Enter Client ID: ");
        int clientId = Integer.parseInt(sc.nextLine());
        Client client = service.getClientById(clientId);

        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.print("Enter Claim Number: ");
        String number = sc.nextLine();
        System.out.print("Enter Claim Amount: ");
        BigDecimal amount = new BigDecimal(sc.nextLine());
        System.out.print("Enter Status (Pending/Approved/Rejected): ");
        String status = sc.nextLine();

        Claim claim = new Claim(0, number, LocalDate.now(), amount, status, client.getPolicy(), client);
        boolean success = service.fileClaim(claim);
        System.out.println(success ? "Claim filed." : "Failed to file claim.");
    }

    private static void makePayment(Scanner sc) {
        System.out.print("Enter Client ID: ");
        int clientId = Integer.parseInt(sc.nextLine());
        Client client = service.getClientById(clientId);

        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.print("Enter Payment Amount: ");
        BigDecimal amount = new BigDecimal(sc.nextLine());
        Payment payment = new Payment(0, LocalDateTime.now(), amount, client);
        boolean success = service.addPayment(payment);
        System.out.println(success ? "Payment successful." : "Payment failed.");
    }

    private static void updatePolicy(Scanner sc) throws PolicyNotFoundException {
        System.out.print("Enter Policy ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        Policy existing = service.getPolicy(id);
        if (existing == null) {
            throw new PolicyNotFoundException("Policy ID not found: " + id);
        }

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Coverage Amount: ");
        BigDecimal coverage = new BigDecimal(sc.nextLine());
        System.out.print("Enter Premium: ");
        BigDecimal premium = new BigDecimal(sc.nextLine());
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        LocalDate start = LocalDate.parse(sc.nextLine());
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        LocalDate end = LocalDate.parse(sc.nextLine());

        Policy policy = new Policy(id, name, type, coverage, premium, start, end);
        boolean success = service.updatePolicy(policy);
        System.out.println(success ? "Policy updated." : "Update failed.");
    }

    private static void deletePolicy(Scanner sc) {
        System.out.print("Enter Policy ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean deleted = service.deletePolicy(id);
        System.out.println(deleted ? "Policy deleted." : "Delete failed.");
    }

    private static void viewAllClients() {
        List<Client> clients = service.getAllClients();
        System.out.println("\nAll Clients:");
        for (Client c : clients) {
            System.out.println(c);
        }
    }
    
}
