package com.hexaware.insurancems.dao;

import com.hexaware.insurancems.beans.Claim;
import com.hexaware.insurancems.beans.Client;
import com.hexaware.insurancems.beans.Policy;
import com.hexaware.insurancems.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAOImpl implements IClaimDAO {

    @Override
    public boolean fileClaim(Claim claim) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "INSERT INTO Claims (claim_number, date_filed, claim_amount, status, policy_id, client_id) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, claim.getClaimNumber());
            ps.setDate(2, Date.valueOf(claim.getDateFiled()));
            ps.setBigDecimal(3, claim.getClaimAmount());
            ps.setString(4, claim.getStatus());
            ps.setInt(5, claim.getPolicy().getPolicyId());
            ps.setInt(6, claim.getClient().getClientId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("File Claim Error: " + e.getMessage());
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
    public boolean updateClaimStatus(int claimId, String status) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "UPDATE Claims SET status = ? WHERE claim_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, claimId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Claim Status Error: " + e.getMessage());
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
    public List<Claim> getClaimsByClientId(int clientId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Claim> claims = new ArrayList<>();

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return claims;

            String sql = "SELECT * FROM Claims WHERE client_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, clientId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policy_id"));

                Client client = new Client();
                client.setClientId(rs.getInt("client_id"));

                Claim claim = new Claim(
                    rs.getInt("claim_id"),
                    rs.getString("claim_number"),
                    rs.getDate("date_filed").toLocalDate(),
                    rs.getBigDecimal("claim_amount"),
                    rs.getString("status"),
                    policy,
                    client
                );
                claims.add(claim);
            }
        } catch (SQLException e) {
            System.err.println("Get Claims by Client Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }
        return claims;
    }

    @Override
    public Claim getClaimById(int claimId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Claim claim = null;

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return null;

            String sql = "SELECT * FROM Claims WHERE claim_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, claimId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policy_id"));

                Client client = new Client();
                client.setClientId(rs.getInt("client_id"));

                claim = new Claim(
                    rs.getInt("claim_id"),
                    rs.getString("claim_number"),
                    rs.getDate("date_filed").toLocalDate(),
                    rs.getBigDecimal("claim_amount"),
                    rs.getString("status"),
                    policy,
                    client
                );
            }
        } catch (SQLException e) {
            System.err.println("Get Claim By ID Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }
        return claim;
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
