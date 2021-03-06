package bancouabcs;

import static bancouabcs.Ventana.cont1;
import static bancouabcs.Ventana.stat;
import static bancouabcs.Ventana.stmt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Registro extends JFrame {

    JFrame historyWindow;
    JFrame newAccountWindow;
    JTable table;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JPanel panelhistorial;
    JButton depositar;
    JButton back;

    public static Statement stmt;
    public static int cont2;
    JTextField cuentaDestino = new JTextField();
    JTextField cantidad = new JTextField();
    JTextField descripcion = new JTextField();
    JLabel regi1 = new JLabel();
    JLabel regi2 = new JLabel();
    JLabel regi3 = new JLabel();

    public Registro() {

        panelhistorial = new JPanel();

        //MOSTRAR CUENTAS
        try {
            stmt = ConexionMySQL.conexion.createStatement();

            String query2 = "SELECT date FROM transactions WHERE id=(SELECT max(id) FROM transactions WHERE account_number='" + Ventana.dato + "')";
            System.out.println(query2);
            ResultSet rs2 = stmt.executeQuery(query2);

            String[] dato2 = new String[1];
            while (rs2.next()) {
                dato2[0] = rs2.getString(1);

                JLabel fecha = new JLabel();
                fecha.setText("Ultimo movimiento: " + rs2.getString(1));
                fecha.setBounds(20, 5, 500, 100);
                fecha.setFont(new Font("Candra", 0, 20));
                fecha.setForeground(new Color(215, 173, 71));
                panelhistorial.add(fecha);
            }
            String query = "SELECT account_number,balance,type FROM account WHERE email='" + Login.u + "'" + "AND " + "account_number=" + "'" + Ventana.dato + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            String[] dato = new String[3];
            while (rs.next()) {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);

                JLabel usuarioname = new JLabel();
                usuarioname.setText("Numero de cuenta: " + rs.getString(1) + "  \n Saldo de la cuenta: " + rs.getString(2) + "  \n Tipo de cuenta: " + rs.getString(3));
                usuarioname.setBounds(20, 5, 500, 100);
                usuarioname.setFont(new Font("Candra", 0, 20));
                usuarioname.setForeground(new Color(215, 173, 71));
                panelhistorial.add(usuarioname);
            }

        } catch (SQLException e1) {
        }

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
        historyWindow.setSize(1280, 720);
        historyWindow.setLocationRelativeTo(null);
        historyWindow.setUndecorated(false);

        historyWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Ventana inicio = new Ventana();
            }
        });

        regi1.setIcon(new ImageIcon(("error.png")));
        regi2.setIcon(new ImageIcon(("error.png")));
        regi3.setIcon(new ImageIcon(("error.png")));
        regi1.setVisible(false);
        regi2.setVisible(false);
        regi3.setVisible(false);
        regi1.setBounds(355, 65, 30, 30);
        regi2.setBounds(355, 160, 30, 30);
        regi3.setBounds(355, 260, 30, 30);

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
        model.addColumn("Cuenta destinatario");
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        String[] dato = new String[4];

        JScrollPane scroll = new JScrollPane(table);
        historyWindow.add(scroll, BorderLayout.SOUTH);
        try {
            stmt = ConexionMySQL.conexion.createStatement();
            String query = "SELECT * FROM transactions WHERE email='" + Login.u + "' AND " + "account_number='" + Ventana.dato + "';";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                dato[0] = rs.getString(2);
                dato[1] = rs.getString(4);
                dato[2] = rs.getString(5);
                dato[3] = rs.getString(3);
                model.addRow(dato);
                System.out.println(rs.getString(2) + " / " + rs.getString(4)
                        + " / " + rs.getString(5) + " / " + rs.getString(3));
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

        JButton transaccion = new JButton("");
        transaccion.setFocusable(false);
        transaccion.setBorder(null);
        transaccion.setOpaque(false);
        transaccion.setBackground(new Color(0, 0, 0, 0));
        transaccion.setIcon(new ImageIcon(("transaccion.png")));
        transaccion.setBounds(0, 0, 200, 100);

        cuentaDestino.setBounds(150, 65, 200, 25);
        cantidad.setBounds(150, 160, 200, 25);
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
                cont1 = 0;
                Validar();
                System.out.println("contador=" + cont1);
                //REALIZAR TRANSACCION----------------------------------

                if (cont1 == 0) {
                    String destinyAcc = cuentaDestino.getText();
                    String deposit = cantidad.getText();
                    String description = descripcion.getText();
                    // String senderAcc = cuentaDestino.getText();
                    try {
                        stmt = ConexionMySQL.conexion.createStatement();
                        String query = "SELECT `account_number` FROM `account` WHERE `account_number`='" + destinyAcc + "'";
                        System.out.println(query);
                        System.out.println(destinyAcc);
                        ResultSet rs = stmt.executeQuery(query);

                        if (rs.next()) {
                            cont2++;
                            try {
                                stat = ConexionMySQL.conexion.createStatement();
                                String query1 = "INSERT INTO transactions (destinyAcc, deposit, description, email, account_number) VALUES('"
                                        + destinyAcc + "','" + deposit + "','" + description + "','" + Login.u + "','" + Ventana.dato + "');";
                                System.out.println(query1);
                                stat.executeUpdate(query1);
                                String query2 = "UPDATE account SET balance=balance+" + deposit + " WHERE account_number="
                                        + destinyAcc + ";";
                                System.out.println(query2);
                                stat.executeUpdate(query2);
                                String query3 = "UPDATE account SET balance=balance-" + deposit + " WHERE account_number="
                                        + Ventana.dato + ";";
                                System.out.println(query3);
                                stat.executeUpdate(query3);
                            } catch (SQLException e1) {
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Account not found");
                            cuentaDestino.setText("");
                            cantidad.setText("");
                            descripcion.setText("");
                        }

                    } catch (SQLException e1) {
                    }
                    //------------------------
                    if (cont2 != 0) {
                        newAccountWindow.dispose();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Datos err??neos. Por favor, int??ntelo otra vez.", "ACCESO DENEGADO",
                            JOptionPane.ERROR_MESSAGE);
                }
                //ACTUALZIAR VENTANA
                if (cont2 != 0) {
                    historyWindow.dispose();
                    Ventana inicio = new Ventana();
                    Registro incia = new Registro();
                    cont2 = 0;
                }

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
                newAccountWindow.setResizable(false);
                newAccountWindow.add(realizarTX);
                newAccountWindow.add(regi1);
                newAccountWindow.add(regi2);
                newAccountWindow.add(regi3);
                newAccountWindow.add(cuentaDestino);
                newAccountWindow.add(cantidad);
                newAccountWindow.add(descripcion);
                newAccountWindow.add(fondoTransaccion);

            }
        });
        historyWindow.add(panelhistorial, BorderLayout.NORTH);
        historyWindow.setVisible(true);
        panelhistorial.add(transaccion);
    }

    public void Validar() {
        if (cuentaDestino.getText().equals("")) {
            regi1.setVisible(true);
            cont1++;
        } else {
            regi1.setVisible(false);
        }
        if (cantidad.getText().equals("")) {
            regi2.setVisible(true);
            cont1++;
        } else {
            regi2.setVisible(false);
        }
    }
}
