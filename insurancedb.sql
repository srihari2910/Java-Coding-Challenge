CREATE DATABASE INSURANCEDB;
USE INSURANCEDB;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    CHECK (role IN ('Admin', 'Agent', 'Client'))
);

CREATE TABLE Policies (
    policy_id INT AUTO_INCREMENT PRIMARY KEY,
    policy_name VARCHAR(255) NOT NULL,
    policy_type VARCHAR(100),
    coverage_amount DECIMAL(12, 2) NOT NULL,
    premium DECIMAL(10, 2) NOT NULL,
    start_date DATE,
    end_date DATE
);

CREATE TABLE Clients (
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255),
    policy_id INT,
    FOREIGN KEY (policy_id) REFERENCES Policies(policy_id) ON DELETE SET NULL
);

CREATE TABLE Claims (
    claim_id INT AUTO_INCREMENT PRIMARY KEY,
    claim_number VARCHAR(100) NOT NULL UNIQUE,
    date_filed DATE NOT NULL,
    claim_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending',
    policy_id INT,
    client_id INT,
    CHECK (status IN ('Pending', 'Approved', 'Rejected')),
    FOREIGN KEY (policy_id) REFERENCES Policies(policy_id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES Clients(client_id) ON DELETE CASCADE
);

CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    payment_date DATETIME NOT NULL,
    payment_amount DECIMAL(10, 2) NOT NULL,
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES Clients(client_id) ON DELETE CASCADE
);


INSERT INTO Users (username, password, role) VALUES
('admin1', 'admin@123', 'Admin'),
('agent1', 'agent@123', 'Agent'),
('client1', 'client@123', 'Client');

INSERT INTO Policies (policy_name, policy_type, coverage_amount, premium, start_date, end_date) VALUES
('Life Shield Plan', 'Life', 1000000.00, 12000.00, '2024-01-01', '2034-01-01'),
('Auto Secure Plus', 'Auto', 500000.00, 8000.00, '2024-06-01', '2025-06-01'),
('Health Protect', 'Health', 300000.00, 5000.00, '2024-03-01', '2029-03-01');

INSERT INTO Clients (client_name, contact_info, policy_id) VALUES
('aswin', 'aswin@example.com', 1),
('vijay', 'vijay@example.com', 2),
('srinath', 'srinath@example.com', 3);

INSERT INTO Claims (claim_number, date_filed, claim_amount, status, policy_id, client_id) VALUES
('CLM10001', '2024-06-15', 20000.00, 'Approved', 1, 1),
('CLM10002', '2024-06-20', 15000.00, 'Pending', 2, 2),
('CLM10003', '2024-06-25', 10000.00, 'Rejected', 3, 3);

INSERT INTO Payments (payment_date, payment_amount, client_id) VALUES
(NOW(), 12000.00, 1),
(NOW(), 8000.00, 2),
(NOW(), 5000.00, 3);

