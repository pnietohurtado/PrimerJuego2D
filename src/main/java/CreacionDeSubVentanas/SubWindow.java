/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CreacionDeSubVentanas;

import com.mycompany.primerjuego2d.main.GamePanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author pablo
 */
public class SubWindow {
    
    // -------------------------- Declaració de Variables ----------------------
    
    private Graphics2D g2; 
    private GamePanel gp; 
    private int X; 
    private int Y; 
    private Color color1; 
    private Color color2; 
    
    // ------------------------- Constructor -----------------------------------
    
    public SubWindow(GamePanel gp) {
        this.gp = gp; 
        
        this.X = 0; 
        this.Y = 0; 
    }
    
    // ------------------------- SubVentana personalizada ----------------------
    
    public void SubWindow(int x, int y,int width, int height, Color c1, Color c2){
        
        g2.setColor(c1); 
        g2.fillRoundRect(x,y,width,height,35,35); 

        g2.setColor(c2); 
        g2.setStroke(new BasicStroke(5)); 
        g2.drawRoundRect(x+5, y+5, width-10, height-10 , 25, 25);
        
    }
    
    // ------------------------ Draw -------------------------------------------
    
    public void draw(Graphics2D g2){
        this.g2 = g2; 
    }
    
}
