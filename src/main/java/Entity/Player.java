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
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Frogy.png"));
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
                image = up1; 
                break;
            case "down": 
                image = up1; 
                break;
            case "left": 
                image = up1; 
                break;
            case "right": 
                image = up1; 
                break;
        }
        
            g2.drawImage(image,x,y,gamePanel.tileSize, gamePanel.tileSize,null); 
    }
    
}
