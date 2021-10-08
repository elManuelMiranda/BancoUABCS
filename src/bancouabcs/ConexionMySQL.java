package bancouabcs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionMySQL {

    public static Connection conexion;
    public static Statement sentencia;
    public static ResultSet resultado;

    public static void ConectarBasedeDatos() {
        try {
            final String Controlador = "org.gjt.mm.mysql.Driver";
            Class.forName(Controlador);
            final String url_bd = "jdbc:mysql://localhost/examen_u2_u3?characterEncoding=utf8";
            conexion = DriverManager.getConnection(url_bd, "root", "");
            sentencia = conexion.createStatement();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DesconectarBasedeDatos() {
        try {
            if (conexion != null) {
                if (sentencia != null) {
                    sentencia.close();
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return conexion;
    }
}
