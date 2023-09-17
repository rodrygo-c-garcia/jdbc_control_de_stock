package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnect {
    private Connection connection = null;
    private Statement statement = null;
    private String url = "jdbc:mysql://localhost:3306/db_ctrl_stock?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private List<Map<String, String>> datos = new ArrayList<>();

    public DBConnect() {
    }
    /**
     * @return boolean
     */
    public boolean connectDB(){
        boolean result = false;
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, null);
            // Statement es una interfaz que se utiliza para ejecutar instrucciones SQL en una base de datos
            this.statement = connection.createStatement();
            // execute() devuelve un booleano que indica si hubo conexion o no
            result = statement.execute("SELECT * FROM producto");
            System.out.println("Connection: " + result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return result;
        }
    }

    /**
     * @return void
     */
    public void getData(){
        try {
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
                this.datos.add(fila);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return void
     */
    public void showData(){
        for(Map<String, String> dato : this.datos) {
            System.out.print(dato.get("id"));
            System.out.print(" | " + dato.get("nombre"));
            System.out.print(" | " + dato.get("descripcion"));
            System.out.println(" | " + dato.get("cantidad"));
        }
    }
}
