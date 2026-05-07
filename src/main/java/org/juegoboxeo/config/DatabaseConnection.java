package org.juegoboxeo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/BDJuegoBoxeo";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";
    
    /**
     * Conecta con la base de datos
     *
     * @return conexión activa
     * @throws SQLException si falla la conexión
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}