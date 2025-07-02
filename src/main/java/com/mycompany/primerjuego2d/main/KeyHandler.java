/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author pablo
 */
public class KeyHandler implements KeyListener{

    
    // ------------------------- Variables de esta clase -----------------------
    
    // Variables de movimiento del personaje
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
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
    
    public GamePanel gp; 
    
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
        
        if (gp.gameState == gp.playState || gp.gameState == gp.pauseState){
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
                }if(code == KeyEvent.VK_K && gp.gameState == gp.playState)
                {
                    if(showCollisions == false){
                        this.showCollisions = true; 
                    }else if(showCollisions == true){
                        this.showCollisions = false; 
                    }
                }  
                if(code == KeyEvent.VK_F3 && gp.gameState == gp.playState){
                    
                    if(showData == true){
                        showData = false; 
                    }else{
                        showData = true; 
                    }
                }
                if(code == KeyEvent.VK_ESCAPE) // In order to pause the game
                {
                    System.out.println("activa ESCAPE ");
                    //if(pauseGame == false){
                        if(gp.gameState == gp.playState){
                            gp.gameState = gp.pauseState; 
                        }else if(gp.gameState == gp.pauseState){
                            gp.gameState = gp.playState; 
                        }
                        
                        
                    /*
                    }else{
                        if(gp.gameState == gp.pauseState){
                            gp.gameState = gp.playState; 
                        }else if(gp.gameState == gp.playState){
                            gp.gameState = gp.pauseState; 
                        }
                        pauseGame = false; 
                        System.out.println(pauseGame);
                    }
                    */
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
        }
        
        
        
    
        // Dialogue State 
        if(gp.gameState == gp.dialogueState){
            this.showData = false; // Para que los datos de las coordenadas no interrumpan el texto del dialogo
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState; 
            }
        }
        
        
        
        if(gp.gameState == gp.inventoryState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState; 
            }
        }
        
        // Title statement 
        
        if(gp.gameState == gp.titleState){
            if(gp.ui.titleScreenState == 0){

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
                        gp.ui.titleScreenState = 1; 
                    }else if(gp.ui.commandNumber == 1){
                        gp.gameState = gp.playState; 
                    }else if(gp.ui.commandNumber == 2){
                        System.exit(0); 
                    }
                }
            }else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
                {
                    gp.ui.commandNumber--; 
                    if(gp.ui.commandNumber < 0){
                        gp.ui.commandNumber = 3; 
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
                {
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
