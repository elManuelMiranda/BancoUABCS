package bancouabcs;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

    JFrame loginWindow;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JButton ingresar;
    Image imagenoriginal;
    int cont = 0;
    static String u, c, d;
    public static Statement stmt;
    JPasswordField contraseña = new JPasswordField("");

    public Login() {

        // CONEXION A LA BASE DE DATOS
        ConexionMySQL.ConectarBasedeDatos();

        // ATRIBUTOS DE LA VENTANA
        loginWindow = new JFrame("Login");
        loginWindow.setSize(626, 354);
        loginWindow.setLocationRelativeTo(null);
        loginWindow.setUndecorated(true);
        loginWindow.setLayout(null);

        // PANEL DE PRESENTACION
        panelPresentacion = new JPanel();
        panelPresentacion.setSize(loginWindow.getWidth(), loginWindow.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);

        // PROPIEDADES DEL BOTON INGRESAR
        ingresar = new JButton();
        ingresar.setContentAreaFilled(false);
        ingresar.setFocusable(false);
        ingresar.setBorder(null);
        ingresar.setOpaque(false);
        ingresar.setBackground(new Color(0, 0, 0, 0));
        ingresar.setIcon(new ImageIcon(("ingresar.png")));
        ingresar.setBounds(300, 250, 200, 110);

        // BOTON REGISTRAR
        JButton registrar = new JButton();
        registrar.setContentAreaFilled(false);
        registrar.setFocusable(false);
        registrar.setBorder(null);
        registrar.setOpaque(false);
        registrar.setBackground(new Color(0, 0, 0, 0));
        registrar.setIcon(new ImageIcon(("registrar.png")));
        registrar.setBounds(100, 250, 200, 110);

        // BOTON CERRAR
        JButton cerrar = new JButton();
        cerrar.setFocusable(false);
        cerrar.setBorder(null);
        cerrar.setOpaque(false);
        cerrar.setContentAreaFilled(false);
        cerrar.setBackground(new Color(0, 0, 0, 0));
        cerrar.setIcon(new ImageIcon(("cerrar.png")));
        cerrar.setBounds(575, 0, 60, 60);

        JButton ver = new JButton();
        ver.setFocusable(false);
        ver.setBorder(null);
        ver.setOpaque(false);
        ver.setContentAreaFilled(false);
        ver.setBackground(new Color(0, 0, 0, 0));
        ver.setIcon(new ImageIcon(("ver.png")));
        ver.setBounds(500, 220, 60, 60);

        cerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cerrar.setIcon(new ImageIcon("cerrar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cerrar.setIcon(new ImageIcon("cerrar.png"));
            }
        });
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        // IMAGEN DE PRESENTACION
        fondoPresentacion = new JLabel();
        fondoPresentacion.setIcon(new ImageIcon("menu.png"));
        fondoPresentacion.setBounds(0, 0, loginWindow.getWidth(), loginWindow.getHeight());
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion, 0);
        fondoPresentacion.add(ingresar);
        fondoPresentacion.add(registrar);
        fondoPresentacion.add(cerrar);
        fondoPresentacion.add(ver);
        // TEXTO USUARIO Y CONTRASEï¿½A
        JTextField correo = new JTextField("");
        correo.setBounds(250, 110, 200, 40);
        correo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                correo.setText("");
            }
        });

        contraseña.setEchoChar('*');
        contraseña.setBounds(320, 170, 200, 40);
        contraseña.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contraseña.setText("");
            }
        });

        ver.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                contraseña.setEchoChar((char) 0);

            }

        });

        // BOTON INGRESAR
        ingresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ingresar.setIcon(new ImageIcon("ingresar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ingresar.setIcon(new ImageIcon("ingresar.png"));
            }
        });
        ingresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {

                    u = correo.getText();
                    c = Hash.md5(contraseña.getText());

                    String SQL = "SELECT email ,password FROM users" + " WHERE email='" + u + "'AND password='"
                            + c + "'";
                    System.out.println(SQL);

                    ConexionMySQL.resultado = ConexionMySQL.sentencia.executeQuery(SQL);

                    int cont = 0;

                    while (ConexionMySQL.resultado.next()) {
                        cont++;
                    }
                    if (cont >= 1) {
                        loginWindow.setVisible(false);
                        Ventana mapa = new Ventana();
                        JOptionPane.showMessageDialog(null, "BIENVENIDO", "ACCESO CONCEDIDO",
                                JOptionPane.DEFAULT_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos erróneos. Por favor, inténtelo otra vez.", "ACCESO DENEGADO",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error base de datos");
                }
            }
        });

        // BOTON REGISTAR
        registrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registrar.setIcon(new ImageIcon("registrar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registrar.setIcon(new ImageIcon("registrar.png"));
            }
        });
        registrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Registrar registrar = new Registrar();

            }
        });
        loginWindow.add(correo);
        loginWindow.add(contraseña);
        loginWindow.add(fondoPresentacion);
        loginWindow.add(panelPresentacion);
        loginWindow.setVisible(true);
    }

}
