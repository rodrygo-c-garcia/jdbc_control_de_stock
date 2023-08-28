package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/control_stock?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        Connection connection = DriverManager.getConnection(url, user, null);
    }
}