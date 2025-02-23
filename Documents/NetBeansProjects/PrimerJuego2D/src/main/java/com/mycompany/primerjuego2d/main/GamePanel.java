/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author pablo
 */
public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS 
    final int originalTileSize = 16; // 16x16 Tile (player size) (and any NPC) 
    final int scale = 3; // It in fact looks like 48x48 
    
    final int tileSize = originalTileSize * scale; // Actual Tile size 48x48
    final int maxScreenCol = 16; 
    final int maxScreenRow = 12; 
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels 
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels 
    
    // FPS 
    final int FPS = 60; 
    
    //Instanciate the keyHandler 
    KeyHandler keyHandler = new KeyHandler(); 
    
    // Here's de "Game Clock" 
    Thread gameThread; 
    
    //Set player's default position
    int playerX = 100; 
    int playerY = 100; 
    int playerSpeed = 4; 
    
    // Constructor of our game panel 
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.black); 
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    
    public void startGameThread() 
    {
        gameThread = new Thread(this); // Le estamos pasando esta clase como un runnable 
        gameThread.start(); 
    }
    
    @Override 
    public void run(){
        // Here we will be initializing our game loop
        while(gameThread != null) // Will execute until we close the gamePanel. 
        {
            
            update(); 
           
            repaint(); 
        }
    }
    
    public void update () 
    {
        if(keyHandler.upPressed == true)
        {
            playerY -= playerSpeed;  
        }
        else if(keyHandler.downPressed == true)
        {
            playerY += playerSpeed; 
        }
        else if(keyHandler.leftPressed == true)
        {
            playerX -= playerSpeed;
        }
        else if(keyHandler.rightPressed == true)
        {
            playerX += playerSpeed; 
        }
        
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // This way we could update the drawing as the player moves 
        
        Graphics2D g2 = (Graphics2D)g; // Add functions to the game
        g2.setColor(Color.white); 
        g2.fillRect(this.playerX, this.playerY, this.tileSize, this.tileSize);  
        
        g2.dispose(); // Dispose the graphic context release any sys resource using 
    }
    
}
