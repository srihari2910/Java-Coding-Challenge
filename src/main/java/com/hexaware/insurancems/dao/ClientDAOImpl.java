package com.hexaware.insurancems.dao;

import com.hexaware.insurancems.beans.Client;
import com.hexaware.insurancems.beans.Policy;
import com.hexaware.insurancems.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements IClientDAO {

    @Override
    public boolean registerClient(Client client) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "INSERT INTO Clients (client_name, contact_info, policy_id) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getClientName());
            ps.setString(2, client.getContactInfo());
            if (client.getPolicy() != null)
                ps.setInt(3, client.getPolicy().getPolicyId());
            else
                ps.setNull(3, Types.INTEGER);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Register Client Error: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();  // This is now safe!
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Client> getAllClients() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return clients;

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Clients");

            while (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policy_id"));

                Client client = new Client(
                        rs.getInt("client_id"),
                        rs.getString("client_name"),
                        rs.getString("contact_info"),
                        policy
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            System.err.println("Get Clients Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }
        return clients;
    }

    @Override
    public Client getClientById(int clientId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Client client = null;

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return null;

            String sql = "SELECT * FROM Clients WHERE client_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, clientId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policy_id"));

                client = new Client(
                        rs.getInt("client_id"),
                        rs.getString("client_name"),
                        rs.getString("contact_info"),
                        policy
                );
            }
        } catch (SQLException e) {
            System.err.println("Get Client By ID Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }

        return client;
    }

    @Override
    public boolean updateClient(Client client) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "UPDATE Clients SET client_name = ?, contact_info = ?, policy_id = ? WHERE client_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getClientName());
            ps.setString(2, client.getContactInfo());
            if (client.getPolicy() != null)
                ps.setInt(3, client.getPolicy().getPolicyId());
            else
                ps.setNull(3, Types.INTEGER);
            ps.setInt(4, client.getClientId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Client Error: " + e.getMessage());
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
    public boolean deleteClient(int clientId) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "DELETE FROM Clients WHERE client_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, clientId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Client Error: " + e.getMessage());
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

    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable res : resources) {
            try {
                if (res != null) res.close();
            } catch (Exception e) {
                System.err.println("Error closing resource: " + e.getMessage());
            }
        }
    }
}
