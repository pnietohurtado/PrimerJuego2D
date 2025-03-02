/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import javax.swing.JFrame;

/**
 *
 * @author pablo
 */
public class main {

    public static void main(String[] args) {
        JFrame window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        window.setResizable(false); 
        window.setTitle("Mi primer juego 2D"); 
        
        GamePanel gamePanel = new GamePanel(); 
        window.add(gamePanel); // De esta forma vamos a cargar nuestro JPanel en el window configurado. 
        
        window.pack(); // Forma muy necesaria para poder ver los ajustes del "GamePanel" 
        
        window.setLocationRelativeTo(null); 
        window.setVisible(true); 
        
        gamePanel.startGameThread();
    }
}
