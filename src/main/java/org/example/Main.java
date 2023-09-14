package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args)  {
        String url = "jdbc:mysql://localhost:3306/control_stock?useTimezone=true&serverTimezone=UTC";
        String user = "root";

        List<Map<String, String>> datos = new ArrayList<>();
        getData(url, user, datos);
        showData(datos);
    }

    static public void getData(String url, String user, List<Map<String, String>> datos){
        // siempre tratar las conexiones dentro de un try catch
        try {
            Connection connection = DriverManager.getConnection(url, user, null);
            // Statement es una interfaz que se utiliza para ejecutar instrucciones SQL en una base de datos
            Statement statement = connection.createStatement();
            // execute() devuelve un booleano que indica si hubo conexion o no
            boolean result = statement.execute("SELECT * FROM producto");
            System.out.println("Connection: " + result);

            // ResultSet es una interfaz que se utiliza para representar el conjunto de resultados de una base de datos
            ResultSet resultSet = statement.executeQuery("SELECT * FROM producto");
            //ResultSet resultSet = statement.getResultSet();

            // mientras tengamos datos para leer, vamos imprimiendo
            while (resultSet.next()) {
                Map<String, String> fila = new HashMap<>();
                // obtenemos las filas de los registros
                fila.put("id", resultSet.getString("id"));
                fila.put("nombre", resultSet.getString("nombre"));
                fila.put("descripcion", resultSet.getString("descripcion"));
                fila.put("cantidad", resultSet.getString("cantidad"));
                // agregamos todos los registros a nuestra lista de datos
                datos.add(fila);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static public void showData(List<Map<String, String>> datos){
        for(Map<String, String> dato : datos) {
            System.out.print(dato.get("id"));
            System.out.print(" | " + dato.get("nombre"));
            System.out.print(" | " + dato.get("descripcion"));
            System.out.println(" | " + dato.get("cantidad"));
        }
    }
}