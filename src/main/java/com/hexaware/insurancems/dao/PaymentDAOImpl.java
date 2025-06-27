package com.hexaware.insurancems.dao;

import com.hexaware.insurancems.beans.Client;
import com.hexaware.insurancems.beans.Payment;
import com.hexaware.insurancems.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements IPaymentDAO {

    @Override
    public boolean addPayment(Payment payment) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "INSERT INTO Payments (payment_date, payment_amount, client_id) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(payment.getPaymentDate()));
            ps.setBigDecimal(2, payment.getPaymentAmount());
            ps.setInt(3, payment.getClient().getClientId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Add Payment Error: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Payment> getPaymentsByClientId(int clientId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList<>();

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return payments;

            String sql = "SELECT * FROM Payments WHERE client_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, clientId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setClientId(clientId);

                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getTimestamp("payment_date").toLocalDateTime(),
                    rs.getBigDecimal("payment_amount"),
                    client
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            System.err.println("Get Payments Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }

        return payments;
    }

    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable res : resources) {
            try {
                if (res != null) res.close();
            } catch (Exception e) {
                System.err.println("Resource Closing Error: " + e.getMessage());
            }
        }
    }
}
