package com.hexaware.insurancems.dao;

import com.hexaware.insurancems.beans.Policy;
import com.hexaware.insurancems.util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PolicyDAOImpl implements IPolicyService {

    @Override
    public boolean createPolicy(Policy policy) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "INSERT INTO Policies (policy_name, policy_type, coverage_amount, premium, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, policy.getPolicyName());
            ps.setString(2, policy.getPolicyType());
            ps.setBigDecimal(3, policy.getCoverageAmount());
            ps.setBigDecimal(4, policy.getPremium());
            ps.setDate(5, Date.valueOf(policy.getStartDate()));
            ps.setDate(6, Date.valueOf(policy.getEndDate()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Create Policy Error: " + e.getMessage());
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
    public Policy getPolicy(int policyId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Policy policy = null;
        try {
            con = DBConnUtil.getConnection();
            if (con == null) return null;

            String sql = "SELECT * FROM Policies WHERE policy_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, policyId);
            rs = ps.executeQuery();

            if (rs.next()) {
                policy = new Policy(
                    rs.getInt("policy_id"),
                    rs.getString("policy_name"),
                    rs.getString("policy_type"),
                    rs.getBigDecimal("coverage_amount"),
                    rs.getBigDecimal("premium"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Get Policy Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }
        return policy;
    }

    @Override
    public List<Policy> getAllPolicies() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Policy> policies = new ArrayList<>();

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return policies;

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Policies");

            while (rs.next()) {
                Policy policy = new Policy(
                    rs.getInt("policy_id"),
                    rs.getString("policy_name"),
                    rs.getString("policy_type"),
                    rs.getBigDecimal("coverage_amount"),
                    rs.getBigDecimal("premium"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate()
                );
                policies.add(policy);
            }
        } catch (SQLException e) {
            System.err.println("Get All Policies Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Closing error: " + e.getMessage());
            }
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "UPDATE Policies SET policy_name = ?, policy_type = ?, coverage_amount = ?, premium = ?, start_date = ?, end_date = ? WHERE policy_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, policy.getPolicyName());
            ps.setString(2, policy.getPolicyType());
            ps.setBigDecimal(3, policy.getCoverageAmount());
            ps.setBigDecimal(4, policy.getPremium());
            ps.setDate(5, Date.valueOf(policy.getStartDate()));
            ps.setDate(6, Date.valueOf(policy.getEndDate()));
            ps.setInt(7, policy.getPolicyId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Policy Error: " + e.getMessage());
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
    public boolean deletePolicy(int policyId) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnUtil.getConnection();
            if (con == null) return false;

            String sql = "DELETE FROM Policies WHERE policy_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, policyId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Policy Error: " + e.getMessage());
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
