package bancouabcs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Registro {

    JFrame historyWindow;
    JTable table;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JButton back;
    public static Statement stmt;

    public Registro() {
        // ATRIBUTOS DE LA VENTANA
        historyWindow = new JFrame("Historial");
        historyWindow.setSize(600, 400);
        historyWindow.setLocationRelativeTo(null);
        historyWindow.setUndecorated(false);

        // TABLA CON HISTORIAL DEL USUARIO
        table = new JTable();
        table.setBounds(10, 10, 1, 1);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Fechas");
        model.addColumn("Descripcion");
        model.addColumn("Depositos");
        model.addColumn("Retiros");
        table.setModel(model);
        String[] titles = new String[4];
        titles[0] = "Fechas";
        titles[1] = "Descripcion";
        titles[2] = "Depositos";
        titles[3] = "Retiros";
        model.addRow(titles);
        String[] dato = new String[4];
        try {
            stmt = ConexionMySQL.conexion.createStatement();
            String query = "SELECT * FROM transactions WHERE email='" + Login.u + "';";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                dato[0] = rs.getString(2);
                dato[1] = rs.getString(4);
                dato[2] = rs.getString(5);
                dato[3] = rs.getString(6);
                model.addRow(dato);

                System.out.println(rs.getString(2) + " / " + rs.getString(4)
                        + " / " + rs.getString(5) + " / " + rs.getString(6));
            }
        } catch (SQLException e1) {
        }

        // BOTON REGRESAR
        back = new JButton();
        back.setFocusable(false);
        back.setBorder(null);
        back.setOpaque(false);
        back.setBackground(new Color(0, 0, 0, 0));
        back.setContentAreaFilled(false);
        back.setIcon(new ImageIcon(("regresar.png")));
        back.setBounds(200, 300, 200, 55);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                historyWindow.dispose();
            }
        });

        historyWindow.add(table);
        historyWindow.setVisible(true);
    }

}
