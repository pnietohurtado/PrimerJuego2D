/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import com.mycompany.primerjuego2d.main.GamePanel;
import java.awt.Graphics2D;
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
    public boolean collision = false; 
    public int worldX, worldY; 
    
    public void draw(Graphics2D g2, GamePanel gamePanel){
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; 
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY; 
            
        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
            worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
            worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
            worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                  
            g2.drawImage(image, screenX,screenY,gamePanel.tileSize,gamePanel.tileSize,null); 
        }

    }
    
}
