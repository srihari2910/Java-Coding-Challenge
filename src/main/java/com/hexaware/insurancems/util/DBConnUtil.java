package com.hexaware.insurancems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = DBPropertyUtil.getPropertyString("db.properties");
            conn = DriverManager.getConnection(dbUrl);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("DB connection failed: " + e.getMessage());
        }

        return conn;
    }
}

