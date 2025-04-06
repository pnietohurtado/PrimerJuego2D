/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Objects.OBJ_Key;
import Objects.OBJ_Pokeball;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author pablo
 */
public class UI {

    GamePanel gp; 
    Font arial_40; 
    Graphics2D g2; 
    BufferedImage pokeImage; 
    public boolean messageOn = false; 
    public String message = ""; 
    int messageCounter = 0; 
    public int commandNumber = 0; 
    
    public UI(GamePanel gp){
        this.gp = gp; 
        
        arial_40 = new Font("Arial", Font.PLAIN, 40); 
        //OBJ_Key key = new OBJ_Key(); 
        //keyImage = key.image; 
        
        OBJ_Pokeball poke = new OBJ_Pokeball(); 
        pokeImage = poke.image; 
    }
    
    public void showMessage(String text){
        message = text; 
        messageOn = true; 
    }
    
    
    public void draw(Graphics2D g2){
        this.g2 = g2; 
        
        g2.setFont(arial_40);
        g2.setColor(Color.white); 

        if(gp.gameState == gp.playState){
            
        }else if(gp.gameState == gp.pauseState){
            drawPauseScreen(); 
        }else if(gp.gameState == gp.titleState){
            drawTitleScreen(); 
        }
    }
    
    public void drawPauseScreen(){
        String text = "PAUSED"; 
        int x = getXForCenteredText(text); 
        int y = gp.screenHeight /2; 
        
        g2.drawString(text, x, y);
    }
    
    
    public int getXForCenteredText(String text){
         
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2; 
        return x; 
    }
    
    public void drawTitleScreen(){
        g2.setColor(Color.black);  // Set the background color 
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        String text = "Pokemón Rojo Fuego"; 
        int x = getXForCenteredText(text); 
        int y = gp.tileSize * 3; 
        
        g2.setColor(Color.white); 
        g2.drawString(text,x+2,y+2); 
        g2.setColor(Color.red); // Set the text color 
        g2.drawString(text,x,y); 
        
        
        
        x = gp.screenWidth/2 - (gp.tileSize*2)/2 ; 
        y += gp.tileSize*2; 
        g2.drawImage(gp.obj[3].image, x, y, gp.tileSize*2, gp.tileSize * 2, null); 
        
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        
        g2.setColor(Color.white);
        text = "NEW GAME"; 
        x = getXForCenteredText(text); 
        y = gp.tileSize * 8; 
        g2.drawString(text, x, y); 
        if(commandNumber == 0){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
        
        text = "LOAD GAME"; 
        x = getXForCenteredText(text); 
        y = gp.tileSize * 9; 
        g2.drawString(text, x, y); 
        if(commandNumber == 1){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
        text = "QUIT GAME"; 
        x = getXForCenteredText(text); 
        y = gp.tileSize * 10; 
        g2.drawString(text, x, y); 
        if(commandNumber == 2){
            g2.drawString(">",x - gp.tileSize,y); 
        }
    }
    
}
