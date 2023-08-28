package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/control_stock?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        // siempre tratar las conexiones dentro de un try catch
        try {
            Connection connection = DriverManager.getConnection(url, user, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}