package dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CargaBD {

    public static void main(String[] args) {
        // Configuración de los parámetros de conexión
        String host = "localhost";
        String puerto = "5433";
        String nombreBD = "postgres"; // Nombre de la base de datos
        String usuario = "postgres";
        String contraseña = "4lt41r";

        // Cadena de conexión JDBC
        String url = "jdbc:postgresql://" + host + ":" + puerto + "/" + nombreBD;

        // Variables de conexión y consulta
        Connection conn = null;
        Statement stmt = null;

        try {
            // Establecer conexión a la base de datos
            conn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión establecida con éxito");

            // Crear una declaración (Statement)
            stmt = conn.createStatement();

            // Ejecutar una consulta SELECT para recuperar los datos de la tabla 'productos'
            String sql = "SELECT * FROM productos"; 
            ResultSet rs = stmt.executeQuery(sql);

            // Procesar el ResultSet
            while (rs.next()) {
                String codigo_producto = rs.getString("codigo_producto");
                String nombre_producto = rs.getString("nombre_producto");
                String descripcion_producto = rs.getString("descripcion_producto");
                double precio_producto = rs.getDouble("precio_producto");
                System.out.println("Código: " + codigo_producto + ", Nombre: " + nombre_producto + ", Descripción: " + descripcion_producto + ", Precio: " + precio_producto);
            }

            // Cerrar el ResultSet
            rs.close();

        } catch (SQLException e) {
            // Manejo de errores de conexión o de la consulta SQL
            System.out.println("Error al establecer la conexión o ejecutar la consulta: " + e.getMessage());
        } finally {
            // Cerrar recursos, siempre asegurando que se cierren adecuadamente
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}