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

    JFrame signUpWindow;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JButton accept;
    JTextField name, fLastName, sLastName, email, password, cpassword;
    JLabel field1 = new JLabel();
    JLabel field2 = new JLabel();
    JLabel field3 = new JLabel();
    JLabel field4 = new JLabel();
    public static Statement stmt;

    public Registrar() {

        signUpWindow = new JFrame("Registro");
        signUpWindow.setSize(600, 400);
        signUpWindow.setLocationRelativeTo(null);
        signUpWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpWindow.setUndecorated(true);
        signUpWindow.setLayout(null);

        // ERROR FIELDS
        field1.setIcon(new ImageIcon("error.png"));
        field2.setIcon(new ImageIcon("error.png"));
        field3.setIcon(new ImageIcon("error.png"));
        field4.setIcon(new ImageIcon("error.png"));
        field1.setBounds(520, 40, 30, 30);
        field2.setBounds(520, 80, 30, 30);
        field3.setBounds(520, 170, 30, 30);
        field4.setBounds(520, 215, 30, 30);
        field1.setVisible(false);
        field2.setVisible(false);
        field3.setVisible(false);
        field4.setVisible(false);
        signUpWindow.add(field1);
        signUpWindow.add(field2);
        signUpWindow.add(field3);
        signUpWindow.add(field4);

        // PANEL DE PRESENTACION
        panelPresentacion = new JPanel();
        panelPresentacion.setSize(signUpWindow.getWidth(), signUpWindow.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);

        // FIELDS DE DATOS DE USUARIO
        name = new JTextField("");
        name.setBounds(180, 35, 340, 30);
        fLastName = new JTextField("");
        fLastName.setBounds(320, 70, 200, 30);
        sLastName = new JTextField("");
        sLastName.setBounds(320, 120, 200, 30);
        email = new JTextField("");
        email.setBounds(320, 170, 200, 30);
        password = new JTextField("");
        password.setBounds(220, 210, 290, 30);
        cpassword = new JTextField("");
        cpassword.setBounds(300, 250, 210, 30);

        // BOTON ACEPTAR
        accept = new JButton();
        accept.setContentAreaFilled(false);
        accept.setFocusable(false);
        accept.setBorder(null);
        accept.setOpaque(false);
        accept.setBackground(new Color(0, 0, 0, 0));
        accept.setIcon(new ImageIcon(("aceptar.png")));
        accept.setBounds(300, 250, 200, 110);

        // BOTON CANCELAR
        JButton cancel = new JButton();
        cancel.setFocusable(false);
        cancel.setBorder(null);
        cancel.setOpaque(false);
        cancel.setContentAreaFilled(false);
        cancel.setBackground(new Color(0, 0, 0, 0));
        cancel.setIcon(new ImageIcon(("cancelar.png")));
        cancel.setBounds(100, 250, 200, 110);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUpWindow.dispose();
            }
        });

        // FONDO DE PRESENTACION
        fondoPresentacion = new JLabel();
        fondoPresentacion.setIcon(new ImageIcon("registrar.jpg"));
        fondoPresentacion.setBounds(0, 0, signUpWindow.getWidth(), signUpWindow.getHeight());
        fondoPresentacion.setVisible(true);
        fondoPresentacion.add(accept);
        fondoPresentacion.add(cancel);
        panelPresentacion.add(fondoPresentacion, 0);

        signUpWindow.add(name);
        signUpWindow.add(fLastName);
        signUpWindow.add(sLastName);
        signUpWindow.add(email);
        signUpWindow.add(password);
        signUpWindow.add(cpassword);
        signUpWindow.add(fondoPresentacion);
        signUpWindow.add(panelPresentacion);
        signUpWindow.setVisible(true);
    }

    public void Check() {
        if (name.getText().equals("")) {
            field1.setVisible(true);
        } else {
            field1.setVisible(false);
        }
        if (fLastName.getText().equals("")) {
            field2.setVisible(true);
        } else {
            field2.setVisible(false);
        }
        if (email.getText().equals("")) {
            field3.setVisible(true);
        } else {
            field3.setVisible(false);
        }
        if (password.getText().equals("")) {
            field4.setVisible(true);
        } else {
            field4.setVisible(false);
        }
    }

}
