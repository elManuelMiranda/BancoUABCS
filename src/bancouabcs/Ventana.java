package bancouabcs;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana extends JFrame {

    JLabel fondoPresentacion;
    JFrame newRouteWindow;
    int cont = 0;
    int numregistros = 0;
    public static String coste;
    public static String[] registros = new String[10];
    public static Statement stmt;
    ResultSet rs;

    public Ventana() {

        setTitle("Banco");
        setSize(1000, 500);
        setVisible(true);
        setLocation(200, 150);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image myIcon = tk.getImage("ico.png");
        setIconImage(myIcon);

        // CERRAR NEW ROUTE WINDOW
        JButton closeNRWindow = new JButton();
        closeNRWindow.setFocusable(false);
        closeNRWindow.setBorder(null);
        closeNRWindow.setOpaque(false);
        closeNRWindow.setContentAreaFilled(false);
        closeNRWindow.setBackground(new Color(0, 0, 0, 0));
        closeNRWindow.setIcon(new ImageIcon(("cerrar.png")));
        closeNRWindow.setBounds(800, 20, 200, 100);
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
                newRouteWindow.dispose();
            }
        });

        // BOTON ACEPTAR NUEVA RUTA
        JButton accept = new JButton("Crear cuenta");
        accept.setIcon(new ImageIcon("aceptar.png"));
        accept.setBounds(150, 260, 200, 100);
        accept.setBorder(null);
        accept.setBackground(null);
        accept.setContentAreaFilled(false);
        accept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                accept.setIcon(new ImageIcon("aceptar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                accept.setIcon(new ImageIcon("aceptar.png"));
            }
        });

        // FONDOS
        JPanel panelPresentacion = new JPanel();
        panelPresentacion.setSize(this.getWidth(), this.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);
        JLabel fondoPresentacion = new JLabel();
        fondoPresentacion.setBounds(0, 0, 1000, 500);
        fondoPresentacion.setVisible(true);
        fondoPresentacion.setIcon(new ImageIcon(("interfaz.jpg")));
        panelPresentacion.add(fondoPresentacion, 0);
        
            JPanel agregarcuenta = new JPanel();
        agregarcuenta.setSize(this.getWidth(), this.getHeight());
       agregarcuenta.setLocation(0, 0);
        agregarcuenta.setLayout(null);
        agregarcuenta.setVisible(true);
        JLabel fondoagregarcuenta = new JLabel();
        fondoagregarcuenta.setBounds(0, 0, 500, 400);
        fondoagregarcuenta.setVisible(true);
        fondoagregarcuenta.setIcon(new ImageIcon(("interfaz2.jpg")));
        agregarcuenta.add(fondoagregarcuenta, 0);

        // BOTON NUEVA RUTA
        JButton newRoute = new JButton("");
        newRoute.setFocusable(false);
        newRoute.setBorder(null);
        newRoute.setOpaque(false);
        newRoute.setBackground(new Color(0, 0, 0, 0));
        newRoute.setIcon(new ImageIcon(("agregaru.png")));
        newRoute.setBounds(750, 80, 200, 100);
        newRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newRouteWindow = new JFrame();
     
                newRouteWindow.setSize(500, 400);
                newRouteWindow.setLocationRelativeTo(null);
                newRouteWindow.setLayout(null);
                newRouteWindow.setVisible(true);
                newRouteWindow.add(accept);
                newRouteWindow.add(closeNRWindow);
                newRouteWindow.add(fondoagregarcuenta);

            }
        });
        add(newRoute);
        // BOTON HISTORIAL
        JButton history = new JButton("");
        history.setFocusable(false);
        history.setBorder(null);
        history.setOpaque(false);
        history.setBackground(new Color(0, 0, 0, 0));
        history.setIcon(new ImageIcon(("historial.png")));
        history.setBounds(350,350, 200, 100);
        history.addActionListener(new ActionListener() {
            @SuppressWarnings("static-access")
            @Override
            public void actionPerformed(ActionEvent e) {

                Registro historial = new Registro();

            }
        });
        add(history);
        repaint();
        
        //BOTON TRNASACCION
       
        JButton transaccion = new JButton("");
        transaccion.setFocusable(false);
        transaccion.setBorder(null);
        transaccion.setOpaque(false);
        transaccion.setBackground(new Color(0, 0, 0, 0));
        transaccion.setIcon(new ImageIcon(("transaccion.png")));
        transaccion.setBounds(650,350, 200, 100);
        transaccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Login login = new Login();
            }
        });
add(transaccion);
        // BOTON CERRAR SESISION
        JButton logOut = new JButton("");
        logOut.setFocusable(false);
        logOut.setBorder(null);
        logOut.setOpaque(false);
        logOut.setBackground(new Color(0, 0, 0, 0));
        logOut.setIcon(new ImageIcon(("cambiar.png")));
        logOut.setBounds(750,20, 200, 100);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Login login = new Login();
            }
        });
        add(logOut);
//fondo
        add(fondoPresentacion);
        repaint();
        validate();
    }

}
