/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class Entity {
    public int worldX, worldY; 
    public int speed; 

    GamePanel gp; 
    
    public Entity(GamePanel gp){
        this.gp = gp; 
    }
    
    public BufferedImage f1,f2,l1,l2,r1,r2,b1,b2; 
    public String direction; 
    
    public int spriteCounter = 0; 
    public int spriteNum = 1; 
    
    public Rectangle solidArea = new Rectangle(0,0,48,48); // Basically we create the collision range of the player 
    public Rectangle nearSolidArea ; 
    public int solidAreaDefaultX, solidAreaDefaultY; 
    public boolean collision = false; 
    
    
    public int actionLockCounter = 0; 
    
    
    
    
    public BufferedImage setUp(String imagePath){
        UtilityTool u = new UtilityTool(); 
        BufferedImage image = null; 
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = u.scaleImage(image, gp.tileSize, gp.tileSize); 
            
        }catch(IOException e){
            e.printStackTrace(); 
        }
        
        return image; 
    }
    
    
    public void setAction(){}
    public void update(){
        
        setAction(); 
        
        collision = false; 
        gp.cH.checkTile(this);
        gp.cH.checkObject(this, false); 
        gp.cH.checkPlayer(this);
        
        if(collision == false){
                switch(direction ){
                    case "up": 
                        worldY -= speed;  
                        break; 
                    case "down": 
                        worldY += speed; 
                        break; 
                    case "left": 
                        worldX -= speed;
                        break; 
                    case "right": 
                        worldX += speed; 
                        break; 
                }
            }
            
            spriteCounter++; 
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2; 
                }else if (spriteNum == 2){
                    spriteNum = 1; 
                }
                spriteCounter = 0; 
            }  
    }
    
    
    public void draw(Graphics2D g2){
        BufferedImage image = null; 
        int screenX = worldX - gp.player.worldX + gp.player.screenX; 
        int screenY = worldY - gp.player.worldY + gp.player.screenY; 
            
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            
                switch(direction) {
                    case "up": 
                        if(spriteNum == 1) {
                            image = b1; 
                        }else if(spriteNum == 2){
                            image = b2; 
                        }
                        break;
                    case "down": 
                        if(spriteNum == 1) {
                            image = f1; 
                        }else if(spriteNum == 2){
                            image = f2; 
                        }
                        break;
                    case "left": 
                        if(spriteNum == 1) {
                            image = l1; 
                        }else if(spriteNum == 2){
                            image = l2; 
                        }
                        break;
                    case "right": 
                        if(spriteNum == 1) {
                            image = r1; 
                        }else if(spriteNum == 2){
                            image = r2; 
                        }
                        break;
                }
        
                  
            g2.drawImage(image, screenX,screenY,gp.tileSize,gp.tileSize,null); 
        
        }
        
        
       
        
        
        
    }
    
    
    
}
