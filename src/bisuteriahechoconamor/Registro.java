package bisuteriahechoconamor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta clase me permite registrar clientes en la base de datos.
 * Aquí solamente desarrollo la funcionalidad de inserción (registro).
 * 
 * @author Lelia
 */
public class Registro {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/laboratoriosql";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {

        // Aquí estoy enviando datos de ejemplo para probar el registro
        registrarCliente("Maria Perez", "maria2@gmail.com", "123456", "5551234567");

    }

    /**
     * Este método me permite registrar un cliente nuevo en la base de datos.
     * Aquí realizo únicamente la inserción de datos.
     */
    public static void registrarCliente(String nombre, String correo, String password, String telefono) {

        Connection conexion = null;
        Statement sentencia = null;

        try {
            // Primero cargo el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Después establezco la conexión con la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            // Aquí creo mi objeto Statement para ejecutar la consulta
            sentencia = conexion.createStatement();

            // Aquí construyo la consulta SQL para insertar el nuevo cliente
            String consultaSQL = "INSERT INTO clientes (nombre, correo, password, telefono) VALUES ('"
                    + nombre + "', '"
                    + correo + "', '"
                    + password + "', '"
                    + telefono + "')";

            // Aquí ejecuto la consulta
            int filasInsertadas = sentencia.executeUpdate(consultaSQL);

            // Verifico si realmente se insertó el registro
            if (filasInsertadas > 0) {
                System.out.println("El cliente fue registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el cliente.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No encontré el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al registrar el cliente.");
            e.printStackTrace();
        } finally {
            try {
                // Aquí me aseguro de cerrar la conexión para evitar fugas de memoria
                if (sentencia != null) {
                    sentencia.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}