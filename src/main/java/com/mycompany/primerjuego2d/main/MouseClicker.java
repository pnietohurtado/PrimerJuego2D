
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author pablo
 */
public class MouseClicker implements MouseListener{

    public int clickX; 
    public int clickY; 
    public GamePanel gp; 
    
    public MouseClicker(GamePanel gp){
        this.gp = gp; 
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.clickX = e.getX(); 
        this.clickY = e.getY(); 
        
        if(gp.gameState == gp.titleState){
            if((this.clickX >= 5 || this.clickX <= 10) && this.clickY == 8) {
                gp.gameState = gp.playState; 
            }
        }
        
        System.out.println("Pulsando en la casilla " + clickX + " " + clickY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
    }
    

    // Prueba de método para poder interactuar con el Mouse
    public void draw(Graphics2D g2){
        g2.setColor(Color.white); 
        g2.drawRect(clickX , clickY , gp.tileSize, gp.tileSize);
    }
    
}
