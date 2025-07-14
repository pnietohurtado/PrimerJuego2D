/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Conexion.Conexion;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class KeyHandler implements KeyListener{

    // Conexion para poder guardar la partida
    private Connection getConnection() throws SQLException{
        return Conexion.getConnection(); 
    }
    
    PreparedStatement pt; 
    ResultSet rs; 
    
    
    // ------------------------- Variables de esta clase -----------------------
    
    // Variables de movimiento del personaje
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    
    // Varible sobre Entorno
    public boolean showCollisions;
    public boolean drawTime;
    
    // Interacción con los objetos del mapa 
    public boolean interactEntity; 
    
    // Pausar el juego
    public boolean pauseGame = false; 
    
    // Mostrar objetos de el inventario
    public boolean showData; 
    
    public boolean rightCorner; // Para comprobar que no haya más de dos elementos en la parte superior derecha como los FPS y las coordenadas
    
    public boolean tileCollision; 
    
    public GamePanel gp; 
    
    public boolean playMusic ; // Para poder poner la música 
    
    // -------------------------------------------------------------------------
    
    
    // Contructor de la clase 
    public KeyHandler(GamePanel gp){
        this.gp = gp; 
    }
    
    // NO TOCAR 
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); 
        
        // En el caso de que el juego este pausado podemos seguir pulsando teclas
        
        if (gp.gameState == gp.playState ){
                    if(code == KeyEvent.VK_W)
                {
                    this.upPressed = true; 
                    gp.fst.Encontrar();
                    //System.out.println("Me activo con la W");
                }
                if(code == KeyEvent.VK_S)
                {
                    this.downPressed = true; 
                    gp.fst.Encontrar();
                    //System.out.println("Me activo con la S");
                }
                if(code == KeyEvent.VK_A)
                {
                    this.leftPressed = true; 
                    gp.fst.Encontrar();
                    /* Tried to make the player sprint when the key "SHIFT" is pressed
                    if(code == KeyEvent.VK_SHIFT){
                        System.out.println("FIUMMMMMM");
                        gp.player.speed = 17; 
                    }
                    */
                }
                if(code == KeyEvent.VK_D)
                {
                    this.rightPressed = true; 
                    gp.fst.Encontrar();
                }if(code == KeyEvent.VK_K)
                {
                    if(showCollisions == false){
                        this.showCollisions = true; 
                    }else if(showCollisions == true){
                        this.showCollisions = false; 
                    }
                }  
                if(code == KeyEvent.VK_F3){
                    
                    if(showData == true){
                        showData = false; 
                    }else{
                        showData = true; 
                    }
                }
                if(code == KeyEvent.VK_ESCAPE) // In order to pause the game
                {
                    gp.sonido.volume = -10.0f; 
                    gp.playMusicOnce(3); // Para poder poner la música 
                    
                    gp.gameState = gp.pauseState; 

                }
                
                // ---------------- Cambiar al modo Dios -----------------------
                if(code == KeyEvent.VK_L){
                    if(gp.tileManager.hayColision == false){
                        gp.tileManager.hayColision = true; 
                    }else{
                        gp.tileManager.hayColision = false; 
                    }
                    gp.tileManager.getTileImage();
                    //System.out.println("Valor en GP: " + gp.tileManager.hayColision);
                   
                }

                if(code  == KeyEvent.VK_T)
                {
                    if(drawTime == false){
                        drawTime = true; 
                    }else if(drawTime == true){
                        drawTime = false; 
                    }
                }

                if(code == KeyEvent.VK_E){ // To catch any object on the floor 
                    this.interactEntity = true; 
                }

                // In order to enter the menu State 
                if(code == KeyEvent.VK_CONTROL){
                    gp.gameState = gp.titleState; 
                }
                
                if(code == KeyEvent.VK_I){
                    gp.gameState = gp.inventoryState; 
                }
                
                if(code == KeyEvent.VK_H && gp.gameState == gp.playState ){
                    if(tileCollision == false){
                        this.tileCollision = true; 
                    }else if(tileCollision == true){
                        this.tileCollision = false; 
                    }
                }
                
                if(code == KeyEvent.VK_ENTER){
                    if(enterPressed == false){
                        this.enterPressed = true; 
                    }else if(enterPressed == true){
                        this.enterPressed = false; 
                    }
                }
        }
        
        
        // Pause Game 
        
        if(gp.gameState == gp.pauseState){
            
            if(code == KeyEvent.VK_W ||code == KeyEvent.VK_UP){
                gp.sonido.volume = -20.0f; 
                gp.playMusicOnce(3); // Para poder poner la música 
                
                gp.ui.commandNumber--; 
                if(gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 3; 
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.sonido.volume = -20.0f; 
                gp.playMusicOnce(3); // Para poder poner la música 
                
                gp.ui.commandNumber++; 
                if(gp.ui.commandNumber > 3){
                    gp.ui.commandNumber = 0; 
                }
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNumber == 0){
                    try { 
                        pt = getConnection().prepareStatement("INSERT INTO jugador(Pos_X, Pos_Y) VALUES(?,?)");
                        pt.setInt(1, (gp.player.worldX) );
                        pt.setInt(2, (gp.player.worldY) );
                        pt.executeUpdate(); 
                        
                        
                        PreparedStatement pt2 = getConnection().prepareStatement("INSERT INTO inventario VALUES(?,?,?,?)"); 
                        
                        gp.gameState = gp.playState; 
                    } catch (SQLException ex) {
                        Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }else if(gp.ui.commandNumber == 1){
                    gp.gameState = gp.playState; 
                }else if(gp.ui.commandNumber == 2){
                    if(this.playMusic == false){
                        gp.playMusic(1);
                        gp.sonido.volume = -20.0f; 
                        this.playMusic = true; 
                    }else if(this.playMusic == true){
                        gp.stopMusic();
                        this.playMusic = false; 
                    }
                }else if(gp.ui.commandNumber == 3){
                    System.exit(0); 
                }
            }
            
        }
        
    
        // Dialogue State 
        if(gp.gameState == gp.dialogueState){
            this.showData = false; // Para que los datos de las coordenadas no interrumpan el texto del dialogo
            if(code == KeyEvent.VK_ENTER){
                gp.sonido.stop();
                gp.gameState = gp.playState; 
            }
        }
        
        
        
        
        
        if(gp.gameState == gp.inventoryState){
            if(code == KeyEvent.VK_ENTER ){
                gp.gameState = gp.playState; 
            }
        }
        
        
        
        
        
        
        
        
        
        // Title statement 
        
        if(gp.gameState == gp.titleState){
            
            if(gp.ui.titleScreenState == 0){

                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
                {
                    gp.sonido.volume = -10.0f; 
                    gp.playMusicOnce(3); // Para poder poner la música 
                    
                    gp.ui.commandNumber--; 
                    if(gp.ui.commandNumber < 0){
                        gp.ui.commandNumber = 2; 
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
                {
                    gp.sonido.volume = -10.0f; 
                    gp.playMusicOnce(3); // Para poder poner la música 
                    
                    gp.ui.commandNumber++; 
                    if(gp.ui.commandNumber > 2){
                        gp.ui.commandNumber = 0; 
                    }

                }

                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNumber == 0){
                        gp.ui.titleScreenState = 1; 
                    }else if(gp.ui.commandNumber == 1){
                        try { 
                            pt = getConnection().prepareStatement("SELECT Pos_X, Pos_Y FROM jugador ORDER BY ID DESC LIMIT 1");
                            rs = pt.executeQuery(); 
                            
                            while(rs.next()){
                                gp.player.worldX = rs.getInt("Pos_X");
                                gp.player.worldY = rs.getInt("Pos_Y");
                            }
                            
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        gp.gameState = gp.playState; 
                    }else if(gp.ui.commandNumber == 2){
                        System.exit(0); 
                    }
                }
            }else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
                {
                    gp.sonido.volume = -10.0f; 
                    gp.playMusicOnce(3); // Para poder poner la música 
                    
                    gp.ui.commandNumber--; 
                    if(gp.ui.commandNumber < 0){
                        gp.ui.commandNumber = 3; 
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
                {
                    gp.sonido.volume = -10.0f; 
                    gp.playMusicOnce(3); // Para poder poner la música 
                    
                    gp.ui.commandNumber++; 
                    if(gp.ui.commandNumber > 3){
                        gp.ui.commandNumber = 0; 
                    }

                }

                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNumber == 0){
                        gp.ui.titleScreenState = 1; 
                    }else if(gp.ui.commandNumber == 1){
                        gp.gameState = gp.playState; 
                    }else if(gp.ui.commandNumber == 2){
                        System.exit(0); 
                    }else if(gp.ui.commandNumber == 3){
                        gp.ui.titleScreenState = 0; 
                    }
                }
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode(); 
        
        
        if(code == KeyEvent.VK_W)
        {
            this.upPressed = false; 
        }
        if(code == KeyEvent.VK_S)
        {
            this.downPressed = false; 
        }
        if(code == KeyEvent.VK_A)
        {
            this.leftPressed = false; 
        }
        if(code == KeyEvent.VK_D)
        {
            this.rightPressed = false; 
        }
        if(code == KeyEvent.VK_E){
            this.interactEntity = false; 
        }
    }
    
    
    
}
