/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancouabcs;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Luis Pablo Escalante
 */
public class Ventana extends JFrame {

    int posx;
    int posy;
    int ancho;
    int alto;
    String titulo;

    public Ventana() {
        posx = 600;
        posy = 200;
        ancho = 800;
        alto = 600;
        titulo = "BancoUabcs";

        setLocation(posx, posy);
        setSize(ancho, alto);
        setTitle(titulo);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton boton1 = new JButton();
        boton1.setBounds(300, 450, 200, 40);
        add(boton1);

    }

}
