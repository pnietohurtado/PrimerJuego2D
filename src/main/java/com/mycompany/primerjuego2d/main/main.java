/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author pablo
 */
public class main {

    public static void main(String[] args) {
        Container con; 
        JLabel pictureLabel; 
        ImageIcon image; 
        
        JFrame window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        window.setResizable(false); 
        window.setTitle("Mi primer juego 2D"); 
        
        con = window.getContentPane(); 
        
        GamePanel gamePanel = new GamePanel(); 
        con.add(gamePanel); 
        
        pictureLabel = new JLabel();
        image = new ImageIcon(".//res//Fondo//pokemon.png"); 
        pictureLabel.setIcon(image);
        gamePanel.add(pictureLabel); 
        
        window.add(gamePanel); // De esta forma vamos a cargar nuestro JPanel en el window configurado. 
        
        window.pack(); // Forma muy necesaria para poder ver los ajustes del "GamePanel" 
    
        
        window.setLocationRelativeTo(null); 
        window.setVisible(true); 
        
        gamePanel.setUpGame(); 
        gamePanel.startGameThread();
    }
}
