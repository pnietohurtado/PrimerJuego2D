/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.UtilityTool;
import java.awt.AlphaComposite;
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
    
    // --------------------------- Declaración de variables --------------------
    
    public int worldX, worldY; 
    public int speed; 
    
    public BufferedImage f1,f2,l1,l2,r1,r2,b1,b2; // Imagenes necesarias para crear movimiento de personaje 
    public String direction; 
    
    public int spriteCounter = 0; 
    public int spriteNum = 1; 
    
    public Rectangle solidArea = new Rectangle(0,0,48,48); 
    public Rectangle nearSolidArea ; 
    public int solidAreaDefaultX, solidAreaDefaultY; 
    public boolean collision = false; 
    
    public boolean move = false; // False se puede mover, True no se puede mover 
    
    public int actionCountNPC = 0;  // Interacciones del NPC por colision del usuario
    public boolean collisionPlayer = false; 
    
    public int actionLockCounter = 0; // Para poder determinar la velocidad de NPC
    
    GamePanel gp; 
    
    public String name; 
    
    // -- Prueba sobre animación 
    public float opacity = 1.0f; 
    

    
    // -------------------------------------------------------------------------
    
    // Constructor de la clase 
    public Entity(GamePanel gp){
        this.gp = gp; 
    }
    
    
    
    // --------------------------- Función de lectura imágenes -----------------
    
    public int duplicarSize = 1; 
    public BufferedImage setUp(String imagePath){
        UtilityTool u = new UtilityTool(); 
        BufferedImage image = null; 
        
        try{

            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = u.scaleImage(image, gp.tileSize , gp.tileSize); 
            
        }catch(IOException e){
            e.printStackTrace(); 
        }
        
        return image; 
    }
    
    // -------------------------------------------------------------------------
    
    
    public void setAction(){
        
    }
    
    
    public void update(){
        if(this.move == false){
        setAction(); 
        
        collision = false; 
        gp.cH.checkTile(this);
        gp.cH.checkObject(this, false); 
        gp.cH.checkPlayer(this); // Esto hace que exista colisión de el NPC con el player
        
        //int npcIndex = gp.cH.checkEntity(this, gp.npc); 
        
        //Forma de que el NPC interactue con el player 
        
        /*
        if(gp.npc[gp.player.numero].actionCountNPC == 1 && gp.cH.collisionPlayer == true ){
            
            gp.sonido.volume = -10.0f; 
            gp.playMusicOnce(4); // Para poder poner la música
            
            gp.gameState = gp.dialogueState; 
            gp.npc[gp.player.numero].actionCountNPC = 0; 
        }
        */
        
        if(collision == false ){
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
        
            
        
        /*
            int npcIndex = gp.cH.checkEntity(this, gp.player); 
            //int playerIndex = gp.cH.checkEntity(this, gp.player); 
            interactNPC(npcIndex); 
        */  
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
        
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity); 
            g2.setComposite(ac);
            g2.drawImage(image, screenX,screenY,gp.tileSize * duplicarSize,gp.tileSize * duplicarSize,null); 
        
        }
        
        
       
        
        
        
    }
    
    
    
}
