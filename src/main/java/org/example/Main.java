package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args)  {
        String url = "jdbc:mysql://localhost:3306/control_stock?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        // siempre tratar las conexiones dentro de un try catch
        try {
            Connection connection = DriverManager.getConnection(url, user, null);
            // Statement es una interfaz que se utiliza para ejecutar instrucciones SQL en una base de datos
            Statement statement = connection.createStatement();
            // execute() devuelve un booleano, true si es un ResultSet, false si no lo es
            boolean result = statement.execute("SELECT * FROM producto");
            System.out.println(result);

            // ResultSet es una interfaz que se utiliza para representar el conjunto de resultados de una base de datos
            ResultSet resultSet = statement.executeQuery("SELECT * FROM producto");
            // mientras tengamos datos para leer, vamos imprimiendo
            while (resultSet.next()) {
                System.out.println(resultSet.getString("nombre"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}