/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author pablo
 */
public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, showCollisions, drawTime;
    public boolean showFPS; 
    public boolean catchObject; 
    
    public GamePanel gp; 
    
    public KeyHandler(GamePanel gp){
        this.gp = gp; 
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); 
        
        if(code == KeyEvent.VK_W)
        {
            this.upPressed = true; 
        }
        if(code == KeyEvent.VK_S)
        {
            this.downPressed = true; 
        }
        if(code == KeyEvent.VK_A)
        {
            this.leftPressed = true; 
        }
        if(code == KeyEvent.VK_D)
        {
            this.rightPressed = true; 
        }if(code == KeyEvent.VK_K)
        {
            if(showCollisions == false){
                this.showCollisions = true; 
            }else if(showCollisions == true){
                this.showCollisions = false; 
            }
        }
        if(code == KeyEvent.VK_ESCAPE) // In order to pause the game
        {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState; 
            }else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState; 
            }
        }
        
        if(code  == KeyEvent.VK_T)
        {
            if(drawTime == false){
                drawTime = true; 
            }else if(drawTime == true){
                drawTime = false; 
            }
        }
        
        if(code  == KeyEvent.VK_P)
        {
            if(showFPS == false){
                showFPS = true; 
            }else if(showFPS == true){
                showFPS = false; 
            }
        }
        
        if(code == KeyEvent.VK_E){ // To catch any object on the floor 
            this.catchObject = true; 
        }
        
        // In order to enter the menu State 
        if(code == KeyEvent.VK_CONTROL){
            gp.gameState = gp.titleState; 
        }
        
        
        
        
        // Title statement 
        
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNumber--; 
                if(gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 2; 
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNumber++; 
                if(gp.ui.commandNumber > 2){
                    gp.ui.commandNumber = 0; 
                }
                
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNumber == 0){
                    gp.gameState = gp.playState; 
                }else if(gp.ui.commandNumber == 1){
                    gp.gameState = gp.playState; 
                }else if(gp.ui.commandNumber == 2){
                    System.exit(0); 
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
            this.catchObject = false; 
        }
    }
    
    
    
}
