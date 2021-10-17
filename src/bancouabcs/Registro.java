package bancouabcs;

import static bancouabcs.Ventana.cont1;
import static bancouabcs.Ventana.stat;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.GroupLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Registro {

    JFrame historyWindow;
    JFrame newAccountWindow;
    JTable table;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JPanel panelhistorial;
    JButton depositar;
    JButton back;
    public static Statement stmt;

    public Registro() {

        JLabel PRUEBA = new JLabel("hola");
        JLabel balance = new JLabel("crayola");
        panelhistorial = new JPanel();
        JButton transaccion = new JButton("");

        PRUEBA.setOpaque(true);
        PRUEBA.setBackground(Color.yellow);
        panelhistorial.add(PRUEBA);

        balance.setOpaque(true);
        balance.setBackground(Color.red);
        panelhistorial.add(balance);
        //BOTON TRANSACCION
        transaccion.setFocusable(false);
        transaccion.setBorder(null);
        transaccion.setOpaque(false);
        transaccion.setIcon(new ImageIcon(("transaccion.png")));
        panelhistorial.add(transaccion);

        GroupLayout layout = new GroupLayout(panelhistorial);
        panelhistorial.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(PRUEBA)
                        .addComponent(balance)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(transaccion))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(PRUEBA)
                                .addComponent(balance)
                                .addComponent(transaccion))
        );

        JButton closeNRWindow = new JButton();
        closeNRWindow.setFocusable(false);
        closeNRWindow.setBorder(null);
        closeNRWindow.setOpaque(false);
        closeNRWindow.setContentAreaFilled(false);
        closeNRWindow.setBackground(new Color(0, 0, 0, 0));
        closeNRWindow.setIcon(new ImageIcon(("cerrar.png")));
        closeNRWindow.setBounds(420, 5, 50, 50);
        closeNRWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeNRWindow.setIcon(new ImageIcon("cerrar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeNRWindow.setIcon(new ImageIcon("cerrar.png"));
            }
        });
        closeNRWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                newAccountWindow.dispose();
            }
        });

        JPanel panelTransaccion = new JPanel();
        panelTransaccion.setSize(500, 400);
        panelTransaccion.setLocation(0, 0);
        panelTransaccion.setLayout(null);
        panelTransaccion.setVisible(true);
        JLabel fondoTransaccion = new JLabel();
        fondoTransaccion.setBounds(0, 0, 500, 400);
        fondoTransaccion.setVisible(true);
        fondoTransaccion.setIcon(new ImageIcon(("Depositar.jpg")));
        panelTransaccion.add(fondoTransaccion, 0);

        // ATRIBUTOS DE LA VENTANA
        historyWindow = new JFrame("Historial");
        historyWindow.setSize(600, 400);
        historyWindow.setLocationRelativeTo(null);
        historyWindow.setUndecorated(false);

        // TABLA CON HISTORIAL DEL USUARIO
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable();
        table.setBounds(10, 10, 1, 1);
        model.addColumn("Fechas");
        model.addColumn("Descripcion");
        model.addColumn("Depositos");
        model.addColumn("Retiros");
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        String[] dato = new String[4];

        JScrollPane scroll = new JScrollPane(table);
        historyWindow.add(scroll, BorderLayout.CENTER);
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

        JTextField cuentaDestino = new JTextField();
        cuentaDestino.setBounds(150, 65, 200, 25);
        JTextField cantidad = new JTextField();
        cantidad.setBounds(150, 160, 200, 25);
        JTextField descripcion = new JTextField();
        descripcion.setBounds(150, 260, 200, 25);
        // BOTON REALIZAR TRANSACCION
        JButton realizarTX = new JButton("Crear cuenta");
        realizarTX.setIcon(new ImageIcon("realizarTX.png"));
        realizarTX.setBounds(150, 280, 200, 100);
        realizarTX.setBorder(null);
        realizarTX.setBackground(null);
        realizarTX.setContentAreaFilled(false);
        realizarTX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                realizarTX.setIcon(new ImageIcon("realizarTX2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                realizarTX.setIcon(new ImageIcon("realizarTX.png"));
            }
        });
        realizarTX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //REALIZAR TRANSACCION----------------------------------
                if (cont1 == 0) {
                    String destinyAcc = cuentaDestino.getText();
                    String deposit = cantidad.getText();
                    String description = descripcion.getText();
                    //String senderAcc = cuentaDestino.getText();
                    try {
                        stat = ConexionMySQL.conexion.createStatement();
                        String query = "INSERT INTO transactions (destinyAcc, deposit, description, email) VALUES('"
                                + destinyAcc + "','" + deposit + "','" + description + "','" + Login.u + "');";
                        System.out.println(query);
                        stat.executeUpdate(query);
                        String query2 = "UPDATE account SET balance=balance+" + deposit + " WHERE account_number="
                                + destinyAcc + ";";
                        System.out.println(query2);
                        stat.executeUpdate(query2);
                        /*String query3 = "UPDATE account SET balance=balance-" + deposit + " WHERE account_number=" 
                                + senderAcc + ";";
                        System.out.println(query3);
                        stat.executeUpdate(query3);*/
                    } catch (SQLException e1) {
                    }

                    newAccountWindow.dispose();

                } else {

                }
                //ACTUALZIAR VENTANA

                Ventana mapa = new Ventana();
                newAccountWindow.dispose();
            }
        });
        transaccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newAccountWindow = new JFrame();
                newAccountWindow.setSize(500, 400);
                newAccountWindow.setLocationRelativeTo(null);
                newAccountWindow.setLayout(null);
                newAccountWindow.setVisible(true);
                newAccountWindow.add(realizarTX);
                newAccountWindow.add(closeNRWindow);
                newAccountWindow.add(cuentaDestino);
                newAccountWindow.add(cantidad);
                newAccountWindow.add(descripcion);
                newAccountWindow.add(fondoTransaccion);

            }
        });
        historyWindow.add(panelhistorial, BorderLayout.NORTH);
        historyWindow.setVisible(true);
    }

    private void add(JPanel panelhistorial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
