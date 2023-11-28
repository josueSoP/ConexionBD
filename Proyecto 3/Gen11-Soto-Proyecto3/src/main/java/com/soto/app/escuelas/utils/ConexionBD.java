//Esta clase se encarga de gestionar la conexión a una base de datos Oracle
package com.soto.app.escuelas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Atributos
    private static String url = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";  // URL de conexión a la BD
    private static String username = "SYSTEM";                            // Nombre de usuario de la BD
    private static String password = "admin";                            // Contraseña de usuario de la BD

    // Métodos

    // Método que establece la conexión al servidor de la BD de Oracle
    public static Connection getInstance() {
        try {
            // Intenta obtener la conexión utilizando la información proporcionada
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // En caso de error, lanza una excepción de tipo RuntimeException
            throw new RuntimeException(e);
        }
    }
}
