package bancouabcs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registrar extends JFrame {

    JFrame registrarWindow;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JButton aceptar;
    public static Statement stat;
    int cont = 0;
    JTextField usuario = new JTextField("");
    JTextField apellido = new JTextField("");
    JTextField segundoapellido = new JTextField("");
    JTextField correo = new JTextField("");
    JTextField contraseña = new JTextField("");
    JTextField cpassword = new JTextField("");
    JLabel regi1 = new JLabel();
    JLabel regi2 = new JLabel();
    JLabel regi3 = new JLabel();
    JLabel regi5 = new JLabel();
    JLabel regi6 = new JLabel();

    public void Validar() {
        if (usuario.getText().equals("")) {
            regi1.setVisible(true);
            cont++;
        } else {
            regi1.setVisible(false);
        }
        if (apellido.getText().equals("")) {
            regi2.setVisible(true);
            cont++;
        } else {
            regi2.setVisible(false);
        }
        if (correo.getText().equals("")) {
            regi3.setVisible(true);
            cont++;
        } else {
            regi3.setVisible(false);
        }
        if (contraseña.getText().equals("")) {
            regi5.setVisible(true);
            cont++;
        } else {
            regi5.setVisible(false);
        }
        
         if (cpassword.getText().equals("")  )  {
            regi6.setVisible(true);
            cont++;
        } else {
            regi6.setVisible(false);
        }
    }

    public Registrar() {

        registrarWindow = new JFrame("Registro");
        registrarWindow.setSize(600, 400);
        registrarWindow.setLocationRelativeTo(null);
        registrarWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrarWindow.setUndecorated(true);
        registrarWindow.setLayout(null);

        //OCULTAR AVISO
        regi1.setVisible(false);
        regi2.setVisible(false);
        regi3.setVisible(false);
        regi5.setVisible(false);
         regi6.setVisible(false);

        // PANEL DE PRESENTACION
        panelPresentacion = new JPanel();
        panelPresentacion.setSize(registrarWindow.getWidth(), registrarWindow.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);

        // BOTON ACEPTAR
        aceptar = new JButton();
        aceptar.setContentAreaFilled(false);
        aceptar.setFocusable(false);
        aceptar.setBorder(null);
        aceptar.setOpaque(false);
        aceptar.setBackground(new Color(0, 0, 0, 0));
        aceptar.setIcon(new ImageIcon(("aceptar.png")));
        aceptar.setBounds(300, 250, 200, 110);

        // BOTON REGRESAR
        JButton cancelar = new JButton();
        cancelar.setFocusable(false);
        cancelar.setBorder(null);
        cancelar.setOpaque(false);
        cancelar.setBackground(new Color(0, 0, 0, 0));
        cancelar.setIcon(new ImageIcon(("cancelar.png")));
        cancelar.setBounds(100, 250, 200, 110);

        // FONDO DE PRESENTACION
        fondoPresentacion = new JLabel();
        fondoPresentacion.setIcon(new ImageIcon("registrar.jpg"));
        fondoPresentacion.setBounds(0, 0, registrarWindow.getWidth(), registrarWindow.getHeight());
        fondoPresentacion.setVisible(true);
        fondoPresentacion.add(aceptar);
        fondoPresentacion.add(cancelar);
        panelPresentacion.add(fondoPresentacion, 0);

        // BOTON CANCELAR
        aceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                aceptar.setIcon(new ImageIcon("aceptar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                aceptar.setIcon(new ImageIcon("aceptar.png"));
            }
        });
        cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelar.setIcon(new ImageIcon("cancelar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelar.setIcon(new ImageIcon("cancelar.png"));
            }
        });
        cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                registrarWindow.dispose();
            }
        });
        // INGRESAR USUARIO Y CONTRASE A
        usuario.setBounds(180, 35, 340, 30);
        apellido.setBounds(320, 70, 200, 30);
        segundoapellido.setBounds(320, 120, 200, 30);
        correo.setBounds(320, 170, 200, 30);
        contraseña.setBounds(220, 210, 290, 30);
        cpassword.setBounds(300, 250, 210, 30);

        //VALIDAD CAMPOS
        regi1.setIcon(new ImageIcon(("error.png")));
        regi2.setIcon(new ImageIcon(("error.png")));
        regi3.setIcon(new ImageIcon(("error.png")));
        regi5.setIcon(new ImageIcon(("error.png")));
        regi6.setIcon(new ImageIcon(("error.png")));
        regi1.setBounds(520, 35, 30, 30);
        regi2.setBounds(520, 70, 30, 30);
        regi3.setBounds(520, 170, 30, 30);
        regi5.setBounds(520, 210, 30, 30);
        regi6.setBounds(520, 250, 30, 30);
        registrarWindow.add(regi1);
        registrarWindow.add(regi2);
        registrarWindow.add(regi3);
        registrarWindow.add(regi5);
        registrarWindow.add(regi6);

        registrarWindow.add(usuario);
        registrarWindow.add(apellido);
        registrarWindow.add(segundoapellido);
        registrarWindow.add(correo);
        registrarWindow.add(contraseña);
        registrarWindow.add(cpassword);
        registrarWindow.add(fondoPresentacion);
        registrarWindow.add(panelPresentacion);
        registrarWindow.setVisible(true);

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont = 0;
                Validar();
                System.out.println("contador=" + cont);
                if (cont == 0) {
                    if (usuario.getText() != null && apellido.getText() != null && contraseña.getText().equals(cpassword.getText()) ) {
                        String nombre = usuario.getText();
                        String apellido1 = apellido.getText();
                        String apellido2 = segundoapellido.getText();
                        String correo1 = correo.getText();
                        String contraseña1 = Hash.md5(contraseña.getText());

                        try {
                            stat = ConexionMySQL.conexion.createStatement();

                            String query = "INSERT INTO registro (nombre,primer_ap,segundo_ap,contraseña,correo) values('"
                                    + nombre + "','" + apellido1 + "','" + apellido2 + "','" + contraseña1 + "','" + correo1 + "')";
                            System.out.println(query);

                            stat.executeUpdate(query);
                        } catch (SQLException e1) {
                        }

                        repaint();
                        validate();
                        JOptionPane.showMessageDialog(null, "Registro completado");
                        registrarWindow.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales");
                    }
                }
            }
        });

    }
}
