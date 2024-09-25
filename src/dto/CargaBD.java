package dto;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CargaBD {

    public static void main(String[] args) {
        String host = "localhost";
        String puerto = "5432";
        String nombreBD = "postgres";
        String usuario = "postgres";
        String contraseña = "gwxGYW!957vv";

        // Configurar DataSource
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        
        dataSource.setServerName(host);
        dataSource.setPortNumber(Integer.parseInt(puerto));
        dataSource.setDatabaseName(nombreBD);
        dataSource.setUser(usuario);
        dataSource.setPassword(contraseña);

        // Establecer la conexión
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Conexión establecida con éxito");

            // Ejecutar una consulta SELECT
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");

            
            while (rs.next()) {
                // Supongamos que tienes columnas id y nombre
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
}