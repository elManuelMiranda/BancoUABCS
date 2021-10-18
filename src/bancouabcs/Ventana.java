package bancouabcs;

import static bancouabcs.Registro.stmt;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame {

    JLabel fondoPresentacion;
    JFrame newAccountWindow;
    public static String h;
    int cont = 0;
    int numregistros = 0;
    public static String coste;
    public static String[] registros = new String[10];
    public static Statement stmt;
    private JComboBox<String> combo1;
    ResultSet rs;
    public static int cont1 = 0;
    public static int cuenta;
    public static Statement stat;
    public int nuevacuenta = 1;
  public int espacio=200;

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

        // CERRAR INICIO WINDOW
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
                newAccountWindow.dispose();
            }
        });

        // BOTON CREAR NUEVA CUENTA
        JButton acceptNewAcc = new JButton("Crear cuenta");
        acceptNewAcc.setIcon(new ImageIcon("aceptar.png"));
        acceptNewAcc.setBounds(150, 260, 200, 100);
        acceptNewAcc.setBorder(null);
        acceptNewAcc.setBackground(null);
        acceptNewAcc.setContentAreaFilled(false);
        acceptNewAcc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                acceptNewAcc.setIcon(new ImageIcon("aceptar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                acceptNewAcc.setIcon(new ImageIcon("aceptar.png"));
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

        // BOTON AGREGAR NUEVA CUENTA
        JButton newAccount = new JButton("");
        newAccount.setFocusable(false);
        newAccount.setBorder(null);
        newAccount.setOpaque(false);
        newAccount.setBackground(new Color(0, 0, 0, 0));
        newAccount.setIcon(new ImageIcon(("agregaru.png")));
        newAccount.setBounds(750, 80, 200, 100);

        combo1 = new JComboBox<String>();
        combo1.setBounds(150, 120, 200, 50);
        combo1.addItem("Cuenta Normal");
        combo1.addItem("Cuenta Premium");
        JTextField monto = new JTextField();
        monto.setBounds(150, 220, 200, 50);
        
          //VENTANA HISTORIAL OCULTA
                JButton history = new JButton("1");
                history.setFocusable(false);
                history.setBorder(null);
                history.setVisible(false);
                history.setOpaque(false);
                history.setBackground(new Color(0, 0, 0, 0));
                history.setIcon(new ImageIcon(("historial.png")));
                history.setBounds(20, 300, 200, 100);
                history.addActionListener(new ActionListener() {
                    @SuppressWarnings("static-access")
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            h = history.getText();
                        Registro historial = new Registro();
                    }
                });
                add(history);
                //VENTANA HISTORIAL 2 OCULTA
                JButton history2 = new JButton("2");
             
                history2.setFocusable(false);
                history2.setBorder(null);
                history2.setVisible(false);
                history2.setOpaque(false);
                history2.setBackground(new Color(0, 0, 0, 0));
                history2.setIcon(new ImageIcon(("historial.png")));
                history2.setBounds(220, 300, 200, 100);
                history2.addActionListener(new ActionListener() {
                    @SuppressWarnings("static-access")
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           h = history2.getText();
                        Registro historial = new Registro();
                    }
                });
                add(history2);
                 //VENTANA HISTORIAL 3 OCULTA
                JButton history3 = new JButton("3");
                history3.setFocusable(false);
                history3.setBorder(null);
                history3.setVisible(false);
                history3.setOpaque(false);
                history3.setBackground(new Color(0, 0, 0, 0));
                history3.setIcon(new ImageIcon(("historial.png")));
                history3.setBounds(420, 300, 200, 100);
                history3.addActionListener(new ActionListener() {
                    @SuppressWarnings("static-access")
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           h = history3.getText();
                        Registro historial = new Registro();
                    }
                });
                add(history3);
                 //VENTANA HISTORIAL 3 OCULTA
                JButton history4 = new JButton("4");
                history4.setFocusable(false);
                history4.setBorder(null);
                history4.setVisible(false);
                history4.setOpaque(false);
                history4.setBackground(new Color(0, 0, 0, 0));
                history4.setIcon(new ImageIcon(("historial.png")));
                history4.setBounds(620, 300, 200, 100);
                history4.addActionListener(new ActionListener() {
                    @SuppressWarnings("static-access")
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           h = history4.getText();
                        Registro historial = new Registro();
                    }
                });
                add(history4);
                 //VENTANA HISTORIAL 3 OCULTA
                JButton history5 = new JButton("5");
                history5.setFocusable(false);
                history5.setBorder(null);
                history5.setVisible(false);
                history5.setOpaque(false);
                history5.setBackground(new Color(0, 0, 0, 0));
                history5.setIcon(new ImageIcon(("historial.png")));
                history5.setBounds(820, 300, 200, 100);
                history5.addActionListener(new ActionListener() {
                    @SuppressWarnings("static-access")
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           h = history3.getText();
                        Registro historial = new Registro();
                    }
                });
                add(history5);

        newAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newAccountWindow = new JFrame();
                newAccountWindow.setSize(500, 400);
                newAccountWindow.setLocationRelativeTo(null);
                newAccountWindow.setLayout(null);
                newAccountWindow.setVisible(true);
                newAccountWindow.add(acceptNewAcc);
                newAccountWindow.add(closeNRWindow);
                newAccountWindow.add(combo1);
                newAccountWindow.add(monto);
                newAccountWindow.add(fondoagregarcuenta);
                repaint();
                validate();
            }
        });
        acceptNewAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = (int) (Math.random() * 8999 + 1000);
                cuenta = r;
                System.out.println("" + combo1.getSelectedItem().toString() + " " + monto.getText() + " " + r);
                //SUBIR CUENTA----------------------------------
                if (cont1 == 0) {
                    String balance = monto.getText();
                    String type = combo1.getSelectedItem().toString();
                      nuevacuenta++;
                       System.out.println(nuevacuenta);
                    try {
                        stat = ConexionMySQL.conexion.createStatement();
                        String query = "INSERT INTO account (account_number,balance,type,email) values('"
                                + cuenta + "','" + balance + "','" + type + "','" + Login.u + "')";
                        System.out.println(query);
                        stat.executeUpdate(query);
                    } catch (SQLException e1) {
                    }
                          
                    repaint();
                    validate();

                } else {

                }
                //ACTUALZIAR VENTANA
                dispose();
                Ventana mapa = new Ventana();
                newAccountWindow.dispose();
              
            }
        });
        add(newAccount);
        int contCuentas=0;
        //MOSTRAR CUENTAS
        try {
            stmt = ConexionMySQL.conexion.createStatement();
            String query = "SELECT account_number,balance,type FROM account WHERE email='" + Login.u + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            String[] dato = new String[3];

            while (rs.next()) {
                  contCuentas++;
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
            

                JLabel usuariocuenta = new JLabel();
                JLabel saldocuenta = new JLabel();
                JLabel tipodecuenta = new JLabel();
                usuariocuenta.setText("Num de cuenta: " + rs.getString(1));
                saldocuenta.setText("saldo de cuenta: " + rs.getString(2));
                tipodecuenta.setText("Tipo de ceunta: " + rs.getString(3));
                usuariocuenta.setBounds(20 * nuevacuenta, 150, 400, 200);
                saldocuenta.setBounds(20 * nuevacuenta, 180, 400, 200);
                tipodecuenta.setBounds(20 * nuevacuenta, 200, 400, 200);
                usuariocuenta.setFont(new Font("Candra", 0, 20));
                usuariocuenta.setForeground(new Color(215, 173, 71));
                saldocuenta.setForeground(new Color(215, 173, 71));
                tipodecuenta.setForeground(new Color(215, 173, 71));
                 nuevacuenta = nuevacuenta + 10;
                repaint();
                add(usuariocuenta);
                add(saldocuenta);
                add(tipodecuenta);
               if(contCuentas==1){
                   history.setVisible(true);
               }
               if(contCuentas==2){
                   history2.setVisible(true);
               }
               if(contCuentas==3){
                   history3.setVisible(true);
               }
               if(contCuentas==4){
                   history4.setVisible(true);
               }
               if(contCuentas==5){
                   history5.setVisible(true);
               }
              
            }
  
        } catch (SQLException e1) {
        }
              


        // BOTON HISTORIAL
        // BOTON CERRAR SESION
        JButton logOut = new JButton("");
        logOut.setFocusable(false);
        logOut.setBorder(null);
        logOut.setOpaque(false);
        logOut.setBackground(new Color(0, 0, 0, 0));
        logOut.setIcon(new ImageIcon(("cambiar.png")));
        logOut.setBounds(750, 20, 200, 100);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Login login = new Login();
            }
        });
        add(logOut);
        //MOSTRAR USUARIO LOGEADO
        try {
            stmt = ConexionMySQL.conexion.createStatement();
            String query = "SELECT first_name,last_name,sec_last_name FROM users WHERE email='" + Login.u + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            String[] dato = new String[3];

            while (rs.next()) {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);

                JLabel usuarioname = new JLabel();
                usuarioname.setText("Bienvenido: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                usuarioname.setBounds(20, 5, 500, 100);
                usuarioname.setFont(new Font("Candra", 0, 20));
                usuarioname.setForeground(new Color(215, 173, 71));
                add(usuarioname);
            }

        } catch (SQLException e1) {
        }

        add(fondoPresentacion);
        repaint();
        validate();
    }

}
