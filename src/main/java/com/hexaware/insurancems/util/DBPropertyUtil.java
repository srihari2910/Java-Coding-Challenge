package com.hexaware.insurancems.util;

import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getPropertyString(String fileName) {
        Properties props = new Properties();
        StringBuilder sb = new StringBuilder();

        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("File not found in classpath: " + fileName);
            }

            props.load(input);
            sb.append("jdbc:mysql://")
              .append(props.getProperty("host"))
              .append(":")
              .append(props.getProperty("port"))
              .append("/")
              .append(props.getProperty("dbname"))
              .append("?user=")
              .append(props.getProperty("username"))
              .append("&password=")
              .append(props.getProperty("password"));
        } catch (Exception e) {
            System.err.println("Unable to load DB properties: " + e.getMessage());
        }

        return sb.toString();
    }
}
