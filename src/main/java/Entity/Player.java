/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.awt.image.BufferedImage;
import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import com.mycompany.primerjuego2d.main.UtilityTool;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
/**/
/**
 *
 * @author pablo
 */
public class Player extends Entity{
    
    KeyHandler keyHandler; 
    
    
    public final int screenX;
    public final int screenY; 
    
    //public int hasKey = 0; // How many key the player currently has 
    public int hasPokeball = 0; // It works as a "Inventory" 
    
    public Player(GamePanel gp, KeyHandler kh){
        super(gp); 
        
        this.keyHandler = kh; 
        
        screenX = gp.screenWidth/2 - (gp.tileSize / 2); 
        screenY = gp.screenHeight/2 - (gp.tileSize /2); 
        
        solidArea = new Rectangle();
        solidArea.x = 8; 
        solidArea.y = 16; 
        
        solidAreaDefaultX = solidArea.x; 
        solidAreaDefaultY = solidArea.y; 
        
        solidArea.width = 32; 
        solidArea.height = 32; 
        
        setDefaultValues(); 
        getPlayerImage(); 
    }
    
    public void setDefaultValues()
    {
        worldX = gp.tileSize * 35 ; 
        worldY = gp.tileSize * 410 ; 
        speed = 4; 
        direction = "down"; 
    }
    
    public void getPlayerImage()
    {
       
        f1 = setUp("/player/Front1");
        f2 = setUp("/player/Front2");
        r1 = setUp("/player/Rigth1");
        r2 = setUp("/player/Rigth2");
        l1 = setUp("/player/Left1");
        l2 = setUp("/player/Left2");
        b1 = setUp("/player/Up1");
        b2 = setUp("/player/Up2");
       
    }
   
    
    public void update()
    {
        if(keyHandler.upPressed == true || keyHandler.downPressed == true ||
                keyHandler.leftPressed == true || keyHandler.rightPressed == true ){
            
            if(keyHandler.upPressed == true)
            {
                direction = "up"; 
            }
            else if(keyHandler.downPressed == true)
            {
                direction = "down";               
            }
            else if(keyHandler.leftPressed == true)
            {
                direction="left";                
            }
            else if(keyHandler.rightPressed == true)
            {
                direction="right";                
            }

            // Check tile collision 
            collision = false; 
            gp.cH.checkTile(this);
            
            // Check object Collision 
            int objIndex = gp.cH.checkObject(this, true); 
            pickUpObject (objIndex); 
            
            // If collision is false, player can't move 
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
    }
    
    public void pickUpObject(int i){
        if(i != 999){
            System.out.println("obj -> " +i);
            
            String objectName = gp.obj[i].name; 
            
            switch(objectName){
                /*
                case "Llave": 
                    if(keyHandler.catchObject == true){
                        hasKey++; 
                        gamePanel.obj[i] = null; 
                        gamePanel.ui.showMessage("You got a key!"); 
                   
                    }
                    break; */
                case "Pokeball": 
                    if(keyHandler.catchObject == true){
                        hasPokeball++; 
                        gp.obj[i] = null; 
                        gp.gameState = gp.dialogueState; 
                    }
                    break; 
            
            }
        }
    }
    
    
    
    public void draw(Graphics2D g2) 
    {
        /*
        g2.setColor(Color.white); 
        g2.fillRect(this.x, this.y, gamePanel.tileSize, gamePanel.tileSize);  
        */
        
        BufferedImage image = null; 
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
        
            g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize,null); 
            if(this.keyHandler.showCollisions == true){
                g2.setColor(Color.red); 
                g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }
    }
    
}
