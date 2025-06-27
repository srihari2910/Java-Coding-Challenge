package com.hexaware.insurancems.util;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        Connection con = DBConnUtil.getConnection();

        if (con != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Failed to connect to database.");
        }
    }
}
