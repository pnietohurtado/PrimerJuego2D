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
    BufferedImage keyImage; 
    BufferedImage pokeImage; 
    public boolean messageOn = false; 
    public String message = ""; 
    int messageCounter = 0; 
    
    public UI(GamePanel gp){
        this.gp = gp; 
        
        arial_40 = new Font("Arial", Font.PLAIN, 40); 
        OBJ_Key key = new OBJ_Key(); 
        keyImage = key.image; 
        
        OBJ_Pokeball poke = new OBJ_Pokeball(); 
        pokeImage = poke.image; 
    }
    
    public void showMessage(String text){
        message = text; 
        messageOn = true; 
    }
    
    
    public void draw(Graphics2D g2){
        g2.setFont(arial_40); 
        g2.setColor(Color.white); 
        g2.drawImage(pokeImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null); 
        g2.drawString("x " + gp.player.hasPokeball, 74,65);
        
        // Message
        if(messageOn == true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5); 
            messageCounter++; 
            
            if(messageCounter > 120){
                messageCounter = 0; 
                messageOn = false; 
            }
        }
    }
    
}
