/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author pablo
 */
public class SuperObject 
        /*Parent class to all objects class*/
{
    
    public BufferedImage image; 
    public String name; 
    public KeyHandler kH; 
    public boolean collision = false; 
    public int worldX, worldY; 
    public Rectangle solidArea = new Rectangle(0,0,48,48); 
    public int solidAreaDefaultX = 0; 
    public int solidAreaDefaultY = 0;
    
    // ------- En el caso de que queramos que el objeto sea más grande ---------
    public int multiplicadorSize = 1; 
    // -------------------------------------------------------------------------
    
    public void draw(Graphics2D g2, GamePanel gamePanel){
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; 
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY; 
            
        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
            worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
            worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
            worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                  
            g2.drawImage(image, screenX,screenY,(gamePanel.tileSize) * multiplicadorSize,(gamePanel.tileSize) * multiplicadorSize,null); 
        
        }
        /*
        if(this.kH.showCollisions == true){
            g2.setColor(Color.red); 
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
        */
    }
    
    
    @Override 
    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        sb.append(name).append("\n"); 
        return sb.toString(); 
    }
}
