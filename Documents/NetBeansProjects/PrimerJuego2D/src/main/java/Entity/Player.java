/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.awt.image.BufferedImage;
import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class Player extends Entity{
    
    GamePanel gamePanel; 
    KeyHandler keyHandler; 
    
    public Player(GamePanel gp, KeyHandler kh){
        this.gamePanel = gp; 
        this.keyHandler = kh; 
        
        setDefaultValues(); 
        getPlayerImage(); 
    }
    
    public void setDefaultValues()
    {
        x = 100; 
        y = 100; 
        speed = 4; 
        direction = "down"; 
    }
    
    public void getPlayerImage()
    {
        try{
            f1 = ImageIO.read(getClass().getResourceAsStream("/player/Front1.png"));
            f2 = ImageIO.read(getClass().getResourceAsStream("/player/Front2.png"));
            l1 = ImageIO.read(getClass().getResourceAsStream("/player/Left1.png"));
            l2 = ImageIO.read(getClass().getResourceAsStream("/player/Left2.png"));
            r1 = ImageIO.read(getClass().getResourceAsStream("/player/Rigth1.png"));
            r2 = ImageIO.read(getClass().getResourceAsStream("/player/Rigth2.png"));
            b1 = ImageIO.read(getClass().getResourceAsStream("/player/Up1.png"));
            b2 = ImageIO.read(getClass().getResourceAsStream("/player/Up2.png"));
        }catch(IOException e){
            e.printStackTrace(); 
        }
    }
    
    public void update()
    {
        if(keyHandler.upPressed == true)
        {
            direction = "up"; 
            y -= speed;  
        }
        else if(keyHandler.downPressed == true)
        {
            direction = "down";
            y += speed; 
        }
        else if(keyHandler.leftPressed == true)
        {
            direction="left";
            x -= speed;
        }
        else if(keyHandler.rightPressed == true)
        {
            direction="right";
            x += speed; 
        }
        
        spriteCounter++; 
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2; 
            }else if(spriteNum == 2){
                spriteNum = 1; 
            }
            spriteCounter = 0; 
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
                if(spriteNum == 1){
                    image = b1; 
                }
                else if(spriteNum == 2){
                    image = b2; 
                }
                break;
            case "down": 
                if(spriteNum == 1){
                    image = f1; 
                }
                else if(spriteNum == 2){
                    image = f2; 
                }
                break;
            case "left": 
                if(spriteNum == 1){
                    image = l1; 
                }
                else if(spriteNum == 2){
                    image = l2; 
                }
                break;
            case "right": 
                if(spriteNum == 1){
                    image = r1; 
                }
                else if(spriteNum == 2){
                    image = r2; 
                }
                break;
        }
        
            g2.drawImage(image,x,y,gamePanel.tileSize, gamePanel.tileSize,null); 
    }
    
}
