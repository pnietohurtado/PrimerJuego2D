/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author pablo
 */
public class main {

    public static void main(String[] args) throws IOException{

        System.out.println(APIConnect.getFromAPI("http://localhost:8000/api/PlayerGET")); 

        JFrame window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        window.setResizable(false); 
        window.setTitle("Mi primer juego 2D"); 
        
        window.setIconImage(ImageIO.read(main.class.getResource("/Objetos/Pokeball.png"))); 
        
        GamePanel gamePanel = new GamePanel(); 
        
        window.add(gamePanel); // De esta forma vamos a cargar nuestro JPanel en el window configurado. 
        
        window.pack(); // Forma muy necesaria para poder ver los ajustes del "GamePanel" 
    
        
        window.setLocationRelativeTo(null); 
        window.setVisible(true); 
        
        gamePanel.setUpGame(); 
        gamePanel.startGameThread();
    }
}
