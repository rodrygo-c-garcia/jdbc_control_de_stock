package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnect {
    // Connection es una interfaz que se utiliza para conectarse a una base de datos
    private Connection connection = null;
    // Statement es una interfaz que se utiliza para ejecutar instrucciones SQL en una base de datos
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            showData();
        }
    }

    /**
     * @return void
     */
    public void showData(){
        for(Map<String, String> date : this.datos) {
            System.out.print(date.get("id"));
            System.out.print(" | " + date.get("nombre"));
            System.out.print(" | " + date.get("descripcion"));
            System.out.println(" | " + date.get("cantidad"));
        }
    }

    public void saveData(){
        try {
            // RETURN_GENERATED_KEYS es una constante que se utiliza para obtener el id del registro que se acaba de insertar
            this.statement.execute("INSERT INTO producto (nombre, descripcion, cantidad) VALUES ('VASO', 'Vaso de TE', 23), ('PLATO', 'Plato de comer', 10)", Statement.RETURN_GENERATED_KEYS);
            // obtenemos el id del registro que se acaba de insertar
            ResultSet resultSet = this.statement.getGeneratedKeys();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt(1));
            }
            //this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Datos guardados");
        }
    }
}
