/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Entity.Player;
import Objects.SuperObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author pablo
 */
public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS 
    final int originalTileSize = 16; // 16x16 Tile (player size) (and any NPC) 
    final int scale = 3; // It in fact looks like 48x48 
    
    public final int tileSize = originalTileSize * scale; // Actual Tile size 48x48
    public final int maxScreenCol = 16; 
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels 
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels 
    
    // FPS 
    final int FPS = 60;
    
    // World settings 
    public final int maxWorldCol = 168; 
    public final int maxWorldRow = 500; 
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeigth = tileSize * maxWorldRow;
    
    //Incatnce of the tile
    TileManager tileManager = new TileManager(this); 
    
    //Instanciate the keyHandler 
    KeyHandler keyHandler = new KeyHandler(); 
    
    // Here's de "Game Clock" 
    Thread gameThread; 
    
    // Entity of the player 
    public Player player = new Player(this, keyHandler); 
    
    // Collisions
    public CollisionChecker cH = new CollisionChecker(this); 
    
    // Interactuable objects
    public SuperObject obj[] = new SuperObject[30]; 
    
    public AssetSetter aSetter = new AssetSetter(this); 
    
    // Music in-game
    public Sound sonido = new Sound(); 
    
    // Instance UI class 
    public UI ui = new UI(this); 
    
    // Constructor of our game panel 
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.black); 
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    
    public void setUpGame()
            /*To print the object in the map*/
    {
        aSetter.setObject();
        //playMusic(1); 
    }
    
    public void startGameThread() 
    {
        gameThread = new Thread(this); // Le estamos pasando esta clase como un runnable 
        gameThread.start(); 
    }
    
    @Override 
    public void run(){
        double drawInterval = 1000000000/FPS;  // 0.01666 seconds prints 
        double delta = 0; 
        long lastTime = System.nanoTime(); 
        long currentTime; 
        
        // Variable to count the frame per second to ensure it is 60. 
        long timer = 0; 
        int drawCount = 0; 
        
        // Here we will be initializing our game loop
        while(gameThread != null) // Will execute until we close the gamePanel. 
        {
            // System.out.println("Nano time: "+ System.nanoTime()); It returns the time of each nano time of the game running. 
            currentTime = System.nanoTime(); 
            
            delta += (currentTime - lastTime) / drawInterval; 
            timer += (currentTime - lastTime); 
            lastTime = currentTime; 
            
            if(delta >= 1){
                update(); 
                repaint();  
                delta--; 
                drawCount++; 
            }
            
            if(timer >= 1000000000){
                //System.out.println("FPS:"+drawCount);
                drawCount = 0; 
                timer = 0; 
            }
            
        }
    }
    
    public void update () 
    {
        
        player.update();
     
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // This way we could update the drawing as the player moves 
        
        Graphics2D g2 = (Graphics2D)g; // Add functions to the game
       
        //Object 
        tileManager.draw(g2); // It's importante that the background comes before the player 
        for(int i = 0; i < obj.length ; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this); 
            }
        }
        
        //Player 
        player.draw(g2); 
        
        ui.draw(g2);
        
        g2.dispose(); // Dispose the graphic context release any sys resource using 
    }
    
    
    
    // Sonido in-game 
    public void playMusic(int i){
        sonido.setFile(i); 
        
        sonido.play(); 
        sonido.loop();
    }
    
    public void stopMusic(){
        sonido.stop(); 
    }
    
    public void playSE(int i){
        sonido.setFile(i); 
        sonido.play(); 
    }
}
