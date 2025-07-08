
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

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
        this.clickX = e.getX() / gp.tileSize; 
        this.clickY = e.getY() / gp.tileSize;
        
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
    
}
