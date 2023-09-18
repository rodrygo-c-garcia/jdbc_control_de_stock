package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args)  {
        DBConnect dbConnect = new DBConnect();
        if(dbConnect.connectDB()) {
            dbConnect.getData();
//            dbConnect.saveData();
            dbConnect.deleteData(3);
            dbConnect.getData();
        } else {
            System.out.println("No se pudo conectar a la base de datos");
        }
    }
}